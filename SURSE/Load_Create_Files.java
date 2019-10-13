import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class Load_Create_Files extends JFrame implements ActionListener{
	JButton facturiButton, taxeButton, produseButton, outputButton;
	 JFileChooser fc;
	 File facturiFile = null, taxeFile = null, produseFile = null, outputFile = null;
	 int ok = 0;
	 Gestiune gestiune;
	
	public Load_Create_Files(String titlu) {
		super(titlu);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		fc = new JFileChooser();
		facturiButton = new JButton("Alege fisierul pentru facturi");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		facturiButton.addActionListener(this);
		add(facturiButton, c);
		
		taxeButton = new JButton("Alege fisierul pentru taxe");
		c.gridx = 1;
		c.gridy = 0;
		taxeButton.addActionListener(this);
		add(taxeButton, c);
		
		produseButton = new JButton("Alege fisierul pentru produse");
		c.gridx = 0;
		c.gridy = 1;
		produseButton.addActionListener(this);
		add(produseButton, c);
		
		outputButton = new JButton("Alege directorul unde se va salva fisierul out.txt");
		c.gridx = 1;
		c.gridy = 1;
		outputButton.addActionListener(this);
		add(outputButton, c);
		
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == facturiButton) {
			int returnVal = fc.showOpenDialog(Load_Create_Files.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				if(fc.getSelectedFile().getName().equals("facturi.txt")) {
                	facturiFile = fc.getSelectedFile();
                	ok++;
				}
				else
					JOptionPane.showMessageDialog(null, "Fisierul ales este incorect");
			}
		}
		else if(e.getSource() == taxeButton) {
			int returnVal = fc.showOpenDialog(Load_Create_Files.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				if(fc.getSelectedFile().getName().equals("taxe.txt")) {
                	taxeFile = fc.getSelectedFile();
                	ok++;
				}
				else
					JOptionPane.showMessageDialog(null, "Fisierul ales este incorect");
			}
		}
		else if(e.getSource() == produseButton) {
			int returnVal = fc.showOpenDialog(Load_Create_Files.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				if(fc.getSelectedFile().getName().equals("produse.txt")) {
                	produseFile = fc.getSelectedFile();
                	ok++;
				}
				else
					JOptionPane.showMessageDialog(null, "Fisierul ales este incorect");
			}
		}
		else if(e.getSource() == outputButton) {
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int returnVal = fc.showOpenDialog(Load_Create_Files.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
                	outputFile = fc.getSelectedFile();
                	ok++;
			}
		}
		if(ok == 4) {
			String fisier = outputFile.getPath() + "\\out.txt";
			gestiune = Gestiune.getInstance(produseFile.getName(), taxeFile.getName(), facturiFile.getName());
			GenerateOutput object = new GenerateOutput();
			object.createLists(gestiune, fisier);
			object.afisare(object.mini_market, "MiniMarket", gestiune, fisier);
			object.afisare(object.medium_market, "MediumMarket", gestiune, fisier);
			object.afisare(object.hyper_market, "HyperMarket", gestiune, fisier);
		}
	}
}