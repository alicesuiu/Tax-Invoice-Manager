import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class LinkedPage extends JFrame implements ActionListener{
	JButton page1, page2, page3;
	public LinkedPage(String titlu) {
		super(titlu);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		setPreferredSize(new Dimension(900, 600));
		
		JLabel background1=new JLabel(new ImageIcon("sistem.jpg"));
		add(background1);
		background1.setLayout(new GridBagLayout());
		
		page1 = new JButton("Incarcare fisiere cu date");
		c.anchor = GridBagConstraints.PAGE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 50, 600);
		page1.addActionListener(this);
		background1.add(page1, c);
		
		page2 = new JButton("Gestiunea produselor");
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(50, 0, 0, 600);
		page2.addActionListener(this);
		background1.add(page2, c);
		
		page3 = new JButton("Statistica magazinelor");
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(100, 0, 0, 600);
		page3.addActionListener(this);
		background1.add(page3, c);
		
		
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == page1)
			new Load_Create_Files("Incarcare fisiere");
		else if(e.getSource() == page2)
			new ProductsFrame("Afisare si administrare produse");
		else if(e.getSource() == page3)
			new StatisticFrame("Statistica magazine");
	}
	
}
