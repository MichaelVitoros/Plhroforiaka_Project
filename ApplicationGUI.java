import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class ApplicationGUI extends JFrame {
	
	private static final long serialVersionUID = -7101537812878958022L;
	
	private JFrame frame = new JFrame("League of Legends App");//Creates a JFrame window with "STRING" as its name
	private JButton jButton = new JButton();	
	private double screenWidth;
	private double screenHeight;
	
	private RiotApiConnector myApiConnector = new RiotApiConnector();

	public ApplicationGUI(){
		
		setScreenHeight();
		setScreenWidth();
		
		frame.setSize(1000,600);
		//frame.setLocation( (int) getScreenWidth()/4, (int) getScreenHeight()/4 );
		frame.setLocationRelativeTo(null);//Sets the location of the window on the center of the screen if set null
		
		frame.setLayout(new GridBagLayout());
	
		jButton = callForSummonerName();
		
		frame.add(jButton);		
		//frame.pack();
		frame.setVisible(true);//The window is shown
		frame.setResizable(false);//The window cannot be resized by the User
		
	}
	
	private JButton callForSummonerName(){
		jButton.setText("Press me");
		jButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(" was pressed.");
				try {
					myApiConnector.myFunction();
				}catch (InterruptedException e1) {
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
