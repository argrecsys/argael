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

import javax.swing.JEditorPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Usuario
 */
public class ArgaelFormUtils {

    /**
     *
     * @param model
     * @param acId
     * @param acIdIx
     * @return
     */
    public static int getAcIndexFromTable(TableModel model, int acId, int acIdIx) {
        int ix = -1;
        for (int i = 0; i < model.getRowCount() && ix == -1; i++) {
            if (acId == Integer.parseInt(model.getValueAt(i, acIdIx).toString())) {
                ix = i;
            }
        }
        return ix;
    }

    /**
     *
     * @param table
     * @param index1
     * @param index2
     */
    public static void selectMultipleTableRows(JTable table, int index1, int index2) {
        ListSelectionModel tblModel = table.getSelectionModel();
        tblModel.clearSelection();
        tblModel.addSelectionInterval(index1, index1);
        tblModel.addSelectionInterval(index2, index2);
    }

    /**
     *
     * @param editor
     * @param content
     */
    public static void updateEditorContent(JEditorPane editor, String content) {
        int caretPosition = editor.getCaretPosition();
        editor.setText(content);
        editor.setCaretPosition(caretPosition);
    }

}
