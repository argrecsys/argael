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
package es.uam.irg.struct;

import java.util.List;

/**
 * Argument annotator tool data model class.
 */
public class ArgumentModel {

    private final List<String> argComponents;
    private final List<String> qualityMetrics;
    private final List<String> relCategories;
    private final List<String> relIntents;

    /**
     * Data model constructor.
     *
     * @param argComponents (e.g., claim, premise, major claim)
     * @param relCategories (e.g., addition, conclusion, summary)
     * @param relIntents (e.g., support, attack)
     * @param qualityMetrics (e.g., incorrect, not relevant, relevant)
     */
    public ArgumentModel(List<String> argComponents, List<String> relCategories, List<String> relIntents, List<String> qualityMetrics) {
        this.argComponents = argComponents;
        this.relCategories = relCategories;
        this.relIntents = relIntents;
        this.qualityMetrics = qualityMetrics;
    }

    /**
     *
     * @return
     */
    public List<String> getArgumentComponents() {
        return this.argComponents;
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

}
