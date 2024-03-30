package BusinessLogic;

import GUI.SimulationFrame;
import Model.Client;
import Model.Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class SimulationManager implements Runnable {
    //data read from UI
    public Input input;
    public int timeLimit = 100; //maximum processing time - read from UI
    public int maxProcessingTime = 10;
    public int minProcessingTime = 10;
    public int numberOfServers = 3;
    public int numberOfClients = 100;
    public SelectionPolicy selP = SelectionPolicy.SHORTEST_TIME;
    //entity responsible with queue management and client distribution
    private final Scheduler scheduler;
    //frame for displaying simulation
    private SimulationFrame frame;
    //pool of tasks(client shopping in the store)
    private List<Client> generatedClients;

    public SimulationManager(String fileName, String fileOutputName) {
        input = new Input(fileName, fileOutputName);
        generateRandomClients();
        System.out.println(generatedClients);
        scheduler = new Scheduler(input.getNrQueues(), input.getNrClients());
    }

    /*public SimulationManager(ArrayList<Integer> array) {
        input = new Input(array);
        generateRandomClients();
        System.out.println(generatedClients);
        scheduler = new Scheduler(input.getNrQueues(), input.getNrClients());
    }*/

    private void generateRandomClients() {
        Random random = new Random();
        generatedClients = new ArrayList<>();
        for (int i = 0; i < input.getNrClients(); i++) {
            generatedClients.add(new Client(i + 1,
                    Math.abs(random.nextInt(input.getMinArrivalTime(), input.getMaxArrivalTime() + 1)),
                    Math.abs(random.nextInt(input.getMinServiceTime(), input.getMaxServiceTime() + 1))));
        }
    }

    @Override
    public void run() {
        int currentTime = 0;
        int sum = 0;
        for (Client c : generatedClients)
            sum += c.getServiceTime();

        while (true) {
            try {
                input.getFileWriter().write("\n\nTime = " + currentTime);

                System.out.println("Time " + currentTime);

                Iterator<Client> clientIterator = generatedClients.iterator();
                Client currentClient;
                while (clientIterator.hasNext()) {
                    currentClient = clientIterator.next();
                    if (currentClient.getArrivalTime() == currentTime) {
                        scheduler.dispatchClient(currentClient);
                        clientIterator.remove();

                    }
                }
                printClients();
                scheduler.displayServers(input.getFileWriter());

                if (generatedClients.isEmpty()) {
                    int NR = 0;
                    for (Server s : scheduler.getServers()) {
                        if (s.getClienti().isEmpty()) {
                            NR++;
                        }
                    }
                    float media;
                    if (input.getNrQueues() == NR) {
                        media = (float) sum / input.getNrClients();
                        System.out.println(media);
                        input.getFileWriter().write("\nAverage waiting time: " + media);
                        break;
                    }
                }
                Thread.sleep(1000);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            currentTime++;
        }
        try {
            input.getFileWriter().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printClients() {
        System.out.print("Waiting clients: ");
        try {
            input.getFileWriter().write("\nWaiting clients: ");
            for (Client c : generatedClients) {
                System.out.print(c);
                input.getFileWriter().write(c.toString());
            }
            System.out.println();
            input.getFileWriter().write("\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
