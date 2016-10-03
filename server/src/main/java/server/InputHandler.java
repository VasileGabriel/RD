package server;

import common.KeyEventContainer;
import common.MouseEventContainer;
import java.awt.Robot;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseWheelEvent;

public class InputHandler {

    private Robot robot;

    InputHandler(Robot robot) {
        this.robot = robot;
    }

    public void handleMouseEvent(MouseEventContainer mec) {
        NativeMouseEvent e = mec.getEvent();

        if (mec.type == MouseEventContainer.PRESSEDEVENT) {
            handleMousePressedEvent(e);
//			handleMovedEvent(e);
        } else if (mec.type == MouseEventContainer.RELEASEDEVENT) {
            handleMouseReleasedEvent(e);
        } else if (mec.type == MouseEventContainer.MOVEDEVENT) {
            handleMouseMovedEvent(e);
        } else if (mec.type == MouseEventContainer.DRAGGEDEVENT) {
            handleMouseDraggedEvent(e);
        }
    }

    public void handleKeyEvent(KeyEventContainer kec) {
        NativeKeyEvent e = kec.getEvent();

        if (kec.type == KeyEventContainer.PRESSEDEVENT) {
            handleKeyPressedEvent(e);
        } else if (kec.type == KeyEventContainer.RELEASEDEVENT) {
            handleKeyReleasedEvent(e);
        }
    }

    public void handleMouseWheelEvent(NativeMouseWheelEvent e) {
        System.out.println(e);
        // robot.mouseWheel(e.getWheelRotation());
    }

    private void handleMousePressedEvent(NativeMouseEvent e) {
        if (e.getButton() == NativeMouseEvent.BUTTON1) {
            // robot.mousePress(InputEvent.BUTTON1_MASK);
        }
        if (e.getButton() == NativeMouseEvent.BUTTON2) {
            // robot.mousePress(InputEvent.BUTTON2_MASK);
        }
    }

    private void handleMouseReleasedEvent(NativeMouseEvent e) {
        if (e.getButton() == NativeMouseEvent.BUTTON1) {
            // robot.mouseRelease(InputEvent.BUTTON1_MASK);
        }
        if (e.getButton() == NativeMouseEvent.BUTTON2) {
            // robot.mouseRelease(InputEvent.BUTTON2_MASK);
        }
    }

    private void handleMouseMovedEvent(NativeMouseEvent e) {
        // robot.mouseMove(e.getX(), e.getY());
    }

    private void handleMouseDraggedEvent(NativeMouseEvent e) {
        handleMouseMovedEvent(e);
    }

    private void handleKeyPressedEvent(NativeKeyEvent e) {
        // robot.keyPress(e.getRawCode());
    }

    private void handleKeyReleasedEvent(NativeKeyEvent e) {
        // robot.keyRelease(e.getRawCode());
    }

}
