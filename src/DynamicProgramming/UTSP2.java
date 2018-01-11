package DynamicProgramming;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class UTSP2 {

    private Scanner scanner;
    //private BufferedReader bufferedReader;

    public static void main(String[] args) {
        UTSP2 utsp2 = new UTSP2();

        try {
            utsp2.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException {
        scanner = new Scanner(System.in);
        //bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int row, column;
        String[] input;

        while (true) {
            try {
                input = scanner.nextLine().split(" ");
                //input = bufferedReader.readLine().split(" ");
                if (input[0].equals("")||input[1].equals("")) break;
                row = Integer.parseInt(input[0]);
                column = Integer.parseInt(input[1]);
                Node[][] nodes = shittyInput(row, column);
                ArrayList<Node> nodeList = new ArrayList<>();
                int temp, minSum = Integer.MAX_VALUE;
                for (int i = 0; i < row; i++) {
                    temp = search(nodes, nodes[i][0]);
                    nodeList.add(nodes[i][0]);
                    if (temp == minSum) {
                        nodeList.add(nodes[i][0]);
                    } else if (temp < minSum) {
                        minSum = temp;
                        nodeList.clear();
                        nodeList.add(nodes[i][0]);
                    }
                }
                Collections.reverse(nodeList.get(0).path);
                printPath(nodeList.get(0));
                System.out.println(minSum);
                nodeList.clear();
            } catch (Exception e) {
                System.out.println("");
                break;
            }
        }
    }

    private int search(Node[][] nodes, Node currentNode) {
        int topDist = 0, botDist = 0, middleDist = 0;
        Node top, middle, bottom;
        if (currentNode.ownCoordinate.column == (nodes[0].length - 1)) {
            currentNode.distance = currentNode.cost;
            currentNode.path.add(currentNode.ownCoordinate);
            return currentNode.cost;
        }

        if (currentNode.ownCoordinate.row + 1 >= nodes.length) {
            if (nodes[0][currentNode.ownCoordinate.column + 1].distance == 0)
                botDist = search(nodes, nodes[0][currentNode.ownCoordinate.column + 1]);
            else
                botDist = nodes[0][currentNode.ownCoordinate.column + 1].distance;

            bottom = nodes[0][currentNode.ownCoordinate.column + 1];
        } else {
            if (nodes[currentNode.ownCoordinate.row + 1][currentNode.ownCoordinate.column + 1].distance == 0)
                botDist = search(nodes, nodes[currentNode.ownCoordinate.row + 1][currentNode.ownCoordinate.column + 1]);
            else
                botDist = nodes[currentNode.ownCoordinate.row + 1][currentNode.ownCoordinate.column + 1].distance;

            bottom = nodes[currentNode.ownCoordinate.row + 1][currentNode.ownCoordinate.column + 1];
        }

        if (currentNode.ownCoordinate.row - 1 < 0) {
            if (nodes[nodes.length - 1][currentNode.ownCoordinate.column + 1].distance == 0)
                topDist = search(nodes, nodes[nodes.length - 1][currentNode.ownCoordinate.column + 1]);
            else
                topDist = nodes[nodes.length - 1][currentNode.ownCoordinate.column + 1].distance;

            top = nodes[nodes.length - 1][currentNode.ownCoordinate.column + 1];
        } else {
            if (nodes[currentNode.ownCoordinate.row - 1][currentNode.ownCoordinate.column + 1].distance == 0)
                topDist = search(nodes, nodes[currentNode.ownCoordinate.row - 1][currentNode.ownCoordinate.column + 1]);
            else
                topDist = nodes[currentNode.ownCoordinate.row - 1][currentNode.ownCoordinate.column + 1].distance;

            top = nodes[currentNode.ownCoordinate.row - 1][currentNode.ownCoordinate.column + 1];
        }

        if (nodes[currentNode.ownCoordinate.row][currentNode.ownCoordinate.column + 1].distance == 0)
            middleDist = search(nodes, nodes[currentNode.ownCoordinate.row][currentNode.ownCoordinate.column + 1]);
        else
            middleDist = nodes[currentNode.ownCoordinate.row][currentNode.ownCoordinate.column + 1].distance;

        middle = nodes[currentNode.ownCoordinate.row][currentNode.ownCoordinate.column + 1];

        int minDist = Math.min(Math.min(topDist, botDist), middleDist);
        Node optimal = null;
        if (topDist == minDist) {
            optimal = top;
            currentNode.path = new ArrayList<>(top.path);
        }
        if (middleDist == minDist) {
            if (optimal == null) {
                currentNode.path = new ArrayList<>(middle.path);
                optimal = middle;
            } else if (optimal.ownCoordinate.row > middle.ownCoordinate.row) {
                optimal = middle;
                currentNode.path = new ArrayList<>(middle.path);
            }
        }
        if (botDist == minDist) {
            if (optimal == null) {
                currentNode.path = new ArrayList<>(bottom.path);
            } else if (optimal.ownCoordinate.row > bottom.ownCoordinate.row) {
                currentNode.path = new ArrayList<>(bottom.path);
            }
        }
        currentNode.path.add(currentNode.ownCoordinate);
        currentNode.distance = minDist + currentNode.cost;
        return currentNode.distance;
    }


    private Node[][] shittyInput(int row, int column) throws IOException {
        Node[][] nodes = new Node[row][column];
        String[] input;
        int counter = 0;
        for (int i = 0; i < row; i++) {
            input = scanner.nextLine().split(" ");
            //input = bufferedReader.readLine().split(" ");
            if (input.length > column) {
                for (int j = 0; j < input.length; j++) {
                    if (j % column == 0 && j > 0) {
                        i++;
                    }
                    if ((counter % column) == 0) {
                        counter = 0;
                    }
                    nodes[i][counter] = new Node(Integer.parseInt(input[j]), i, counter);
                    counter++;
                }
            } else {
                for (int j = 0; j < column; j++) {
                    nodes[i][j] = new Node(Integer.parseInt(input[j]), i, j);
                }
            }
        }

        return nodes;
    }

    private void printPath(Node node) {
        for (int i = 0; i < node.path.size(); i++) {
            if (i == node.path.size() - 1)
                System.out.print((node.path.get(i).row + 1));
            else
                System.out.print((node.path.get(i).row + 1) + " ");
        }
        System.out.println("");
    }

    private class Node {
        int distance;
        int cost;
        Coordinate ownCoordinate;
        ArrayList<Coordinate> path;

        public Node(int cost, int row, int column) {
            this.distance = 0;
            this.cost = cost;
            path = new ArrayList<>();
            ownCoordinate = new Coordinate(row, column);
        }

    }

    private class Coordinate {
        int row;
        int column;

        public Coordinate(int row, int column) {
            this.row = row;
            this.column = column;
        }

    }
}
