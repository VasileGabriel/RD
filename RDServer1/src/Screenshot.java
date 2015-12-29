import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.Toolkit;


public class Screenshot extends Thread
	{
		private ObjectOutputStream sOutput;
		private Robot robot;
		
		Screenshot(ObjectOutputStream sOutput, Robot robot)
		{
//			this.name = name;
			this.sOutput = sOutput;
			this.robot = robot;
		}
		
		public void run ()
		{
			
			while (true){
				Rectangle rect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
				BufferedImage img = robot.createScreenCapture(rect);
				Message imgMessage = new Message(Message.IMG, img);

				try {
					sOutput.writeObject(imgMessage);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}