package Networking;

import Players.Player;
import Util.GameColor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SClient {

    int id;
    Socket soket;
    ObjectOutputStream socketOutput;
    ObjectInputStream socketInput;
    Listen listenThread;
    PairingThread pairThread;
    SClient adversary;
    public boolean paired = false;

    public SClient(Socket gelenSoket, int id) {
        this.soket = gelenSoket;
        this.id = id;
        try {
            this.socketOutput = new ObjectOutputStream(this.soket.getOutputStream());
            this.socketInput = new ObjectInputStream(this.soket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(SClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.listenThread = new Listen(this);
        this.pairThread = new PairingThread(this);

    }

    class Listen extends Thread {

        SClient currentClient;

        Listen(SClient currentClient) {
            this.currentClient = currentClient;
        }

        public void run() {

            while (currentClient.soket.isConnected()) {

                try {
                    Object received = currentClient.socketInput.readObject();
                    Server.Send(currentClient.adversary,received);

                } catch (IOException e) {
                    currentClient.pairThread.stop();
                    Server.Clients.remove(currentClient);
                    e.printStackTrace();
                    break;
                } catch (ClassNotFoundException e) {
                    currentClient.pairThread.stop();
                    Server.Clients.remove(currentClient);
                    e.printStackTrace();
                    break;
                }

            }

        }

    }

    class PairingThread extends Thread {

        SClient currentClient;

        PairingThread(SClient currentClient) {
            this.currentClient = currentClient;
        }

        public void run() {
            while (currentClient.soket.isConnected() && currentClient.paired == false) {
                try {
                    Server.pairing.acquire(1);
                        SClient crival = null;
                        while (crival == null && currentClient.soket.isConnected()) {
                            for (SClient clnt : Server.Clients) {
                                if (currentClient != clnt && clnt.adversary == null) {
                                    crival = clnt;
                                    crival.paired = true;
                                    crival.adversary = currentClient;
                                    currentClient.adversary = crival;
                                    currentClient.paired = true;
                                    Player p1 = new Player();
                                    Player p2 = new Player();
                                    Random r = new Random();
                                    int luck=r.nextInt(3);
                                    System.out.println(luck);
                                    if (luck==1){
                                        p1.playerColor= GameColor.WHITE;
                                        p2.playerColor=p1.playerColor.getReverse();
                                    }
                                    else{
                                        p1.playerColor= GameColor.BLACK;
                                        p2.playerColor=p1.playerColor.getReverse();
                                    }

                                    p1.rival=p2;
                                    p2.rival=p1;

                                    GameStatus s1 = new GameStatus(GameStatus.Type.User);
                                    s1.content=p1;
                                    Server.Send(currentClient,s1);

                                    GameStatus s2 = new GameStatus(GameStatus.Type.User);
                                    s2.content=p2;
                                    Server.Send(currentClient.adversary,s2);

                                    break;
                                }
                            }
                            sleep(1000);

                    }
                    Server.pairing.release(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PairingThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
