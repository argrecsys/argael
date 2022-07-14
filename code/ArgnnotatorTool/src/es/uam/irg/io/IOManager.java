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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
// import org.yaml.snakeyaml.Yaml;

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

        try {
            File txtFile = new File(filepath);

            if (txtFile.exists() && txtFile.isFile()) {
                try ( BufferedReader reader = new BufferedReader(new FileReader(txtFile))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        annotators.add(line.trim());
                    }
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(IOManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return annotators;
    }

    /**
     *
     * @param filepath
     * @return
     */
    public static String readFile(String filepath) {
        Path filePath = Path.of(filepath);
        return readFile(filePath);
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

        try {
            // Get the file
            File csvFile = new File(taxonomyFilepath);

            // Check if the specified file exists or not
            if (csvFile.exists()) {
                try ( BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                    String row;
                    String category;
                    String subCategory;

                    reader.readLine();
                    while ((row = reader.readLine()) != null) {
                        String[] data = row.split(",");

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
            }

        } catch (IOException ex) {
            Logger.getLogger(IOManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return taxonomy;
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
                // Yaml yaml = new Yaml();
                // data = (Map<String, Object>) yaml.load(inputStream);
            }

        } catch (IOException ex) {
            Logger.getLogger(IOManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;
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
            content = Files.readString(filepath);

        } catch (OutOfMemoryError | IOException ex) {
            Logger.getLogger(IOManager.class.getName()).log(Level.SEVERE, null, ex);
            content = "";
        }
        return content;
    }

}
