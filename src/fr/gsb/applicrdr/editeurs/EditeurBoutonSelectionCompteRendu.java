package fr.gsb.applicrdr.editeurs;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

import fr.gsb.applicrdr.controleurs.ControleurBoutonSelectionCompteRendu;
import fr.gsb.applicrdr.vues.VueGsb;

public class EditeurBoutonSelectionCompteRendu extends DefaultCellEditor {
	
	

	protected JButton bouton ;
	private boolean isPushed ;
	private ControleurBoutonSelectionCompteRendu controleur ;
	
	
	public EditeurBoutonSelectionCompteRendu(JCheckBox cb, VueGsb vueMam) {
		super(cb) ;
		this.bouton = new JButton() ;
		this.bouton.setOpaque(true);
		this.controleur = new ControleurBoutonSelectionCompteRendu(vueMam) ;
		this.bouton.addActionListener(this.controleur) ;
	}
	
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		// TODO Auto-generated method stub
//		return super.getTableCellEditorComponent(table, value, isSelected, row, column);
//		super.getTableCellEditorComponent(table, value, isSelected, row, column);
		this.controleur.setTable(table);
		this.controleur.setRow(row) ;
		this.controleur.setColumn(column);
		this.controleur.setBouton(bouton) ;
		
		if(value == null){
			this.bouton.setText("") ;
		}
		else {
			this.bouton.setText(value.toString()) ;
		}
		
		return this.bouton ;
		
		
	}
	

}
