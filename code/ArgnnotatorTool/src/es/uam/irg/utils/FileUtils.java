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
package es.uam.irg.utils;

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
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.yaml.snakeyaml.Yaml;
import com.opencsv.CSVReader;
import java.io.FileReader;

/**
 *
 * @author Usuario
 */
public class FileUtils {

    /**
     *
     * @param filename
     * @return
     */
    public static String getFileExtension(String filename) {
        int index = filename.lastIndexOf(".");
        if (index == -1) {
            return "";
        }
        return filename.substring(index + 1);
    }

    /**
     *
     * @param filename
     * @return
     */
    public static String getFilenameWithoutExt(String filename) {
        int index = filename.lastIndexOf(".");
        if (index == -1) {
            return filename;
        }
        return filename.substring(0, index);
    }

    /**
     *
     * @param filepath
     * @return
     */
    public static List<String[]> readCsvFile(String filepath) {
        List<String[]> csvFile = new ArrayList<>();

        try ( CSVReader reader = new CSVReader(new FileReader(filepath))) {
            csvFile = reader.readAll();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return csvFile;
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
     * @param filepath
     * @return
     */
    public static String readFile(Path filepath) {
        String content = "";
        try {
            if (filepath.toFile().exists()) {
                content = Files.readString(filepath);
            }

        } catch (OutOfMemoryError | IOException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return content;
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
                currExt = getFileExtension(currName);
                if (currExt.equals(fileExt)) {
                    fileNames.add(getFilenameWithoutExt(currName));
                }
            }
        }

        return fileNames;
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
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
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
    public static boolean saveCsvFile(String filepath, List<String> header, List<String[]> data) {
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

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    /**
     *
     * @param filepath
     * @param content
     * @return
     */
    public static boolean saveFile(String filepath, String content) {
        boolean result = false;
        try {
            if (!filepath.isEmpty() && !content.isEmpty()) {
                Files.write(Paths.get(filepath), content.getBytes());
                result = true;
            }
        } catch (IOException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
