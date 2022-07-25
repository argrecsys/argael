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
package es.uam.irg.io;

import es.uam.irg.utils.FileUtils;
import es.uam.irg.utils.StringUtils;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Input-output manager class.
 */
public class IOManager {

    // Class constants
    private static final String LEXICON_FILEPATH = "Resources/data/argument_lexicon_{}.csv";

    /**
     *
     * @param directory
     * @param currFile
     * @return
     */
    public static Map<String, List<String[]>> readAnnotationData(String directory, String currFile) {
        Map<String, List<String[]>> data = new HashMap<>();
        String acuFile = directory + currFile + "_acu.csv";
        String relFile = directory + currFile + "_rel.csv";
        List<String[]> acuData = FileUtils.readCsvFile(acuFile);
        List<String[]> relData = FileUtils.readCsvFile(relFile);
        data.put("acu", acuData);
        data.put("rel", relData);
        return data;
    }

    /**
     *
     * @param filepath
     * @return
     */
    public static List<String> readAnnotators(String filepath) {
        List<String> annotators = new ArrayList<>();

        // Get the file
        String content = FileUtils.readFile(filepath);

        // Check if the specified file exists or not
        if (!StringUtils.isEmpty(content)) {
            String[] rows = content.split("\n");
            for (String user : rows) {
                annotators.add(user.replace("\r", ""));
            }
        }

        return annotators;
    }

    /**
     *
     * @param folderPath
     * @return
     */
    public static Map<String, String> readHtmlReports(String folderPath) {
        Map<String, String> reports = new HashMap<>();
        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory()) {
            for (File fileEntry : folder.listFiles()) {
                if (fileEntry.isFile()) {
                    Path filepath = Paths.get(fileEntry.getPath());
                    String filename = getFileName(fileEntry.getName());
                    String content = FileUtils.readFile(filepath);
                    reports.put(filename, content);
                }
            }
        }

        return reports;
    }

    /**
     *
     * @param lang
     * @return
     */
    public static Map<String, List<String>> readRelationTaxonomy(String lang) {
        Map<String, List<String>> taxonomy = new HashMap<>();
        String taxonomyFilepath = LEXICON_FILEPATH.replace("{}", lang);

        // Get the file
        String content = FileUtils.readFile(taxonomyFilepath);

        // Check if the specified file exists or not
        if (!StringUtils.isEmpty(content)) {
            String[] rows = content.split("\n");
            String category;
            String subCategory;

            for (int i = 1; i < rows.length; i++) {
                String[] data = rows[i].split(",");

                if (data.length == 6) {
                    category = StringUtils.toTitleCase(data[2]);
                    subCategory = StringUtils.toTitleCase(data[3]);

                    if (!taxonomy.containsKey(category)) {
                        taxonomy.put(category, new ArrayList<>());
                    }
                    if (!taxonomy.get(category).contains(subCategory)) {
                        taxonomy.get(category).add(subCategory);
                    }
                }
            }
        }

        return taxonomy;
    }

    /**
     *
     * @param filepath
     * @return
     */
    public static String readTextFile(String filepath) {
        return FileUtils.readFile(filepath);
    }

    /**
     *
     * @param filename
     * @return
     */
    private static String getFileName(String filename) {
        filename = FileUtils.getFilenameWithoutExt(filename);
        return filename.replace("-", "_").toUpperCase();
    }

}
