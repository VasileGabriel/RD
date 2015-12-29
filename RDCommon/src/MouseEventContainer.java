import java.io.Serializable;

import org.jnativehook.mouse.NativeMouseEvent;


public class MouseEventContainer  implements Serializable {

	public int type;
	NativeMouseEvent event;
	protected static final long serialVersionUID = 1112122200L;

	static final int PRESSEDEVENT = 0, 
			RELEASEDEVENT = 1,
			MOVEDEVENT = 2;
	
	MouseEventContainer(int type, NativeMouseEvent e){
		this.type = type;
		this.event = e;
	}
	
	public NativeMouseEvent getEvent(){
		return event;
	}
}
