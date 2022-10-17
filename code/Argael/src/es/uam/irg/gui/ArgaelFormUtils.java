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
import java.util.Queue;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Usuario
 */
public class ArgaelFormUtils {

    private static final int PROPOSITION_MIN_SIZE = 3;

    /**
     *
     * @param row
     * @param table
     * @param acModel
     * @param arModel
     * @return
     */
    public static String createArgumentRelationString(int row, JTable table, TableModel acModel, TableModel arModel) {
        // Collect relation data
        String text = "";

        if (row >= 0) {
            int acId1 = Integer.parseInt(arModel.getValueAt(row, 1).toString());
            int acId2 = Integer.parseInt(arModel.getValueAt(row, 2).toString());
            String category = arModel.getValueAt(row, 3).toString();
            String intent = arModel.getValueAt(row, 4).toString();
            int acIndex1 = ArgaelFormUtils.getAcIndexFromTable(acModel, acId1, 0);
            int acIndex2 = ArgaelFormUtils.getAcIndexFromTable(acModel, acId2, 0);

            // Show relation
            if (acIndex1 >= 0 && acIndex2 >= 0) {
                String acText1 = acModel.getValueAt(acIndex1, 1).toString();
                String acType1 = acModel.getValueAt(acIndex1, 2).toString();
                String acText2 = acModel.getValueAt(acIndex2, 1).toString();
                String acType2 = acModel.getValueAt(acIndex2, 2).toString();
                text = String.format("[<b>%s (%s)</b>: %s] \u2190 [<b>%s (%s)</b>: %s] (<b>relation</b>: \"%s\" and \"%s\")", acType1, acId1, acText1, acType2, acId2, acText2, category, intent);
                ArgaelFormUtils.selectMultipleTableRows(table, acIndex1, acIndex2);
            }
        }

        return text;
    }

    /**
     *
     * @param editor
     * @param cmbACType
     * @param acTable
     * @return
     */
    public static boolean createNewArgumentComponent(javax.swing.JEditorPane editor, javax.swing.JComboBox<String> cmbACType, javax.swing.JTable acTable) {
        boolean result = false;

        String propText = editor.getSelectedText().trim();
        if (propText != null) {
            propText = propText.trim();
            String propType = cmbACType.getSelectedItem().toString();

            if (propText.length() > PROPOSITION_MIN_SIZE && !propType.equals("-")) {

                // Add new argument component
                int propId = getNextPropositionId(acTable);
                Object[] newRow = new Object[]{propId, propText, propType};
                ((DefaultTableModel) acTable.getModel()).addRow(newRow);

                result = true;
            }
        }

        return result;
    }

    /**
     *
     * @param acSelected
     * @param acTable
     * @param cmbCategory
     * @param cmbIntent
     * @param arTable
     * @return
     */
    public static boolean createNewArgumentRelation(Queue<Integer> acSelected, javax.swing.JTable acTable, javax.swing.JComboBox<String> cmbCategory, javax.swing.JComboBox<String> cmbIntent, javax.swing.JTable arTable) {
        boolean result = false;

        Integer[] selected = new Integer[2];
        selected = acSelected.toArray(selected);

        TableModel acModel = acTable.getModel();
        int acId1 = Integer.parseInt(acModel.getValueAt(selected[0], 0).toString());
        int acId2 = Integer.parseInt(acModel.getValueAt(selected[1], 0).toString());

        if (cmbCategory.getSelectedIndex() > 0 && cmbIntent.getSelectedIndex() > 0) {
            int arId = getNextRelationId(arTable);
            String category = cmbCategory.getSelectedItem().toString();
            String intent = cmbIntent.getSelectedItem().toString();

            Object[] newRow = new Object[]{arId, acId1, acId2, category, intent};
            ((DefaultTableModel) arTable.getModel()).addRow(newRow);

            acTable.clearSelection();
            arTable.clearSelection();
            acSelected.clear();

            result = true;
        }

        return result;
    }

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
     * @return
     */
    public static DefaultTableModel getTableModel(JTable table) {
        if (table != null) {
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            tableModel.setRowCount(0);
            return tableModel;
        }
        return null;
    }

    /**
     * Load table data: arguments annotations and evaluations.
     *
     * @param tableModel
     * @param annotations
     * @param evaluations
     * @param nColumns
     * @throws Exception
     */
    public static void loadArgTableData(DefaultTableModel tableModel, List<String[]> annotations, Map<Integer, String> evaluations, int nColumns) throws Exception {
        if (tableModel != null && annotations != null) {
            for (int i = 0; i < annotations.size(); i++) {
                String[] rowData = annotations.get(i);
                int rowId = Integer.parseInt(rowData[0]);

                tableModel.addRow(FunctionUtils.getSubArray(rowData, 0, nColumns));
                if (evaluations != null && evaluations.containsKey(rowId)) {
                    tableModel.setValueAt(evaluations.get(rowId), i, nColumns);
                }
            }
        }
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
     * @param cmbTargetAnnotator
     * @param strList
     */
    public static void setComboBoxModel(JComboBox<String> cmbTargetAnnotator, List<String> strList) {
        cmbTargetAnnotator.setModel(new DefaultComboBoxModel(strList.toArray(new String[0])));
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

    /**
     *
     * @return
     */
    private static int getNextPropositionId(javax.swing.JTable acTable) {
        int propNextId = 1;
        int nRows = acTable.getRowCount();
        if (nRows > 0) {
            propNextId = Integer.parseInt(acTable.getModel().getValueAt(nRows - 1, 0).toString()) + 1;
        }
        return propNextId;
    }

    /**
     *
     * @return
     */
    private static int getNextRelationId(javax.swing.JTable arTable) {
        int propNextId = 1;
        int nRows = arTable.getRowCount();
        if (nRows > 0) {
            propNextId = Integer.parseInt(arTable.getModel().getValueAt(nRows - 1, 0).toString()) + 1;
        }
        return propNextId;
    }

}
