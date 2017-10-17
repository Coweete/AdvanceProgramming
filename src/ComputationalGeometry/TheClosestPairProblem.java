package ComputationalGeometry;

import java.io.IOException;
import java.util.ArrayList;
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

        ArrayList<Node> nodes = createNodes(nbrOfNodes);
        printArray(nodes);

    }

    public ArrayList<Node> createNodes(int numberofnodes){
        ArrayList<Node> nodes = new ArrayList<>();
        String[] input;
        for (int i = 0; i < numberofnodes; i++) {
            input = scanner.nextLine().split(" ");
            nodes.add(new Node(Float.parseFloat(input[0]),Float.parseFloat(input[1])));
        }
        return nodes;
    }

    private void printArray(ArrayList<Node> nodes){
        for (int i = 0; i < nodes.size(); i++) {
            System.out.println("X:" + nodes.get(i).x + ",Y:" + nodes.get(i).y);
        }
    }


    private class Node{
        float x,y;

        public Node(float x, float y){
            this.x = x;
            this.y = y;
        }
    }
}
