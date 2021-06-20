/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mode;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ngoc
 */
public class TableMode extends DefaultTableModel {
    
    public TableMode() {
        super();
        this.addColumn("Họ_Tên");
        this.addColumn("Quê_Quán");
    }
    
    @Override
    public boolean isCellEditable(int row, int column)
    {
        return false;
    }
    
    @Override
    public Class getColumnClass(int column) {
        switch (column) {
            case 0:
                return String.class;
            case 1:
                return String.class;                      
            default:
                return String.class;
        }
    }
}
