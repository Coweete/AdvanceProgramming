package ComputationalGeometry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by Jounne on 2017-10-16.
 */
public class SunnyMountains {

    private Scanner scanner;

    public static void main(String[] args) {
        SunnyMountains mountains = new SunnyMountains();
        try {
            mountains.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start()throws IOException{
        scanner = new Scanner(System.in);
        int testCases,nbrnodes;
        double result;

        testCases = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < testCases; i++) {
            nbrnodes = Integer.parseInt(scanner.nextLine());
            ArrayList<Node> nodes = createNodes(nbrnodes);
            //Sort the array on x values!
            Collections.sort(nodes, Comparator.comparing(Node::getX));
            result = calculateDistance(nodes);
            System.out.println(String.format("%.2f",result));

        }

    }

    private double calculateDistance(ArrayList<Node> nodes){
        Node temp,currentPos;
        double deltaX,deltaY,sum;
        temp = nodes.get(nodes.size()-1);
        currentPos = nodes.get(nodes.size()-2);
        deltaX = (temp.x - currentPos.x);
        deltaY = (temp.y - currentPos.y);
        sum = Math.sqrt((Math.pow(deltaX,2)+Math.pow(deltaY,2)));
        for (int i = nodes.size()-3; i >= 0 ; i--) {
            if (nodes.get(i).y > currentPos.y){
                sum += calculateHypoFrom3Nodes(nodes.get(i),nodes.get(i+1),currentPos);
                currentPos = nodes.get(i);
            }
        }
        return sum;
    }

    private double calculateHypoFrom3Nodes(Node newNode,Node newNodeplus1,Node currentNode){
        double deltaX,deltaY,sum,k,m,x;
        k = ((double) newNode.y - (double)newNodeplus1.y)/((double)newNode.x - (double)newNodeplus1.x);
        m = (double)newNode.y - (k * (double)newNode.x);
        x = ((double)currentNode.y - m) / k;
        deltaX = ((double)newNode.x - x);
        deltaY = ((double)newNode.y - (double)currentNode.y);
        sum = Math.sqrt((Math.pow(deltaX,2)+Math.pow(deltaY,2)));
        return sum;
    }

    private ArrayList<Node> createNodes(int nbrNodes){
        ArrayList<Node> nodes = new ArrayList<>();
        String[] res;
        for (int i = 0; i < nbrNodes; i++) {
            res = scanner.nextLine().split(" ");
            nodes.add(new Node(Integer.parseInt(res[0]),Integer.parseInt(res[1])));
        }
        return nodes;
    }

    private class Node{
        int x,y;

        public Node(int x,int y){
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

    }
}
