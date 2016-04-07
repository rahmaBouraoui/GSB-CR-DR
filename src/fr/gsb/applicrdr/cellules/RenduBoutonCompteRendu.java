package fr.gsb.applicrdr.cellules;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class RenduBoutonCompteRendu extends JButton implements TableCellRenderer{

	public RenduBoutonCompteRendu() {
		super() ;
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		if(value == null){
			this.setText("");
		}
		else {
			this.setText(value.toString());
		}
		
//		this.setText("Sélectionner");
		
		return this ;
	}
	
	

}
