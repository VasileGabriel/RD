/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.awt.AWTException;
import java.awt.Robot;

/**
 *
 * @author gabriel
 */
public class MyRobot {

    private static Robot robot;

    private MyRobot() throws AWTException{
        
    }

    public static Robot getInstance() {
        if (robot == null) {
            try {
                robot = new Robot();
            } catch (AWTException e) {
                System.out.println("Failed to create ROBOT. "
                        + "You wont be able to use input on server.");
            }
        }
        return robot;
    }
}
