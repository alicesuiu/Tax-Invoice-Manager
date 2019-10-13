import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;

public class StartPage extends JFrame implements KeyListener {
	JPasswordField parola_field;
	JTextField nume_area;
	
	public StartPage(String titlu) {
		super(titlu);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(600, 350));
		
		JLabel background1=new JLabel(new ImageIcon("snowfall.gif"));
		add(background1);
		background1.setLayout(new GridBagLayout());
		
		JLabel title_label = new JLabel("Sistem de facturi fiscale");
		title_label.setFont(new Font("Roboto", Font.BOLD, 20));
		title_label.setForeground(Color.white);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(10, 0, 10, 0);
		background1.add(title_label, c);
		
		JLabel subtitlu = new JLabel("Autentificare");
		subtitlu.setFont(new Font("Roboto", Font.BOLD, 18));
		subtitlu.setForeground(Color.white);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(10, 40, 10, 0);
		background1.add(subtitlu, c);
		
		
		JLabel nume = new JLabel("Nume:");
		nume.setFont(new Font("Roboto", Font.BOLD, 15));
		nume.setForeground(Color.white);
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 2;
		background1.add(nume, c);
		
		nume_area = new JTextField();
		nume_area.setBackground(Color.LIGHT_GRAY);
		nume_area.setFont(new Font("Roboto", Font.BOLD, 12));
		nume_area.addKeyListener(this);
		c.ipadx = 150;
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(10, 5, 10, 0);
		background1.add(nume_area, c);
		
		JLabel parola = new JLabel("Parola:");
		parola.setFont(new Font("Roboto", Font.BOLD, 15));
		parola.setForeground(Color.white);
		c.ipadx = 0;
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(10, 40, 10, 0);
		background1.add(parola, c);
		
		parola_field = new JPasswordField();
		parola_field.setBackground(Color.LIGHT_GRAY);
		parola_field.addKeyListener(this);
		c.ipadx = 150;
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(10, 5, 10, 0);
		background1.add(parola_field, c);
		
		JButton enter = new JButton("Enter");
		c.ipadx = 0;
		c.anchor = GridBagConstraints.LAST_LINE_END;
		c.gridx = 2;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkCredentials();
			}
		});
		background1.add(enter, c);
		getContentPane().setBackground(Color.decode("#81d4fa"));
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
			checkCredentials();
	}

	public void keyReleased(KeyEvent e) {
		
	}
	
	public void keyTyped(KeyEvent e) {
		
	}
	
	public void checkCredentials() {
		char[] password = parola_field.getPassword();
		String pass = new String(password); 
		if(nume_area.getText().equals("alice.suiu") && pass.equals("florenta")) {
			setVisible(false);
			new LinkedPage("Pagina de gestiune");
		}
			
		else {
			JOptionPane.showMessageDialog(null, "Nume sau parola invalida");
			nume_area.setText(null);
			parola_field.setText(null);
		}
	}
}
