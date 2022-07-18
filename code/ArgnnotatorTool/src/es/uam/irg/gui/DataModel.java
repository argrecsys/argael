/**
 * Copyright 2022
 * Andrés Segura-Tinoco
 * Information Retrieval Group at Universidad Autonoma de Madrid
 *
 * This is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * the current software. If not, see <http://www.gnu.org/licenses/>.
 */
package es.uam.irg.gui;

import es.uam.irg.io.IOManager;
import es.uam.irg.utils.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Argument annotator tool data model class.
 */
public class DataModel {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DECIMAL_FORMAT = "0.000";
    private static final String LANG = "es";
    private static final String USERS_FILEPATH = "Resources/config/annotators.txt";

    private final Map<String, String> files;
    private final ReportFormatter formatter;
    private Map<String, List<String>> taxonomy;

    /**
     * Constructor.
     */
    public DataModel() {
        this.files = new HashMap<>();
        this.formatter = new ReportFormatter(DECIMAL_FORMAT, DATE_FORMAT);
        this.loadRelationTaxonomy();
    }

    /**
     *
     * @return
     */
    public String[] getAnnotatorList() {
        List<String> annotators = IOManager.readAnnotators(USERS_FILEPATH);
        return annotators.toArray(new String[0]);
    }

    /**
     *
     * @param filePath
     * @param fileType
     * @return
     */
    public String getFileReport(String filePath, String fileType) {
        String content = "";
        String fileName = StringUtils.getLastToken(filePath, "\\\\");

        if (!fileName.equals("")) {
            if (files.containsKey(fileName)) {
                content = files.get(fileName);
            } else {
                content = IOManager.readFile(filePath);
                content = formatter.getPrettyReport(content, fileType);
                files.put(fileName, content);
            }
        }

        return content;
    }

    /**
     *
     * @return
     */
    public ReportFormatter getFormatter() {
        return this.formatter;
    }

    /**
     *
     * @param defaultValue
     * @return
     */
    public List<String> getSubCategories(boolean defaultValue) {
        List<String> subCategories = new ArrayList<>();

        if (defaultValue) {
            subCategories.add("-");
        }
        taxonomy.values().forEach(valueList -> {
            valueList.forEach(value -> {
                subCategories.add(value.toLowerCase());
            });
        });

        return subCategories;
    }

    /**
     *
     * @param currDirectory
     * @param fileExt
     * @return
     */
    public List<String> readFilenamesInFolder(String currDirectory, String fileExt) {
        return IOManager.readFilenamesInFolder(currDirectory, fileExt);
    }

    /**
     *
     * @param content
     * @param filePath
     */
    public void setFileReport(String content, String filePath) {
        String fileName = StringUtils.getLastToken(filePath, "\\\\");

        if (!fileName.equals("")) {
            files.put(fileName, content);
        }
    }

    /**
     *
     */
    private void loadRelationTaxonomy() {
        this.taxonomy = IOManager.readRelationTaxonomy(LANG);

        System.out.println(">> Taxonomy:");
        for (String category : taxonomy.keySet()) {
            System.out.println(" - " + category);
            List<String> subCategories = taxonomy.get(category);
            java.util.Collections.sort(subCategories);
            subCategories.forEach(subCategory -> {
                System.out.println("\t" + subCategory);
            });
        }

    }

}
