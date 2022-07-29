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
import es.uam.irg.utils.FunctionUtils;
import es.uam.irg.utils.StringUtils;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Input-output manager class.
 */
public class IOManager {

    // Class constants
    public static final List<String> ARG_FILE_TYPES = Arrays.asList(new String[]{"arg_comp", "arg_rel"});

    /**
     *
     * @param directory
     * @param currFile
     * @return
     */
    public static Map<String, List<String[]>> readAnnotationData(String directory, String currFile) {
        Map<String, List<String[]>> annotations = new HashMap<>();

        ARG_FILE_TYPES.forEach(fileType -> {
            String filePath = directory + currFile + "_" + fileType + ".csv";
            List<String[]> data = FileUtils.readCsvFile(filePath);
            annotations.put(fileType, data);
        });

        return annotations;
    }

    /**
     *
     * @param directory
     * @param currFile
     * @return
     */
    public static Map<String, Map<Integer, String>> readEvaluationData(String directory, String currFile) {
        Map<String, Map<Integer, String>> evaluations = new HashMap<>();

        ARG_FILE_TYPES.forEach(fileType -> {
            String filePath = directory + currFile + "_" + fileType + ".csv";
            List<String[]> data = FileUtils.readCsvFile(filePath);
            Map<Integer, String> evals = new HashMap<>();
            for (String[] row : data) {
                if (FunctionUtils.isNumeric(row[0])) {
                    int evalKey = Integer.parseInt(row[0]);
                    String evalValues = row[1];
                    evals.put(evalKey, evalValues);
                }
            }
            evaluations.put(fileType, evals);
        });

        return evaluations;
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
     * @param filepath
     * @return
     */
    public static String readTextFile(String filepath) {
        return FileUtils.readFile(filepath);
    }

    /**
     *
     * @param filepath
     * @return
     */
    public static List<String> readUsers(String filepath) {
        List<String> users = new ArrayList<>();

        // Get the file
        String content = FileUtils.readFile(filepath);

        // Check if the specified file exists or not
        if (!StringUtils.isEmpty(content)) {
            String[] rows = content.split("\n");
            for (String user : rows) {
                users.add(user.replace("\r", ""));
            }
        }

        return users;
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
