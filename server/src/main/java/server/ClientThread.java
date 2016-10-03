/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import common.KeyEventContainer;
import common.Message;
import common.MouseEventContainer;
import java.awt.Robot;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import org.jnativehook.mouse.NativeMouseWheelEvent;

/**
 *
 * @author gabriel
 */
class ClientThread extends Thread {
        // the socket where to listen/talk

        Socket socket;
        ObjectInputStream sInput;
        ObjectOutputStream sOutput;

        InputHandler inputHandler;
        // my unique id (easier for deconnection)
        int id;
        // the Username of the Client
        String username;
        // the only type of message a will receive
        Message cm;
        // the date I connect
        String date;
        Robot robot;

        // Constructore
        ClientThread(Socket socket, Robot robot) {

            // a unique id
            id = 1;
            this.socket = socket;
            this.robot = robot;
            /* Creating both Data Stream */
            System.out.println("Thread trying to create Object Input/Output Streams");
            try {
                // create output first
                sOutput = new ObjectOutputStream(socket.getOutputStream());
                sInput = new ObjectInputStream(socket.getInputStream());

                inputHandler = new InputHandler(robot);
                // read the username
                username = (String) sInput.readObject();
                System.out.println(username + " just connected.");
                new Screenshot(sOutput, robot).start();
            } catch (IOException e) {
                System.out.println("Exception creating new Input/output Streams: " + e);
                return;
            } // have to catch ClassNotFoundException
            // but I read a String, I am sure it will work
            catch (ClassNotFoundException e) {
            }
            date = new Date().toString() + "\n";
        }

        // what will run forever
        @Override
        public void run() {
            // to loop until LOGOUT
            boolean keepGoing = true;
            while (keepGoing) {
                // read a String (which is an object)
                try {
                    cm = (Message) sInput.readObject();
                } catch (IOException e) {
                    System.out.println(username + " Exception reading Streams: " + e);
                    break;
                } catch (ClassNotFoundException e2) {
                    break;
                }
                // the messaage part of the ChatMessage
                String message = cm.getMessage();

                // Switch on the type of message receive
                switch (cm.getType()) {

                    case Message.MESSAGE:
//                        broadcast(username + ": " + message);
                        break;
                    case Message.LOGOUT:
                        System.out.println(username + " disconnected with a LOGOUT message.");
                        keepGoing = false;
                        break;
                    case Message.WHOISIN:
//                        writeMsg("List of the users connected at " + sdf.format(new Date()) + "\n");
                        // scan al the users connected
//                        for (int i = 0; i < al.size(); ++i) {
//                            ClientThread ct = al.get(i);
//                            writeMsg((i + 1) + ") " + ct.username + " since " + ct.date);
//                        }
                        break;
                    case Message.MOUSEEVENT:

                        MouseEventContainer mec = cm.getMec();
                        inputHandler.handleMouseEvent(mec);
                        break;

                    case Message.KEYEVENT:

                        KeyEventContainer kec = cm.getKec();
                        inputHandler.handleKeyEvent(kec);
                        break;

                    case Message.MOUSEWHEELEVENT:

                        NativeMouseWheelEvent mwe = cm.getMWEvent();
                        inputHandler.handleMouseWheelEvent(mwe);
                        break;
                }

            }
            // remove myself from the arrayList containing the list of the
            // connected Clients
//            remove(id);
            close();
        }

        // try to close everything
        private void close() {
            // try to close the connection
            try {
                if (sOutput != null) {
                    sOutput.close();
                }
            } catch (Exception e) {
            }
            try {
                if (sInput != null) {
                    sInput.close();
                }
            } catch (Exception e) {
            };
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (Exception e) {
            }
        }

        /*
		 * Write a String to the Client output stream
         */
        private boolean writeMsg(String msg) {
            // if Client is still connected send the message to it
            if (!socket.isConnected()) {
                close();
                return false;
            }
            // write the message to the stream
            try {
                sOutput.writeObject(msg);
            } // if an error occurs, do not abort just inform the user
            catch (IOException e) {
                System.out.println("Error sending message to " + username);
                System.out.println(e.toString());
            }
            return true;
        }
    }