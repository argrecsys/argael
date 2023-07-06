/**
 * Copyright 2023
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

import java.util.ArrayList;
import java.util.List;

/**
 * Tree node class.
 */
public class TreeNode {

    private final String value;
    private final List<TreeNode> children;
    private final int depth;

    public TreeNode(String value, int depth) {
        this.value = value;
        this.depth = depth;
        this.children = new ArrayList<>();
    }

    public String getValue() {
        return value;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }

    public int getDepth() {
        return depth;
    }
}
