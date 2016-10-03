package client;


import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseWheelEvent;
import org.jnativehook.mouse.NativeMouseWheelListener;

public class GlobalMouseWheelListener implements NativeMouseWheelListener {

    private ObjectOutputStream sOutput;

    GlobalMouseWheelListener(ObjectOutputStream sOutput) {
        this.sOutput = sOutput;
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
    }

    @Override
    public void nativeMouseWheelMoved(NativeMouseWheelEvent e) {
        System.out.println("Mosue Wheel Moved: " + e.getWheelRotation());
    }

    public void init() {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        // Construct the example object.
        GlobalMouseWheelListener example = new GlobalMouseWheelListener(sOutput);

        // Add the appropriate listeners.
        GlobalScreen.addNativeMouseWheelListener(example);
    }
}
