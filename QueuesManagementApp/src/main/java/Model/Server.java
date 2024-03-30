package Model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
    private BlockingQueue<Client> clienti;
    private AtomicInteger waitingPeriod;
    public Server(){
        clienti = new LinkedBlockingQueue<>();
        waitingPeriod = new AtomicInteger(0); //la inceput coada e goala

        //initialize queue and waitingPeriod
    }
    public void addClient(Client client)
    {
        clienti.add(client);
        waitingPeriod.addAndGet(client.getServiceTime());
        //add client to queue
        //increment the waitingPeriod
    }

    public BlockingQueue<Client> getClienti() {
        return clienti;
    }

    public void setClienti(BlockingQueue<Client> clienti) {
        this.clienti = clienti;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(AtomicInteger waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    public void run() {
        while(!clienti.isEmpty()){
            try {
                Thread.sleep(1000);
                setWaitingPeriod(new AtomicInteger(waitingPeriod.intValue()-1));
                Client client1 = clienti.peek();//primul care e in coada
                synchronized (client1){
                    client1.setServiceTime(client1.getServiceTime()-1);
                };
                if(client1.getServiceTime() == 0) //a fost servit
                {
                    clienti.remove(client1);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //take next client from queue
            //stop the thread for a time equal with the client's processing time
            //decrement the waitingPeriod
        }
    }

    @Override
    public String toString() {
        String rezultat = "";
        for(Client c : clienti){
            rezultat += c.toString();
        }
        if(rezultat.isEmpty()){
            rezultat = "closed";
        }
        return rezultat;
    }

    public Client[] getClients(){
        return new Client[0];
    }
}
