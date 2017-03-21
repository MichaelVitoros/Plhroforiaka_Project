import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;



public class ApplicationGUI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7101537812878958022L;
	
	private JFrame frame = new JFrame();
	private JButton jButton = new JButton();
	private double screenWidth;
	private double screenHeight;
	
	private RiotApiConnector myApiConnector = new RiotApiConnector();

	public ApplicationGUI(){
		
		setScreenHeight();
		setScreenWidth();
		
		frame.setTitle("League of Legends App");
		frame.setSize(760,366);
		frame.setLocation( (int) getScreenWidth()/4, (int) getScreenHeight()/4 );
		
		jButton = callForSummonerName();
		
		frame.add(jButton);		
		
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBackground(Color.black);
		
	}
	
	private JButton callForSummonerName(){
		jButton.setText("Press me");
		
		jButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(" was pressed.");
				try {
					myApiConnector.myFunction();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		return jButton;
	}
	
	private double setScreenHeight(){
		screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		return screenHeight;
	}
	
	
	private double setScreenWidth(){
		screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		return screenWidth;
	}

	public double getScreenWidth() {
		return screenWidth;
	}


	public double getScreenHeight() {
		return screenHeight;
	}

}
