package ComputationalGeometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TheClosestPairProblemBrute {

    private BufferedReader reader;

    public static void main(String[] args) {
        TheClosestPairProblemBrute brute = new TheClosestPairProblemBrute();
        try {
            brute.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input;
        int nbrOfNodes;
        nbrOfNodes = Integer.parseInt(reader.readLine());
        while (true) {
            ArrayList<Pair> pairs = createNodes(nbrOfNodes);
            closestPair(pairs);
            nbrOfNodes = Integer.parseInt(reader.readLine());

            if (nbrOfNodes == 0) {
                break;
            }
        }
    }

    private void closestPair(ArrayList<Pair> pairs){
        double max = 10000,dist;
        for (int i = 0; i < pairs.size(); i++) {
            for (int j = i+1; j < pairs.size(); j++) {
                dist = calc(pairs.get(i),pairs.get(j));
                if (dist <= max){
                    max = dist;
                }
            }
        }
        if (max == 10000){
            System.out.println("INFINITY");
        }else {
            System.out.println(String.format("%.4f",max));
        }
    }

    private double calc(Pair p1, Pair p2){
        return  Math.sqrt(((Math.pow((p1.x - p2.x),2)) + (Math.pow((p1.y - p2.y),2))));
    }

    public ArrayList<Pair> createNodes(int numberofnodes) throws IOException {
        ArrayList<Pair> pairs = new ArrayList<>();
        String[] input;
        for (int i = 0; i < numberofnodes; i++) {
            input = reader.readLine().toString().split(" ");
            pairs.add(new Pair(Double.parseDouble(input[0]), Double.parseDouble(input[1])));
        }
        return pairs;
    }

    private class Pair {
        double x, y;

        public Pair(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
}
