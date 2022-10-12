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

import es.uam.irg.utils.FunctionUtils;
import java.util.List;
import java.util.Map;

/**
 * Program main class.
 */
public class Argael {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        // Program hyperparameters from JSON config file
        Map<String, Object> params = InitParams.readInitParams();
        Map<String, String> data = (Map<String, String>) params.get("data");
        String dataFolder = data.get("folder");
        String fileExtension = data.get("extension");
        String lang = data.get("language");
        Map<String, List<String>> annotationModel = (Map<String, List<String>>) params.get("annotation_model");
        List<String> components = annotationModel.get("components");
        List<String> relCategories = annotationModel.get("relation_categories");
        List<String> relIntents = annotationModel.get("relation_intents");
        Map<String, List<String>> evaluationModel = (Map<String, List<String>>) params.get("evaluation_model");
        List<String> qualityMetrics = evaluationModel.get("quality");
        System.out.format(">> Data folder: %s, File extension: %s, document language: %s\n", dataFolder, fileExtension, lang);
        System.out.format(">> Relation Categories: %s, Relation Intents: %s, Quality metrics: %s\n", components, relCategories, relIntents, qualityMetrics);

        // Show ARGAEL gui
        showWinform(dataFolder, fileExtension, components, relCategories, relIntents, qualityMetrics);
    }

    /**
     * Creates and displays the Argument-IR form.
     *
     * @param dataFolder
     * @param fileExtension
     * @param components
     * @param relCategories
     * @param relIntents
     * @param qualityMetrics
     */
    private static void showWinform(String dataFolder, String fileExtension, List<String> components, List<String> relCategories, List<String> relIntents, List<String> qualityMetrics) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ArgaelForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            FunctionUtils.printWithDatestamp(">> ARG-TOOL BEGINS");
            new ArgaelForm(dataFolder, fileExtension, components, relCategories, relIntents, qualityMetrics);
            FunctionUtils.printWithDatestamp(">> ARG-TOOL ENDS");
        });
    }

}
