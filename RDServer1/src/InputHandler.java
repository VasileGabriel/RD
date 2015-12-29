import java.awt.Robot;
import java.awt.event.InputEvent;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseWheelEvent;


public class InputHandler {
	
	private Robot robot;
	
	InputHandler(Robot robot)
	{
		this.robot = robot;
	}
	
	
	public void handleMouseEvent(MouseEventContainer mec) {
		NativeMouseEvent e = mec.getEvent();
		
		if (mec.type == MouseEventContainer.PRESSEDEVENT) {
			handlePressedEvent(e);
		} else if (mec.type == MouseEventContainer.RELEASEDEVENT) {
			handleReleasedEvent(e);
		} else if (mec.type == MouseEventContainer.MOVEDEVENT) {
			handleMovedEvent(e);
		}
		
	}

	
	public void handleKeyEvent(NativeKeyEvent e) {
		System.out.println(e);
		robot.keyPress(e.getRawCode());
		robot.keyRelease(e.getRawCode());
	}
	public void handleMouseWheelEvent(NativeMouseWheelEvent e) {
		System.out.println(e);
		robot.mouseWheel(e.getWheelRotation());
	}
	
	
	
	
	
	private void handlePressedEvent(NativeMouseEvent e) {
		if (e.getButton() == NativeMouseEvent.BUTTON1) {
			robot.mousePress(InputEvent.BUTTON1_MASK);
			return ;
		}
		if (e.getButton() == NativeMouseEvent.BUTTON2) {
			robot.mousePress(InputEvent.BUTTON2_MASK);
			return ;
		}
	}
	private void handleReleasedEvent(NativeMouseEvent e) {
		if (e.getButton() == NativeMouseEvent.BUTTON1) {
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			return ;
		}
		if (e.getButton() == NativeMouseEvent.BUTTON2) {
			robot.mouseRelease(InputEvent.BUTTON2_MASK);
			return ;
		}
	}
	private void handleMovedEvent(NativeMouseEvent e) {
		robot.mouseMove(e.getX(), e.getY());
	}

}
