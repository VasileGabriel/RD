package common;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import org.jnativehook.keyboard.NativeKeyEvent;
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
    public static final int WHOISIN = 0,
            MESSAGE = 1,
            LOGOUT = 2,
            MOUSEEVENT = 3,
            MOUSEWHEELEVENT = 4,
            KEYEVENT = 5,
            IMG = 6;
    private int type;
    private String message;
    private MouseEventContainer mec;
    private KeyEventContainer kec;
    private NativeMouseWheelEvent mwEvent;
    private NativeKeyEvent kEvent;
    byte[] imgInByte;

    // constructor
    public Message(int type, String message) {
        this.type = type;
        this.message = message;
    }
    // constructor

    public Message(int type, MouseEventContainer mec) {
        this.type = type;
        this.mec = mec;
    }
    // constructor

    public Message(int type, KeyEventContainer kec) {
        this.type = type;
        this.kec = kec;
    }
    // constructor

    public Message(int type, NativeMouseWheelEvent mwEvent) {
        this.type = type;
        this.mwEvent = mwEvent;
    }
    // constructor

    public Message(int type, NativeKeyEvent kEvent) {
        this.type = type;
        this.kEvent = kEvent;
    }
    // constructor

    public Message(int type, BufferedImage img) {
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
    public int getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public MouseEventContainer getMec() {
        return mec;
    }

    public KeyEventContainer getKec() {
        return kec;
    }

    public NativeMouseWheelEvent getMWEvent() {
        return mwEvent;
    }

    public BufferedImage getImg() {
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
