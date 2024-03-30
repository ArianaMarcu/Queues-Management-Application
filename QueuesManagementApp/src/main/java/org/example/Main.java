package org.example;

import BusinessLogic.Input;
import BusinessLogic.SimulationManager;
import GUI.SimulationFrame;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        SimulationManager gen = new SimulationManager("input1.txt", "output1.txt");
        Thread t = new Thread(gen);
        t.start();
        //SimulationFrame frame = new SimulationFrame();

        /*ArrayList<Integer> a = new ArrayList<>();
        SimulationManager gen = new SimulationManager(a);
        Thread t = new Thread(gen);
        t.start();*/

        /*//t.join();
        //Thread thread = Thread.currentThread();
        //thread.join();

        //Input input = new Input("input1.txt");*/
    }
}