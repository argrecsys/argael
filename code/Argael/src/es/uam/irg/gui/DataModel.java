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
import es.uam.irg.utils.FileUtils;
import es.uam.irg.utils.StringUtils;
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
    private static final String USERS_FILEPATH = "Resources/config/users.txt";
    private final List<String> components;

    private final Map<String, String> files;
    private final ReportFormatter formatter;
    private final List<String> qualityMetrics;
    private final List<String> relCategories;
    private final List<String> relIntents;

    /**
     * Data model constructor.
     *
     * @param components (e.g., claim, premise, major claim)
     * @param relCategories (e.g., addition, conclusion, summary)
     * @param relIntents (e.g., support, attack)
     * @param qualityMetrics (e.g., incorrect, not relevant, relevant)
     */
    public DataModel(List<String> components, List<String> relCategories, List<String> relIntents, List<String> qualityMetrics) {
        this.components = components;
        this.relCategories = relCategories;
        this.relIntents = relIntents;
        this.qualityMetrics = qualityMetrics;
        this.files = new HashMap<>();
        this.formatter = new ReportFormatter(DECIMAL_FORMAT, DATE_FORMAT);
    }

    /**
     *
     * @return
     */
    public List<String> getArgumentComponents() {
        return this.components;
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
                content = IOManager.readTextFile(filePath);
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
     * @return
     */
    public List<String> getQualityMetrics() {
        return qualityMetrics;
    }

    /**
     *
     * @return
     */
    public List<String> getRelationCategories() {
        return relCategories;
    }

    /**
     *
     * @return
     */
    public List<String> getRelationIntents() {
        return relIntents;
    }

    /**
     *
     * @return
     */
    public String[] getUserList() {
        List<String> annotators = IOManager.readUsers(USERS_FILEPATH);
        return annotators.toArray(new String[0]);
    }

    /**
     *
     * @param currDirectory
     * @param fileExt
     * @return
     */
    public List<String> readFilenamesInFolder(String currDirectory, String fileExt) {
        return FileUtils.readFilenamesInFolder(currDirectory, fileExt);
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

}
