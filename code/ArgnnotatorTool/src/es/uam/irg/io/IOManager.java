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

import es.uam.irg.utils.FunctionUtils;
import es.uam.irg.utils.StringUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.yaml.snakeyaml.Yaml;

/**
 * Input-output manager class.
 */
public class IOManager {

    // Class constants
    private static final String LEXICON_FILEPATH = "Resources/data/argument_lexicon_{}.csv";

    /**
     *
     * @param filepath
     * @return
     */
    public static List<String> readAnnotators(String filepath) {
        List<String> annotators = new ArrayList<>();

        // Get the file
        Path filePath = Path.of(filepath);
        String content = readFile(filePath);

        // Check if the specified file exists or not
        if (!StringUtils.isEmpty(content)) {
            String[] rows = content.split("\n");
            annotators = new ArrayList<>(Arrays.asList(rows));
        }

        return annotators;
    }

    /**
     *
     * @param filepath
     * @return
     */
    public static List<String[]> readCsvFile(String filepath) {
        List<String[]> csvFile = new ArrayList<>();

        Path filePath = Path.of(filepath);
        String content = readFile(filePath);

        // Check if the specified file exists or not
        if (!StringUtils.isEmpty(content)) {
            String[] rows = content.split("\n");

            for (int i = 1; i < rows.length; i++) {
                String[] data = rows[i].split(",");
                csvFile.add(data);
            }
        }

        return csvFile;
    }

    /**
     *
     * @param directory
     * @param fileExt
     * @return
     */
    public static List<String> readFilenamesInFolder(String directory, String fileExt) {
        List<String> fileNames = new ArrayList<>();
        File folder = new File(directory);
        String currName;
        String currExt;

        for (File file : folder.listFiles()) {
            if (file.isFile()) {
                currName = file.getName();
                currExt = FunctionUtils.getFileExtension(currName);
                if (currExt.equals(fileExt)) {
                    fileNames.add(FunctionUtils.getFilenameWithoutExt(currName));
                }
            }
        }

        return fileNames;
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
                    String content = readFile(filepath);
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
        Path filePath = Path.of(taxonomyFilepath);
        String content = readFile(filePath);

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
        Path filePath = Path.of(filepath);
        return readFile(filePath);
    }

    /**
     *
     * @param filepath
     * @return
     */
    public static Map<String, Object> readYamlFile(String filepath) {
        Map<String, Object> data = null;

        try {
            // Get the file
            File yamlFile = new File(filepath);

            // Check if the specified file exists or not
            if (yamlFile.exists()) {
                InputStream inputStream = new FileInputStream(yamlFile);
                Yaml yaml = new Yaml();
                data = (Map<String, Object>) yaml.load(inputStream);
            }

        } catch (IOException ex) {
            Logger.getLogger(IOManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;
    }

    /**
     *
     * @param filepath
     * @param header
     * @param data
     * @return
     */
    public static boolean saveCsvData(String filepath, List<String> header, List<String[]> data) {
        boolean result = false;

        try ( PrintWriter writer = new PrintWriter(filepath)) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < header.size(); i++) {
                sb.append(header.get(i));
                if (i == header.size() - 1) {
                    sb.append("\n");
                } else {
                    sb.append(",");
                }
            }

            data.forEach(row -> {
                for (int i = 0; i < row.length; i++) {
                    sb.append(row[i]);
                    if (i == row.length - 1) {
                        sb.append("\n");
                    } else {
                        sb.append(",");
                    }
                }
            });

            writer.write(sb.toString());
            result = true;

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    /**
     *
     * @param filename
     * @return
     */
    private static String getFileName(String filename) {
        filename = FunctionUtils.getFilenameWithoutExt(filename);
        return filename.replace("-", "_").toUpperCase();
    }

    /**
     *
     * @param filepath
     * @return
     */
    private static String readFile(Path filepath) {
        String content = "";
        try {
            if (filepath.toFile().exists()) {
                content = Files.readString(filepath);
            }

        } catch (OutOfMemoryError | IOException ex) {
            Logger.getLogger(IOManager.class.getName()).log(Level.SEVERE, null, ex);
            content = "";
        }
        return content;
    }

}
