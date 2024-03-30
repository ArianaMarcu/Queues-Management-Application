package BusinessLogic;

import Model.Client;
import Model.Server;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcreteStrategyQueue implements Strategy {

    @Override
    public void addClient(List<Server> servers, Client c) {
        Server minS = getMinServer(servers);
        minS.addClient(c);
    }
    public Server getMinServer(List<Server> servers){
        Server min = null;
        Integer nrClienti = Integer.MAX_VALUE;
        for(Server s : servers){
            if(s.getClients().length < nrClienti){
                min = s;
                nrClienti = s.getClients().length;
            }
        }
        return min;
    }
}
