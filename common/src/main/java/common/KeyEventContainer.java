package common;

import java.io.Serializable;
import org.jnativehook.keyboard.NativeKeyEvent;

public class KeyEventContainer implements Serializable {

    public int type;
    NativeKeyEvent event;
    protected static final long serialVersionUID = 1112122200L;

    public static final int PRESSEDEVENT = 0,
            RELEASEDEVENT = 1;

    public KeyEventContainer(int type, NativeKeyEvent e) {
        this.type = type;
        this.event = e;
    }

    public NativeKeyEvent getEvent() {
        return event;
    }
}
