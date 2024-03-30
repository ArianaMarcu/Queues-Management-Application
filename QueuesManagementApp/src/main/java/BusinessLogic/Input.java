package BusinessLogic;

import GUI.SimulationFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class Input {
    public Integer getNrClients() {
        return nrClients;
    }

    public void setNrClients(Integer nrClients) {
        this.nrClients = nrClients;
    }

    public Integer getNrQueues() {
        return nrQueues;
    }

    public void setNrQueues(Integer nrQueues) {
        this.nrQueues = nrQueues;
    }

    public Integer gettSimulation() {
        return tSimulation;
    }

    public void settSimulation(Integer tSimulation) {
        this.tSimulation = tSimulation;
    }

    public Integer getMinArrivalTime() {
        return minArrivalTime;
    }

    public void setMinArrivalTime(Integer minArrivalTime) {
        this.minArrivalTime = minArrivalTime;
    }

    public Integer getMaxArrivalTime() {
        return maxArrivalTime;
    }

    public void setMaxArrivalTime(Integer maxArrivalTime) {
        this.maxArrivalTime = maxArrivalTime;
    }

    public Integer getMinServiceTime() {
        return minServiceTime;
    }

    public void setMinServiceTime(Integer minServiceTime) {
        this.minServiceTime = minServiceTime;
    }

    public Integer getMaxServiceTime() {
        return maxServiceTime;
    }

    public void setMaxServiceTime(Integer maxServiceTime) {
        this.maxServiceTime = maxServiceTime;
    }

    private Integer nrClients;
    private Integer nrQueues;
    private Integer tSimulation;
    private Integer minArrivalTime;
    private Integer maxArrivalTime;
    private Integer minServiceTime;
    private Integer maxServiceTime;

    public FileWriter getFileWriter() {
        return fileWriter;
    }

    private FileWriter fileWriter;

    public Input(String fileName, String fileOutput){
        try {
            String currentDir = System.getProperty("user.dir") + "\\src\\main\\java\\inputs";
            fileWriter = new FileWriter(currentDir + "\\" + fileOutput);
            ArrayList<Integer> array = new ArrayList<Integer>();
            File file = new File(currentDir + "\\" + fileName);
            BufferedReader buff = new BufferedReader(new FileReader(file));
            String line;
            while ((line = buff.readLine()) != null) {
                line = line.replace(" ", "");
                if (line.contains(",")) {
                    String[] str = line.split(",");
                    array.add(Integer.parseInt(str[0]));
                    array.add(Integer.parseInt(str[1]));
                } else {
                    array.add(Integer.parseInt(line));
                }
            }
            this.nrClients = array.get(0);
            this.nrQueues = array.get(1);
            this.tSimulation = array.get(2);
            this.minArrivalTime = array.get(3);
            this.maxArrivalTime = array.get(4);
            this.minServiceTime = array.get(5);
            this.maxServiceTime = array.get(6);
            //System.out.println(line);
            ///array.add
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    /*public Input(ArrayList<Integer> arrayList)
    {
        try {
            SimulationFrame simulationFrame = new SimulationFrame();
            arrayList = simulationFrame.array;
            this.nrClients = arrayList.get(0);
            this.nrQueues = arrayList.get(1);
            this.tSimulation = arrayList.get(2);
            this.minArrivalTime = arrayList.get(3);
            this.maxArrivalTime = arrayList.get(4);
            this.minServiceTime = arrayList.get(5);
            this.maxServiceTime = arrayList.get(6);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }*/
}
