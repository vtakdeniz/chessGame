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
    ObjectOutputStream sOutput;
    ObjectInputStream sInput;
    Listen listenThread;
    PairingThread pairThread;
    SClient rival;
    public boolean paired = false;

    public SClient(Socket gelenSoket, int id) {
        this.soket = gelenSoket;
        this.id = id;
        try {
            this.sOutput = new ObjectOutputStream(this.soket.getOutputStream());
            this.sInput = new ObjectInputStream(this.soket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(SClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        //thread nesneleri
        this.listenThread = new Listen(this);
        this.pairThread = new PairingThread(this);

    }

    public void Send(Object message) {
        try {
            this.sOutput.writeObject(message);
        } catch (IOException ex) {
            Logger.getLogger(SClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    class Listen extends Thread {

        SClient TheClient;

        Listen(SClient TheClient) {
            this.TheClient = TheClient;
        }

        public void run() {

            while (TheClient.soket.isConnected()) {

                try {
                    Object received = TheClient.sInput.readObject();
                    Server.Send(TheClient.rival,received);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
               /* try {
                    //mesajı bekleyen kod satırı
                    Message received = (Message) (TheClient.sInput.readObject());


                    //mesaj gelirse bu satıra geçer
                    //mesaj tipine göre işlemlere ayır
                    switch (received.type) {
                        case Name:
                            TheClient.name = received.content.toString();
                            // isim verisini gönderdikten sonra eşleştirme işlemine başla
                            TheClient.pairThread.start();
                            break;
                        case Disconnect:
                            break;
                        case Text:
                            //gelen metni direkt rakibe gönder
                            Server.Send(TheClient.rival, received);
                            break;
                        case Selected:
                            //gelen seçim yapıldı mesajını rakibe gönder
                            Server.Send(TheClient.rival, received);
                            break;
                        case Bitis:
                            break;

                    }

                } catch (IOException ex) {
                    Logger.getLogger(SClient.class.getName()).log(Level.SEVERE, null, ex);
                    //client bağlantısı koparsa listeden sil
                    Server.Clients.remove(TheClient);

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(SClient.class.getName()).log(Level.SEVERE, null, ex);
                    //client bağlantısı koparsa listeden sil
                    Server.Clients.remove(TheClient);
                }*/

            }


        }

    }

    class PairingThread extends Thread {

        SClient TheClient;

        PairingThread(SClient TheClient) {
            this.TheClient = TheClient;
        }

        public void run() {
            while (TheClient.soket.isConnected() && TheClient.paired == false) {
                try {
                    Server.pairTwo.acquire(1);
                        SClient crival = null;
                        while (crival == null && TheClient.soket.isConnected()) {
                            for (SClient clnt : Server.Clients) {
                                if (TheClient != clnt && clnt.rival == null) {
                                    crival = clnt;
                                    crival.paired = true;
                                    crival.rival = TheClient;
                                    TheClient.rival = crival;
                                    TheClient.paired = true;
                                    Player p1 = new Player();
                                    Player p2 = new Player();
                                    Random r = new Random();
                                    if (r.nextInt(1)==1){
                                        p1.playerColor= GameColor.WHITE;
                                        p2.playerColor=p1.playerColor.getReverse();
                                    }

                                    p1.rival=p2;
                                    p2.rival=p1;

                                    GameStatus s1 = new GameStatus(GameStatus.Type.User);
                                    s1.content=p1;
                                    Server.Send(TheClient,s1);

                                    GameStatus s2 = new GameStatus(GameStatus.Type.User);
                                    s2.content=p2;
                                    Server.Send(TheClient.rival,s2);

                                    break;
                                }
                            }
                            sleep(1000);

                    }
                    Server.pairTwo.release(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PairingThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
