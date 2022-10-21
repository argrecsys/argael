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

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 */
public class SelectedItems {

    private final Queue<Integer> items;

    /**
     * Constructor.
     */
    public SelectedItems() {
        items = new LinkedList<>();
    }

    /**
     * *
     *
     * @param value
     */
    public void addItem(int value) {
        items.add(value);
        if (items.size() > 2) {
            items.poll();
        }
    }

    /**
     *
     * @param values
     */
    public void addItems(int[] values) {
        for (int value : values) {
            addItem(value);
        }
    }

    /**
     * *
     *
     */
    public void clear() {
        items.clear();
    }

    /**
     *
     * @param value
     * @return
     */
    public boolean contains(int value) {
        return items.contains(value);
    }

    /**
     *
     * @return
     */
    public Integer[] getValues() {
        Integer[] indexes = new Integer[2];
        int i = 0;
        for (int value : items) {
            indexes[i++] = value;
        }
        return indexes;
    }

    /**
     *
     * @return
     */
    public int size() {
        return items.size();
    }

}
