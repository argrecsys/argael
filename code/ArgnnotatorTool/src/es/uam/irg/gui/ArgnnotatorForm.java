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

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * Main GUI class.
 */
public class ArgnnotatorForm extends javax.swing.JFrame {

    // GUI constants
    public static final String HTML_CONTENT_TYPE = "text/html";
    private static final int PROPOSITION_MIN_SIZE = 3;
    private static final boolean NO_USER_CONFIRMATION = true;

    // GUI variables
    private String currDirectory;
    private final DataModel model;
    private final Queue<Integer> acuSelected;
    private String fileExtension;

    /**
     * Creates new form ArgnnotatorForm
     */
    public ArgnnotatorForm() {
        initComponents();

        this.currDirectory = "";
        this.model = new DataModel();
        this.acuSelected = new PriorityQueue<>();
        this.fileExtension = "";

        this.setTablesLookAndFeel();
        this.setComboBoxes();
        this.setVisible(true);
        this.setAnnotatorName();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane1 = new javax.swing.JScrollPane();
        lstFiles = new javax.swing.JList<>();
        scrollPane2 = new javax.swing.JScrollPane();
        textEditor = new javax.swing.JEditorPane();
        scrollPane3 = new javax.swing.JScrollPane();
        tblArgComponents = new javax.swing.JTable();
        scrollPane4 = new javax.swing.JScrollPane();
        tblArgRelations = new javax.swing.JTable();
        lblFileList = new javax.swing.JLabel();
        lblAnnotation = new javax.swing.JLabel();
        lblAddRelation = new javax.swing.JLabel();
        lblDeleteRelation = new javax.swing.JLabel();
        cmbArgCompType = new javax.swing.JComboBox<>();
        btnAddArgument = new javax.swing.JButton();
        cmbCategory = new javax.swing.JComboBox<>();
        cmbIntent = new javax.swing.JComboBox<>();
        btnAddRelation = new javax.swing.JButton();
        btnDeleteRelation = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        mItemImportJsonl = new javax.swing.JMenuItem();
        mItemImportText = new javax.swing.JMenuItem();
        mItemExport = new javax.swing.JMenuItem();
        menuHorzSeparator = new javax.swing.JPopupMenu.Separator();
        mItemClose = new javax.swing.JMenuItem();
        menuHelp = new javax.swing.JMenu();
        mItemAbout = new javax.swing.JMenuItem();
        menuAnnotator = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Argument Annotator Tool v0.5");
        setMinimumSize(new java.awt.Dimension(1060, 500));
        setPreferredSize(new java.awt.Dimension(1300, 650));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lstFiles.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstFiles.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstFilesValueChanged(evt);
            }
        });
        scrollPane1.setViewportView(lstFiles);

        textEditor.setContentType(HTML_CONTENT_TYPE);
        scrollPane2.setViewportView(textEditor);

        tblArgComponents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Text", "Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblArgComponents.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tblArgComponents.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblArgComponentsMouseClicked(evt);
            }
        });
        scrollPane3.setViewportView(tblArgComponents);

        tblArgRelations.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ACU 1", "ACU 2", "Rel Type", "Intent"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblArgRelations.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollPane4.setViewportView(tblArgRelations);

        lblFileList.setText("File list:");

        lblAnnotation.setText("Annotation:");

        lblAddRelation.setText("Add relation:");

        lblDeleteRelation.setText("Delete relation:");

        cmbArgCompType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Major claim", "Claim", "Premise" }));

        btnAddArgument.setText("Add");
        btnAddArgument.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddArgumentActionPerformed(evt);
            }
        });

        cmbCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));

        cmbIntent.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "support", "attack" }));

        btnAddRelation.setText("Add");
        btnAddRelation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddRelationActionPerformed(evt);
            }
        });

        btnDeleteRelation.setText("Delete");
        btnDeleteRelation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteRelationActionPerformed(evt);
            }
        });

        menuFile.setText("File");

        mItemImportJsonl.setText("Import from Jsonl");
        mItemImportJsonl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemImportJsonlActionPerformed(evt);
            }
        });
        menuFile.add(mItemImportJsonl);

        mItemImportText.setText("Import from Text");
        mItemImportText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemImportTextActionPerformed(evt);
            }
        });
        menuFile.add(mItemImportText);

        mItemExport.setText("Export files");
        mItemExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemExportActionPerformed(evt);
            }
        });
        menuFile.add(mItemExport);
        menuFile.add(menuHorzSeparator);

        mItemClose.setText("Close");
        mItemClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemCloseActionPerformed(evt);
            }
        });
        menuFile.add(mItemClose);

        menuBar.add(menuFile);

        menuHelp.setText("Help");

        mItemAbout.setText("About");
        mItemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemAboutActionPerformed(evt);
            }
        });
        menuHelp.add(mItemAbout);

        menuBar.add(menuHelp);

        menuAnnotator.setText("| Annotator:");
        menuBar.add(menuAnnotator);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFileList))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAnnotation)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbArgCompType, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddArgument)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(scrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAddRelation)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbIntent, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddRelation))
                    .addComponent(scrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDeleteRelation)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeleteRelation)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddRelation)
                    .addComponent(lblDeleteRelation)
                    .addComponent(lblFileList)
                    .addComponent(lblAnnotation)
                    .addComponent(cmbArgCompType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddArgument)
                    .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddRelation)
                    .addComponent(cmbIntent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteRelation))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane1)
                    .addComponent(scrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
                    .addComponent(scrollPane4)
                    .addComponent(scrollPane2))
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("Argument Annotator Tool v0.6");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void mItemAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemAboutActionPerformed
        // TODO add your handling code here:
        String aboutMsg = """
                          Argument Annotator Tool
                          
                          Version: 0.7.0
                          Date: 07/18/2022
                          Created by: Andr\u00e9s Segura-Tinoco & Iv\u00e1n Cantador
                          License: Apache License 2.0
                          Web site: https://argrecsys.github.io/arg-nnotator-tool 
                          """;

        JOptionPane.showMessageDialog(this, aboutMsg, "About", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_mItemAboutActionPerformed

    private void mItemCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemCloseActionPerformed
        // TODO add your handling code here:
        closeForm();
    }//GEN-LAST:event_mItemCloseActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        closeForm();
    }//GEN-LAST:event_formWindowClosing

    private void lstFilesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstFilesValueChanged
        // TODO add your handling code here:
        if (!lstFiles.isSelectionEmpty() && !evt.getValueIsAdjusting()) {
            String currFile = lstFiles.getSelectedValue() + "." + fileExtension;
            System.out.println("Selectd file: " + currFile);

            // Query data
            String filepath = currDirectory + "\\" + currFile;
            String result = this.model.getFileReport(filepath, fileExtension);

            // Display report
            this.textEditor.setText(result);
            this.textEditor.setCaretPosition(0);
        }
    }//GEN-LAST:event_lstFilesValueChanged

    private void btnAddArgumentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddArgumentActionPerformed
        // TODO add your handling code here:
        String propText = this.textEditor.getSelectedText();

        if (propText.length() > PROPOSITION_MIN_SIZE) {
            String propType = this.cmbArgCompType.getSelectedItem().toString();
            int propId = this.tblArgComponents.getRowCount() + 1;
            DefaultTableModel tblModel = (DefaultTableModel) this.tblArgComponents.getModel();
            tblModel.addRow(new Object[]{propId, propText, propType});
        }
    }//GEN-LAST:event_btnAddArgumentActionPerformed

    private void btnAddRelationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddRelationActionPerformed
        // TODO add your handling code here:
        if (acuSelected.size() == 2) {
            Integer[] selected = new Integer[2];
            selected = acuSelected.toArray(selected);

            TableModel tblAcuModel = tblArgComponents.getModel();
            int acuId1 = (int) tblAcuModel.getValueAt(selected[0], 0);
            int acuId2 = (int) tblAcuModel.getValueAt(selected[1], 0);
            String category = cmbCategory.getSelectedItem().toString();
            String intent = cmbIntent.getSelectedItem().toString();

            DefaultTableModel tblAcrModel = (DefaultTableModel) tblArgRelations.getModel();
            tblAcrModel.addRow(new Object[]{acuId1, acuId2, category, intent});

            tblArgComponents.clearSelection();
            tblArgRelations.clearSelection();
        }
    }//GEN-LAST:event_btnAddRelationActionPerformed

    private void btnDeleteRelationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteRelationActionPerformed
        // TODO add your handling code here:
        if (tblArgRelations.getRowCount() > 0
                && (NO_USER_CONFIRMATION || JOptionPane.showConfirmDialog(this, "Do you want to remove this relationship?", "Confirmation Dialog", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)) {
            int row = tblArgRelations.getSelectedRow();
            if (row >= 0) {
                ((DefaultTableModel) tblArgRelations.getModel()).removeRow(row);
            }
        }
    }//GEN-LAST:event_btnDeleteRelationActionPerformed

    private void tblArgComponentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblArgComponentsMouseClicked
        // TODO add your handling code here:
        int row = tblArgComponents.rowAtPoint(evt.getPoint());

        if (row >= 0) {
            acuSelected.add(row);
            if (acuSelected.size() > 2) {
                acuSelected.poll();
            }
        }
    }//GEN-LAST:event_tblArgComponentsMouseClicked

    private void mItemImportJsonlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemImportJsonlActionPerformed
        // TODO add your handling code here:
        this.fileExtension = "jsonl";
        importFilesFromDirectory();
    }//GEN-LAST:event_mItemImportJsonlActionPerformed

    private void mItemImportTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemImportTextActionPerformed
        // TODO add your handling code here:
        this.fileExtension = "txt";
        importFilesFromDirectory();
    }//GEN-LAST:event_mItemImportTextActionPerformed

    private void mItemExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemExportActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mItemExportActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddArgument;
    private javax.swing.JButton btnAddRelation;
    private javax.swing.JButton btnDeleteRelation;
    private javax.swing.JComboBox<String> cmbArgCompType;
    private javax.swing.JComboBox<String> cmbCategory;
    private javax.swing.JComboBox<String> cmbIntent;
    private javax.swing.JLabel lblAddRelation;
    private javax.swing.JLabel lblAnnotation;
    private javax.swing.JLabel lblDeleteRelation;
    private javax.swing.JLabel lblFileList;
    private javax.swing.JList<String> lstFiles;
    private javax.swing.JMenuItem mItemAbout;
    private javax.swing.JMenuItem mItemClose;
    private javax.swing.JMenuItem mItemExport;
    private javax.swing.JMenuItem mItemImportJsonl;
    private javax.swing.JMenuItem mItemImportText;
    private javax.swing.JMenu menuAnnotator;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenu menuHelp;
    private javax.swing.JPopupMenu.Separator menuHorzSeparator;
    private javax.swing.JScrollPane scrollPane1;
    private javax.swing.JScrollPane scrollPane2;
    private javax.swing.JScrollPane scrollPane3;
    private javax.swing.JScrollPane scrollPane4;
    private javax.swing.JTable tblArgComponents;
    private javax.swing.JTable tblArgRelations;
    private javax.swing.JEditorPane textEditor;
    // End of variables declaration//GEN-END:variables

    /**
     * Closes winform.
     */
    private void closeForm() {
        this.setVisible(false);
        this.dispose();
        System.exit(0);
    }

    /**
     *
     * @param fileExt
     */
    private void importFilesFromDirectory() {
        JFileChooser jfc = new JFileChooser();
        jfc.setCurrentDirectory(new java.io.File("."));
        jfc.setDialogTitle("Select folder");
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc.setAcceptAllFileFilterUsed(false);

        if (jfc.showOpenDialog(ArgnnotatorForm.this) == JFileChooser.APPROVE_OPTION) {
            currDirectory = jfc.getSelectedFile().toString();
            List<String> files = model.readFilenamesInFolder(currDirectory, fileExtension);
            System.out.println(String.format("Directory: '%s' and number of uploaded files: %d", currDirectory, files.size()));

            lstFiles.removeAll();
            if (files.size() > 0) {
                DefaultListModel listModel = new DefaultListModel();
                listModel.addAll(files);
                lstFiles.setModel(listModel);
            }
        }
    }

    /**
     *
     */
    private void setComboBoxes() {
        List<String> subCategories = model.getSubCategories(true);
        cmbCategory.setModel(new DefaultComboBoxModel<>(subCategories.toArray(new String[0])));
    }

    /**
     *
     */
    private void setTablesLookAndFeel() {

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Table 1: Argument Component Units
        tblArgComponents.getColumnModel().getColumn(0).setPreferredWidth(40);
        tblArgComponents.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblArgComponents.getColumnModel().getColumn(1).setPreferredWidth(210);
        tblArgComponents.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblArgComponents.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        // Table 2: Argument Component Units
        tblArgRelations.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblArgRelations.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblArgRelations.getColumnModel().getColumn(1).setPreferredWidth(50);
        tblArgRelations.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tblArgRelations.getColumnModel().getColumn(2).setPreferredWidth(110);
        tblArgRelations.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblArgRelations.getColumnModel().getColumn(3).setPreferredWidth(90);
        tblArgRelations.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
    }

    /**
     *
     */
    private void setAnnotatorName() {
        String userName = "admin";
        String[] annotators = model.getAnnotatorList();
        String result = (String) JOptionPane.showInputDialog(this, "Please, enter annotator name:", "Annotator Name", JOptionPane.PLAIN_MESSAGE, null, annotators, "");

        if (result != null && result.length() > 0) {
            userName = result;
        }

        this.menuAnnotator.setText("| Annotator: " + userName);
    }

}
