import java.io.IOException;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class HDFrame extends JFrame {
	
	private static final int FRAME_WIDTH = 800;
	private static final int FRAME_HEIGHT = 1000;
	
	private JFrame frame = new JFrame("Hamming Distance");
	
	public HDFrame() throws IOException {
	
		frame.add(new HDPanel());
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
	
	public static void main(String[] args) throws IOException {
		new HDFrame();
	}
	
}

