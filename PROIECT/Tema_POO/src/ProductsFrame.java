import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.table.*;

public class ProductsFrame extends JFrame implements ActionListener {
	JButton addButton, searchButton, deleteButton, editButton;
	DefaultTableModel tableModel = new DefaultTableModel();
	JTable table;
	Gestiune gestiune = Gestiune.getInstance("produse.txt", "taxe.txt", "facturi.txt");
	Vector<String> country = new Vector<>();
	Vector<String> category = new Vector<>();
	Vector<String> name = new Vector<>();
	JTextField denumire = new JTextField();
	JTextField categorie = new JTextField();
	JTextField tara = new JTextField();
	JTextField pret = new JTextField();

	Object[] productFields = { "Denumire Produs", denumire, "Categorie Produs", categorie, "Tara", tara, "Pret", pret };

	public ProductsFrame(String titlu) {
		super(titlu);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(700, 370));

		JLabel title_label = new JLabel("Gestiune Produse");
		title_label.setFont(new Font("Roboto", Font.BOLD, 20));
		title_label.setHorizontalTextPosition(JLabel.CENTER);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(0, 95, 20, 0);
		add(title_label, c);

		addButton = new JButton("Adauga produs");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0, 0, 20, 10);
		addButton.addActionListener(this);
		add(addButton, c);

		deleteButton = new JButton("Sterge produs");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(0, 10, 20, 10);
		deleteButton.addActionListener(this);
		add(deleteButton, c);

		searchButton = new JButton("Cauta produs");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		c.insets = new Insets(0, 10, 20, 10);
		searchButton.addActionListener(this);
		add(searchButton, c);

		editButton = new JButton("Editeaza produs");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 1;
		c.insets = new Insets(0, 10, 20, 0);
		editButton.addActionListener(this);
		add(editButton, c);

		String[] columnNames = { "Denumire", "Categorie", "Tara de Origine", "Pret" };

		table = new JTable();
		table.setPreferredScrollableViewportSize(new Dimension(500, 110));
		table.setFillsViewportHeight(true);
		tableModel.setColumnIdentifiers(columnNames);
		table.setModel(tableModel);
		Object[] datarow = new Object[4];
		for (int i = 0; i < gestiune.produse.size(); i++) {
			Produs p = gestiune.produse.get(i);
			datarow[0] = p.getDenumire();
			datarow[1] = p.getCategorie();
			datarow[2] = p.getTaraOrigine();
			datarow[3] = p.getPret();
			tableModel.addRow(datarow);
			if(country.contains(p.getTaraOrigine()) != true)
				country.add(p.getTaraOrigine());
			if(category.contains(p.getCategorie()) != true)
				category.add(p.getCategorie());
			if(name.contains(p.getDenumire()) != true)
				name.add(p.getDenumire());
		}
		table.setAutoCreateRowSorter(true);
		JScrollPane scrollPane = new JScrollPane(table);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 4;
		c.insets = new Insets(0, 0, 0, 0);
		add(scrollPane, c);

		pack();
		setResizable(false);
		getContentPane().setBackground(Color.decode("#81d4fa"));
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addButton)
			addProduct();
		else if (e.getSource() == deleteButton) {
			if (table.getSelectedRowCount() == 1) {
				int i = table.getSelectedRow();
				String denum, categ, tara, pretProd;
				Object prod;
				double pretP;
				Produs p = null;
				denum = (String) table.getValueAt(i, 0);
				categ = (String) table.getValueAt(i, 1);
				tara = (String) table.getValueAt(i, 2);
				prod = table.getValueAt(i, 3);
				pretProd = prod.toString();
				pretP = Double.parseDouble(pretProd);
				p = new Produs(denum, categ, tara, pretP);
				gestiune.produse.remove(p);
				tableModel.removeRow(table.convertRowIndexToModel(i));
				appendFile("produse.txt");
				//table.removeRowSelectionInterval(0, table.getRowCount() - 1);
			}
			else if(table.getSelectedRowCount() == 0)
				JOptionPane.showMessageDialog(this, "Nu a fost selectat vreun produs");
			else {
				JOptionPane.showMessageDialog(this, "Ati selectat prea multe linii");
				table.removeRowSelectionInterval(0, table.getRowCount() - 1);
			}
		} 
		else if (e.getSource() == editButton) {
			if (table.getSelectedRowCount() == 1) {
				Produs p, q;
				double pretP;
				String denum, categ, tara, pretProd;
				int i = table.getSelectedRow(), j;
				p = giveProduct();
				if(p == null)
					JOptionPane.showMessageDialog(this, "Caracteristicile specificate pentru produs sunt invalide");
				else {
					denum = (String) table.getValueAt(i, 0);
					categ = (String) table.getValueAt(i, 1);
					tara = (String) table.getValueAt(i, 2);
					pretP = (Double) table.getValueAt(i, 3);
					q = new Produs(denum, categ, tara, pretP);
					if(p.getDenumire() != null)
						tableModel.setValueAt(p.getDenumire(), i, 0);
					else
						p.setDenumire(denum);
					if(p.getPret() != 0)
						tableModel.setValueAt(p.getPret(), i, 3);
					else
						p.setPret(pretP);
					p.setCategorie(categ);
					p.setTaraOrigine(tara);
					j = gestiune.produse.indexOf(q);
					if(j > -1) {
						gestiune.produse.remove(j);
						gestiune.produse.add(j, p);
						appendFile("produse.txt");
						table.removeRowSelectionInterval(0, table.getRowCount() - 1);
					}
				}
			}
			else if(table.getSelectedRowCount() == 0)
				JOptionPane.showMessageDialog(this, "Nu a fost selectat vreun produs");
			else {
				JOptionPane.showMessageDialog(this, "Ati selectat prea multe linii");
				table.removeRowSelectionInterval(0, table.getRowCount() - 1);
			}
		}
		else if (e.getSource() == searchButton)
			searchProduct();
	}

	public void addProduct() {
		int i, option = JOptionPane.showConfirmDialog(null, productFields, "Adauga produs", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			if (denumire.getText().length() != 0 && categorie.getText().length() != 0 && tara.getText().length() != 0
					&& pret.getText().length() != 0) {
				double pretProdus = Double.parseDouble(pret.getText());
				Produs p = new Produs(denumire.getText(), categorie.getText(), tara.getText(), pretProdus);
				if (gestiune.produse.contains(p))
					JOptionPane.showMessageDialog(this, "Produsul " + denumire.getText() + " exista deja!");
				else if(category.contains(p.getCategorie()) != true)
					JOptionPane.showMessageDialog(this, "Categoria " + p.getCategorie() + " este invalida!");
				else {
					for(i = 0; i < gestiune.produse.size(); i++) {
						Produs q = gestiune.produse.get(i);
						if(q.getDenumire().equals(p.getDenumire()) && q.getCategorie().equals(p.getCategorie()) 
								&& q.getTaraOrigine().equals(p.getTaraOrigine())) {
							JOptionPane.showMessageDialog(this, "Produsul " + denumire.getText() + " exista deja!");
							denumire.setText(null);
							categorie.setText(null);
							tara.setText(null);
							pret.setText(null);
							return;
						}
					}
					
					Object[] row = new Object[4];
					row[0] = denumire.getText();
					row[1] = categorie.getText();
					row[2] = tara.getText();
					row[3] = pret.getText();
					tableModel.addRow(row);
					gestiune.produse.add(p);
					if(country.contains(p.getTaraOrigine()) != true)
						country.add(p.getTaraOrigine());
					appendFile("produse.txt");
				}
				denumire.setText(null);
				categorie.setText(null);
				tara.setText(null);
				pret.setText(null);
			}
			else
				JOptionPane.showMessageDialog(this, "Nu au fost adaugate toate caracteristicile produsului!");
		}
	}

	public Produs giveProduct() {
		Produs p = null;
		Object[] productFieldsEdit = { "Denumire Produs", denumire, "Pret", pret };
		String denum;
		double pretP;
		int option = JOptionPane.showConfirmDialog(null, productFieldsEdit, "Caracteristici produs", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			if(denumire.getText().length() == 0 && pret.getText().length() == 0) {
				JOptionPane.showMessageDialog(this, "Nu a fost adaugata vreo caracteristica a produsului!");
				return p;
			}
			else {
				if (denumire.getText().length() != 0)
					denum = denumire.getText();
				else
					denum = null;
				if(pret.getText().length() != 0)
					pretP = Double.parseDouble(pret.getText());
				else
					pretP = 0;
				p = new Produs(denum, null, null, pretP);
			}
		}
		denumire.setText(null);
		categorie.setText(null);
		tara.setText(null);
		pret.setText(null);
		return p;
	}
	
	public void searchProduct() {
		ArrayList<Integer> produseCautate;
		String denum, categ, taraProdus, pretP;
		int option = JOptionPane.showConfirmDialog(null, productFields, "Cauta produs", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			if(denumire.getText().length() != 0)
				denum = denumire.getText();
			else
				denum = null;
			if(categorie.getText().length() != 0)
				categ = categorie.getText();
			else
				categ = null;
			if(tara.getText().length() != 0)
				taraProdus = tara.getText();
			else
				taraProdus = null;
			if(pret.getText().length() != 0)
				pretP = pret.getText();
			else
				pretP = null;
			produseCautate = searchCriteria(denum, categ, taraProdus, pretP);
			if(produseCautate == null)
				JOptionPane.showMessageDialog(this, "Produsul nu exista in lista!");
			else {
				table.removeRowSelectionInterval(0, table.getRowCount() - 1);
				for(int i = 0; i < produseCautate.size(); i++) {
					table.addRowSelectionInterval(produseCautate.get(i), produseCautate.get(i));
				}
			}
		}
	}
	
	public ArrayList<Integer> searchCriteria(String denumire, String categorie, String tara, String pret) {
		ArrayList<Integer> produseSelectate = new ArrayList<>();
		int criteriiValide = 0, criteriiProduseValide;
		if(denumire != null)
			criteriiValide++;
		if(categorie != null)
			criteriiValide++;
		if(tara != null)
			criteriiValide++;
		if(pret != null)
			criteriiValide++;
		for(int i = 0; i < table.getRowCount() - 1; i++) {
			criteriiProduseValide = 0;
			if(denumire != null && table.getValueAt(i,  0).equals(denumire))
				criteriiProduseValide++;
			if(categorie != null && table.getValueAt(i, 1).equals(categorie))
				criteriiProduseValide++;
			if(tara != null && table.getValueAt(i, 2).equals(tara))
				criteriiProduseValide++;
			if(pret != null && table.getValueAt(i, 3).toString().equals(pret))
				criteriiProduseValide++;
			if(criteriiValide == criteriiProduseValide)
				produseSelectate.add(i);	
		}
		if(produseSelectate.isEmpty())
			return null;
		return produseSelectate;
	}
	
	public void appendFile(String fisier) {
		FileWriter file1 = null, file2 = null;
		BufferedWriter bw1 = null, bw2 = null;
		ArrayList<String> produseFolosite = new ArrayList<>();
		try {
			file1 = new FileWriter(fisier, false);
			bw1 = new BufferedWriter(file1);
			bw1.write("");
			bw1.close();
			file1.close();
			file2 = new FileWriter(fisier, true);
			bw2 = new BufferedWriter(file2);
			String str = "";
			str = str + "Produs Categorie ";
			for(String tara : country)
				str += tara + " ";
			bw2.write(str);
			bw2.newLine();
			for(Produs p : gestiune.produse) {
				str = "";
				if(produseFolosite.contains(p.getDenumire()) != true) {
					str += p.getDenumire() + " " + p.getCategorie() + " ";
					for(String tara : country) {
						double pret = cautareProdus(p.getDenumire(), tara);
						str += pret + " ";
					}
					bw2.write(str);
					bw2.newLine();
				}
				produseFolosite.add(p.getDenumire());
			}
			bw2.close();
			file2.close();
		}
		catch(IOException e) {
			System.out.println("Eroare la citire");
		}
	}
	
	public double cautareProdus(String denumire, String tara) {
		double pret = 0;
		for(Produs p : gestiune.produse) {
			if(p.getDenumire().equals(denumire) && p.getTaraOrigine().equals(tara)) {
				pret = p.getPret();
				break;
			}
		}
		return pret;
	}
}
