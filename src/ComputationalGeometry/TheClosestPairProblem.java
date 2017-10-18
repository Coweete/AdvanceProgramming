package ComputationalGeometry;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class TheClosestPairProblem {

    private Scanner scanner;

    public static void main(String[] args) {
        TheClosestPairProblem problem = new TheClosestPairProblem();

        try {
            problem.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void start() throws IOException{
        scanner = new Scanner(System.in);
        String[] input;
        int nbrOfNodes;

        nbrOfNodes = Integer.parseInt(scanner.nextLine());
        while (true){
            ArrayList<Pair> pairs = createNodes(nbrOfNodes);
            printArray(pairs);



            nbrOfNodes = Integer.parseInt(scanner.nextLine());
            if (nbrOfNodes == 0){
                break;
            }
        }


    }

    private void closestPair(ArrayList<Pair> pairs){
        ArrayList<Pair>  sortedByX = new ArrayList<>(pairs);
        Collections.sort(sortedByX, Comparator.comparing(Pair::getX));
        ArrayList<Pair> sortedByY = new ArrayList<>(pairs);
        Collections.sort(sortedByY, Comparator.comparing(Pair::getY));
        cps(sortedByX,sortedByY);
    }

    private void cps(ArrayList<Pair> sX,ArrayList<Pair> sY){
        if(sX.size() <= 3){
            //Solve by bruteforce
        }else{
            int n = sX.size() / 2;
            ArrayList<Pair> xL = new ArrayList<>(sX.subList(0,n));
            ArrayList<Pair> xR = new ArrayList<>(sX.subList(n+1,sX.size()-1));

        }
    }

    private double dist(Pair p1,Pair p2){
        return Math.sqrt(((Math.pow((p1.x - p2.x),2)) + (Math.pow((p1.y - p2.y),2))));
    }

    public ArrayList<Pair> createNodes(int numberofnodes){
        ArrayList<Pair> pairs = new ArrayList<>();
        String[] input;
        for (int i = 0; i < numberofnodes; i++) {
            input = scanner.nextLine().split(" ");
            pairs.add(new Pair(Double.parseDouble(input[0]),Double.parseDouble(input[1])));
        }
        return pairs;
    }

    private void printArray(ArrayList<Pair> pairs){
        for (int i = 0; i < pairs.size(); i++) {
            System.out.println("X:" + pairs.get(i).x + ",Y:" + pairs.get(i).y);
        }
    }


    private class Pair {
        double x,y;

        public Pair(double x, double y){
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }
    }
}
