package Networking;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;


class ServerThread extends Thread {

    public void run() {
        while (!Server.serverSocket.isClosed()) {
            try {
                Server.print("Waiting for client");
                Socket clientSocket = Server.serverSocket.accept();
                Server.print("One client accepted");
                SClient nclient = new SClient(clientSocket, Server.client_id);

                Server.client_id++;
                Server.Clients.add(nclient);
                nclient.listenThread.start();
                nclient.pairThread.start();

            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

public class Server {

    public static ServerSocket serverSocket;
    public static int client_id = 0;

    public static int port = 0;

    public static ServerThread main_thread;

    public static ArrayList<SClient> Clients = new ArrayList<>();

    public static Semaphore pairing = new Semaphore(1, true);

    public static void Start(int openport) {
        try {
            Server.port = openport;
            Server.serverSocket = new ServerSocket(Server.port);

            Server.main_thread = new ServerThread();
            Server.main_thread.start();

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void Send(SClient cl, Object msg) {

        try {
            cl.socketOutput.writeObject(msg);
        } catch (IOException ex) {
            Logger.getLogger(SClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public static void print(String msg) {

        System.out.println(msg);

    }



}