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

import es.uam.irg.utils.FileUtils;
import es.uam.irg.utils.StringUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/**
 * Helper for loading application input parameters.
 */
public class InitParams {

    private final static String FILE_PATH = "Resources/config/params.json";

    /**
     *
     * @return
     */
    public static Map<String, Object> readInitParams() {
        Map<String, Object> params = new HashMap<>();
        String jsonText = FileUtils.readFile(FILE_PATH);

        if (!StringUtils.isEmpty(jsonText)) {
            JSONObject json = new JSONObject(jsonText);

            if (!json.isEmpty()) {
                JSONObject data;

                // General parameters
                data = json.getJSONObject("data");
                Map<String, Object> documents = new HashMap<>();
                documents.put("source", data.getString("source"));
                documents.put("fileExtension", data.getString("fileExtension"));
                documents.put("language", data.getString("language"));
                documents.put("result", data.getString("result"));
                params.put("data", documents);

                // Annotation model parameters
                data = json.getJSONObject("annotation_model");
                Map<String, Object> annotationModel = new HashMap<>();
                annotationModel.put("components", data.getJSONArray("components").toList());
                annotationModel.put("relation_categories", data.getJSONArray("relation_categories").toList());
                annotationModel.put("relation_intents", data.getJSONArray("relation_intents").toList());
                params.put("annotation_model", annotationModel);

                // Evaluation model parameters
                data = json.getJSONObject("evaluation_model");
                Map<String, Object> evaluationModel = new HashMap<>();
                evaluationModel.put("quality", data.getJSONArray("quality").toList());
                params.put("evaluation_model", evaluationModel);
            }
        }

        return params;
    }

}
