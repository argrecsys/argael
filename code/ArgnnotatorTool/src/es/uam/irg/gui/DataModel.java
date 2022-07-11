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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Argument annotator tool data model class.
 */
public class DataModel {

    private static final String LANG = "es";

    private final String dateFormat;
    private final String decimalFormat;
    private final Map<String, String> files;
    private Map<String, List<String>> taxonomy;

    /**
     *
     * @param decimalFormat
     * @param dateFormat
     */
    public DataModel(String decimalFormat, String dateFormat) {
        this.decimalFormat = decimalFormat;
        this.dateFormat = dateFormat;
        this.files = new HashMap<>();

        loadRelationTaxonomy();
    }

    /**
     *
     * @param fileName
     * @param directory
     * @return
     */
    public String getFileContent(String fileName, String directory) {
        String content = "";
        if (files.containsKey(fileName)) {
            content = files.get(fileName);
        } else {
            String filepath = directory + "\\" + fileName;
            content = IOManager.readFile(filepath);
            files.put(fileName, content);
        }

        return content;
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
