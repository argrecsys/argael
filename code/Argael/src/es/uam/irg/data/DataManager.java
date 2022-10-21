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
package es.uam.irg.data;

import es.uam.irg.io.IOManager;
import java.util.List;
import java.util.Map;

/**
 * Data model class.
 */
public class DataManager {

    public static final String ANNOTATIONS = "annotations";
    public static final String EVALUATIONS = "evaluations";
    
    // Class members
    private String directory;
    private final DataType type;

    /**
     * Constructor 1: CSV result data type.
     *
     * @param directory
     */
    public DataManager(String directory) {
        this.type = DataType.CSV;
        this.directory = directory;
    }

    /**
     * Constructor 2: Database result data type.
     *
     * @param server
     * @param instance
     * @param user
     * @param pwd
     */
    public DataManager(String server, String instance, String user, String pwd) {
        this.type = DataType.DATABASE;
    }

    /**
     *
     * @param entity
     * @param userName
     * @return
     */
    public Map<String, List<String[]>> getUserAnnotations(String entity, String userName) {
        Map<String, List<String[]>> result = null;

        if (this.type == DataType.CSV) {

            String folderPath = directory + "/" + userName + "/" + ANNOTATIONS;
            result = IOManager.readAnnotationData(folderPath, entity);

        } else if (this.type == DataType.DATABASE) {

        }

        return result;
    }

    /**
     *
     * @param entity
     * @param userName
     * @param evalName
     * @return
     */
    public Map<String, Map<Integer, String>> getUserEvaluations(String entity, String userName, String evalName) {

        Map<String, Map<Integer, String>> result = null;

        if (this.type == DataType.CSV) {
            String folderPath = directory + "/" + userName + "/" + EVALUATIONS +"/" + evalName + "/";
            result = IOManager.readEvaluationData(folderPath, entity);

        } else if (this.type == DataType.DATABASE) {

        }

        return result;
    }

    /**
     *
     * @param entity
     * @param userName
     * @param rows
     * @return
     */
    public boolean saveArgCompAnnotations(String entity, String userName, List<String[]> rows) {
        boolean result = false;

        if (this.type == DataType.CSV) {
            String filePath = createFilepath(userName, ANNOTATIONS, entity, IOManager.FILE_ARG_COMP);
            String[] header = new String[]{"ac_id", "ac_text", "ac_type", "annotator", "timestamp"};
            result = IOManager.saveAnnotationData(filePath, header, rows);

        } else if (this.type == DataType.DATABASE) {

        }

        return result;
    }

    /**
     *
     * @param entity
     * @param userName
     * @param targetUser
     * @param rows
     * @return
     */
    public boolean saveArgCompEvaluations(String entity, String userName, String targetUser, List<String[]> rows) {
        boolean result = false;

        if (this.type == DataType.CSV) {
            String filePath = createFilepath(userName, EVALUATIONS + "/" + targetUser, entity, IOManager.FILE_ARG_COMP);
            String[] header = new String[]{"ac_id", "ac_quality", "evaluator", "timestamp"};
            result = IOManager.saveAnnotationData(filePath, header, rows);

        } else if (this.type == DataType.DATABASE) {

        }

        return result;
    }

    /**
     *
     * @param entity
     * @param userName
     * @param rows
     * @return
     */
    public boolean saveArgRelAnnotations(String entity, String userName, List<String[]> rows) {
        boolean result = false;

        if (this.type == DataType.CSV) {
            String filePath = createFilepath(userName, ANNOTATIONS, entity, IOManager.FILE_ARG_REL);
            String[] header = new String[]{"ar_id", "ac_id1", "ac_id2", "rel_type", "rel_intent", "annotator", "timestamp"};
            result = IOManager.saveAnnotationData(filePath, header, rows);

        } else if (this.type == DataType.DATABASE) {

        }

        return result;
    }

    /**
     *
     * @param entity
     * @param userName
     * @param targetUser
     * @param rows
     * @return
     */
    public boolean saveArgRelEvaluations(String entity, String userName, String targetUser, List<String[]> rows) {
        boolean result = false;

        if (this.type == DataType.CSV) {
            String filePath = createFilepath(userName, EVALUATIONS + "/" + targetUser, entity, IOManager.FILE_ARG_REL);
            String[] header = new String[]{"ac_id", "ac_quality", "evaluator", "timestamp"};
            result = IOManager.saveAnnotationData(filePath, header, rows);

        } else if (this.type == DataType.DATABASE) {

        }

        return result;
    }

    /**
     *
     * @param userName
     * @param resultType
     * @param entity
     * @param fileType
     * @return
     */
    private String createFilepath(String userName, String resultType, String entity, String fileType) {
        String fileName = entity + "_" + fileType + ".csv";
        String filePath = directory + "/" + userName + "/" + resultType + "/" + fileName;
        return filePath;
    }

    private enum DataType {
        CSV, DATABASE
    }

}
