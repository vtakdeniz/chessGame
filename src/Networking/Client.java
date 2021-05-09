package Networking;

import Board.Board;
import Players.Player;
import Util.Move;
import gui.Table;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


class Listen extends Thread {

    public void run() {
        while (Client.socket.isConnected()) {
            try {
                GameStatus received = (GameStatus)(Client.socketInput.readObject());
                switch (received.type) {
                    case Move:
                        Move m = (Move)(received.content);
                        Table.executeRivalMove(m);
                        break;
                    case User:
                        Board b = new Board();
                        Table t = new Table(b);
                        Player p = (Player)(received.content);
                        Table.setPlayer(p);

                        break;
                    case Disconnect:
                        break;
                    case Board:
                        break;

                }

            } catch (IOException ex) {

                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                Client.terminate();
                break;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                Client.terminate();
                break;
            }
        }

    }
}

public class Client {

    public static ObjectInputStream socketInput;
    public static ObjectOutputStream socketOutput;
    public static Socket socket;
    public static Listen listenMe;

    public static void Start(String ip, int port) {
        try {
            Client.socket = new Socket(ip, port);
            Client.print("Connected");
            Client.socketInput = new ObjectInputStream(Client.socket.getInputStream());
            Client.socketOutput = new ObjectOutputStream(Client.socket.getOutputStream());
            Client.listenMe = new Listen();
            Client.listenMe.start();

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void terminate() {
        try {
            if (Client.socket != null) {
                Client.listenMe.stop();
                Client.socket.close();
                Client.socketOutput.flush();
                Client.socketOutput.close();

                Client.socketInput.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public static void Send(Object msg) {
        try {
            Client.socketOutput.writeObject(msg);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public static void print(String msg) {

        System.out.println(msg);

    }



}