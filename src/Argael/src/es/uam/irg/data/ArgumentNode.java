/**
 * Copyright 2024
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

/**
 *
 * @author asegura
 */
public class ArgumentNode {

    private int argumentId;
    private int proposalId;
    private String aspectName;
    private String aspectDescription;
    private String argumentType;
    private String argument;

    public ArgumentNode(int argumentId, int proposalId, String aspectName, String aspectDesc, String argumentType, String argument) {
        this.argumentId = argumentId;
        this.proposalId = proposalId;
        this.aspectName = aspectName;
        this.aspectDescription = aspectDesc;
        this.argumentType = argumentType;
        this.argument = argument;
    }

    public ArgumentNode(String[] data) {
        if (data.length == 6) {
            this.argumentId = Integer.parseInt(data[0]);
            this.proposalId = Integer.parseInt(data[1]);
            this.aspectName = data[2];
            this.aspectDescription = data[3];
            this.argumentType = data[4];
            this.argument = data[5];
        }
    }

    public int getArgumentId() {
        return argumentId;
    }

    public String getArgument() {
        return argument;
    }

    public String getAspectName() {
        return aspectName;
    }

    public String getAspectDesc() {
        return aspectDescription;
    }

    public String getArgumentType() {
        return argumentType;
    }

    public int getProposalId() {
        return proposalId;
    }

    public String getProposalIdString() {
        return Integer.toString(proposalId);
    }

}
