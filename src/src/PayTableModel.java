package src;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import java.util.Vector;

class PayTableModel extends AbstractTableModel {
	private String headName[];
    private Vector content = null;

	public PayTableModel() {
		super();
	}

	public PayTableModel(String[] headName, Vector content) {
		this();
		this.headName = headName;
		this.content = content;
	}

	public int getColumnCount() {
		return headName.length;
	}


    public Object getValueAt(int row, int col) {
        return ((Vector) content.get(row)).get(col);
    }
	
    public void setValueAt(Object value, int row, int col) {
        ((Vector) content.get(row)).remove(col);
        ((Vector) content.get(row)).add(col, value);
        this.fireTableCellUpdated(row, col);
    }
    
    public void addRow(Vector v) {
        content.add(v);
    }
    
    public void removeRow(int row) {
        content.remove(row);
    }
 
    public void removeRows(int row, int count) {
        for (int i = 0; i < count; i++) {
            if (content.size() > row) {
                content.remove(row);
            }
        }
    }
 
 
    public int getRowCount() {
        return content.size();
    }
    

	public String getColumnName(int c) {
		return headName[c];
	}

	
    public Class getColumnClass(int col) {
        return getValueAt(0, col).getClass();
    }

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		 if (columnIndex == 0 ||columnIndex == 1 || columnIndex == 2
		 ||columnIndex == 3 || columnIndex == 4) {
			return false;
		}
		return true;
	}

}