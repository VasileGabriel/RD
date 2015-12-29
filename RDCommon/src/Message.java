import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO; 
import java.io.ByteArrayOutputStream;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseWheelEvent;

/*
 * This class defines the different type of messages that will be exchanged between the
 * Clients and the Server. 
 * When talking from a Java Client to a Java Server a lot easier to pass Java objects, no 
 * need to count bytes or to wait for a line feed at the end of the frame
 */
public class Message implements Serializable {

	protected static final long serialVersionUID = 1112122200L;

	// The different types of message sent by the Client
	// WHOISIN to receive the list of the users connected
	// MESSAGE an ordinary message
	// LOGOUT to disconnect from the Server
	static final int WHOISIN = 0, 
			MESSAGE = 1,
			LOGOUT = 2,
			MOUSEEVENT = 3,
			MOUSEWHEELEVENT = 4,
			KEYEVENT = 5,
			IMG = 6;
	private int type;
	private String message;
	private MouseEventContainer mec;
	private NativeMouseWheelEvent mwEvent;
	private NativeKeyEvent kEvent;
	byte[] imgInByte;
	
	// constructor
//	Message(int type, String message, NativeMouseEvent mEvent, NativeKeyEvent kEvent,BufferedImage img) {
//		this.type = type;
//		this.message = message;
//		this.mEvent = mEvent;
//		this.kEvent = kEvent;
//		
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		try {
//			ImageIO.write(img, "jpg", baos);
//		
//		baos.flush();
//		this.imgInByte = baos.toByteArray();
//		baos.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}	


	// constructor
	Message(int type, String message) {
		this.type = type;
		this.message = message;
	}	
	// constructor
	Message(int type, MouseEventContainer mec) {
		this.type = type;
		this.mec = mec;
	}
	// constructor
	Message(int type, NativeMouseWheelEvent mwEvent) {
		this.type = type;
		this.mwEvent = mwEvent;
	}
	// constructor
	Message(int type, NativeKeyEvent kEvent) {
		this.type = type;
		this.kEvent = kEvent;
	}	
	// constructor
	Message(int type, BufferedImage img) {
		this.type = type;
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(img, "jpg", baos);
		
		baos.flush();
		this.imgInByte = baos.toByteArray();
		baos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	// getters
	int getType() {
		return type;
	}
	String getMessage() {
		return message;
	}
	MouseEventContainer getMec() {
		return mec;
	}
	NativeMouseWheelEvent getMWEvent() {
		return mwEvent;
	}
	NativeKeyEvent getKEvent() {
		return kEvent;
	}
	BufferedImage getImg() {
		InputStream in = new ByteArrayInputStream(imgInByte);
		try {
			return ImageIO.read(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}