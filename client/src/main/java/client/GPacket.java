package client;


import java.awt.image.BufferedImage;
import java.io.Serializable;

/*
 * This class defines the different type of messages that will be exchanged between the
 * Clients and the Server. 
 * When talking from a Java Client to a Java Server a lot easier to pass Java objects, no 
 * need to count bytes or to wait for a line feed at the end of the frame
 */
public class GPacket implements Serializable {

    protected static final long serialVersionUID = 1112122200L;

    static final int IMAGE = 0, DIFF = 1;

    private int type;
    private BufferedImage image;

    // constructor
    GPacket(int type, BufferedImage image) {
        this.type = type;
        this.image = image;
    }

    // getters
    BufferedImage getImage() {
        return image;
    }

    int getType() {
        return type;
    }
}
