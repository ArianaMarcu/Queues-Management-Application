package BusinessLogic;

import Model.Client;
import Model.Server;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcreteStrategyTime implements Strategy {
    public void addClient(List<Server> servers, Client c) {
        Server s = getMinTimeServer(servers);
        s.addClient(c);
    }
    public Server getMinTimeServer(List<Server> servers){
        AtomicInteger min = new AtomicInteger(Integer.MAX_VALUE);
        Server minServer = null;
        for(Server server : servers){
            if(server.getWaitingPeriod().intValue() < min.intValue()) {
                min = server.getWaitingPeriod();
                minServer = server;
            }
        }
        return minServer;
    }
}
