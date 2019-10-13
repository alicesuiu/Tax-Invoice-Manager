import javax.swing.UIManager;

public class BeginApp {
	public static void main ( String args []) throws Exception{	
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) {
			System.out.println("Eroare! Libraria nu a fost gasita!");
		} finally {
			new StartPage("Aplicatie");
			}
		}
}
