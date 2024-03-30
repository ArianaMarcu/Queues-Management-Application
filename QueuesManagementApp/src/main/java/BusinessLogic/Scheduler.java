package BusinessLogic;

import Model.Client;
import Model.Server;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Scheduler {
    private List<Server> servers;
    private int maxNoServers;
    private int maxClientsPerServer;
    private Strategy strategy;
    private SelectionPolicy policy;

    public Scheduler(int maxNoServers, int maxClientsPerServer) { //in care coada adaug
        this.maxClientsPerServer = maxClientsPerServer;
        servers = new ArrayList<>();
        this.maxNoServers = maxNoServers;
        for (int i = 0; i < maxNoServers; i++) {
            servers.add(new Server());
        }
        changeStrategy(SelectionPolicy.SHORTEST_TIME);
        //for maxNoServers
        //create server object
        //create thread with the object
    }

    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    public int getMaxNoServers() {
        return maxNoServers;
    }

    public void setMaxNoServers(int maxNoServers) {
        this.maxNoServers = maxNoServers;
    }

    public int getMaxClientsPerServer() {
        return maxClientsPerServer;
    }

    public void setMaxClientsPerServer(int maxClientsPerServer) {
        this.maxClientsPerServer = maxClientsPerServer;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void changeStrategy(SelectionPolicy policy) {
        this.policy = policy;
    }

    public void dispatchClient(Client c) {
        //strategy.addClient(servers, c);
        Server server = minServer();
        int ok = 0;
        if (server.getClienti().size() == 0) {
            ok = 1;
        }
        server.addClient(c);
        if (ok == 1) {
            Thread thread = new Thread(server);
            thread.start();
        }
        //call the strategy addClient method
    }

    public Server minServer() {
        Server min = null;
        if (policy == SelectionPolicy.SHORTEST_QUEUE) {
            Integer nrClienti = Integer.MAX_VALUE;
            for (Server s : servers) {
                if (s.getClienti().size() < nrClienti) {
                    min = s;
                    nrClienti = s.getClienti().size();
                }
            }
        }
        if (policy == SelectionPolicy.SHORTEST_TIME) {
            AtomicInteger min1 = new AtomicInteger(Integer.MAX_VALUE);
            for (Server server : servers) {
                if (server.getWaitingPeriod().intValue() < min1.intValue()) {
                    min1 = server.getWaitingPeriod();
                    min = server;
                }
            }
        }
        return min;
    }

    public void displayServers(FileWriter fileWriter) {
        try {
            for (Server s : servers) {
                System.out.println("Queue: " + (servers.indexOf(s)+1) + " " + s.toString());
                fileWriter.write("\nQueue: " + (servers.indexOf(s) + 1) + " " + s.toString());
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
