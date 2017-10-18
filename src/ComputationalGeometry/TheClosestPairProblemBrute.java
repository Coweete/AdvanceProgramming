package ComputationalGeometry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TheClosestPairProblemBrute {

    private Scanner scanner;

    public static void main(String[] args) {

    }

    public void start() throws IOException {
        scanner = new Scanner(System.in);
        String[] input;
        int nbrOfNodes;

        nbrOfNodes = Integer.parseInt(scanner.nextLine());
        while (true) {
            ArrayList<Pair> pairs = createNodes(nbrOfNodes);
            printArray(pairs);


            nbrOfNodes = Integer.parseInt(scanner.nextLine());
            if (nbrOfNodes == 0) {
                break;
            }
        }


    }

    private void closestPair(ArrayList<Pair> pairs){

    }

    public ArrayList<Pair> createNodes(int numberofnodes) {
        ArrayList<Pair> pairs = new ArrayList<>();
        String[] input;
        for (int i = 0; i < numberofnodes; i++) {
            input = scanner.nextLine().split(" ");
            pairs.add(new Pair(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
        }
        return pairs;
    }

    private void printArray(ArrayList<Pair> pairs) {
        for (int i = 0; i < pairs.size(); i++) {
            System.out.println("X:" + pairs.get(i).x + ",Y:" + pairs.get(i).y);
        }
    }


    private class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
