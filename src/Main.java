import javax.swing.JFrame;

public class Main {

		private JFrame frame;
		private Screen s;
		
		private Main() {
			frame = new JFrame();
			s = new Screen();
			s.setFocusable(true);
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(true);
			frame.add(s);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}
		
		public static void main(String[] args) {
			new Main();
		}
	
	
	
}