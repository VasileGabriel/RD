package common;

import java.io.Serializable;
import org.jnativehook.mouse.NativeMouseEvent;

public class MouseEventContainer implements Serializable {

    public int type;
    NativeMouseEvent event;
    protected static final long serialVersionUID = 1112122200L;

    public static final int PRESSEDEVENT = 0,
            RELEASEDEVENT = 1,
            MOVEDEVENT = 2,
            DRAGGEDEVENT = 3;

    public MouseEventContainer(int type, NativeMouseEvent e) {
        this.type = type;
        this.event = e;
    }

    public NativeMouseEvent getEvent() {
        return event;
    }
}
