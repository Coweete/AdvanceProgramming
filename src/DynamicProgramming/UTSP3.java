package DynamicProgramming;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class UTSP3 {

    private Scanner scanner;
    private String[] input;
    private Node[][] matrix;

    public static void main(String[] args) {
        UTSP3 utsp3 = new UTSP3();
        try {
            utsp3.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException {
        scanner = new Scanner(System.in);
        int row, column;

        while (scanner.hasNext()){
            row = scanner.nextInt();
            column = scanner.nextInt();

            matrix = new Node[row][column];

            //Create Matrix
            for (int i = 0; i < row; ++i) {
                for (int j = 0; j < column; ++j) {
                    matrix[i][j] = new Node(scanner.nextInt(),i);
                }
            }

            //Create all paths
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    if (j + 1 >= column)
                        continue;

                    if ((i - 1) < 0) {
                        matrix[i][j].top = matrix[row - 1][j + 1];
                    } else {
                        matrix[i][j].top = matrix[i - 1][j + 1];
                    }
                    matrix[i][j].middle = matrix[i][j + 1];
                    if ((i + 1) >= row) {
                        matrix[i][j].bottom = matrix[0][j + 1];
                    } else {
                        matrix[i][j].bottom = matrix[i + 1][j + 1];
                    }
                }
            }

            search(row,column);
            showResult(row);
        }
    }

    private void showResult(int row) {
        int best = Integer.MAX_VALUE;
        int bestRowIndex = 0;

        //Find best value
        for (int i = 0; i < row; i++) {
            if (matrix[i][0].value < best) {
                best = matrix[i][0].value;
                bestRowIndex = i;
            }
        }

        //Print out the path
        Node theBestNode = matrix[bestRowIndex][0];
        System.out.print(theBestNode.row + 1);
        while (theBestNode.from != null) {
            theBestNode = theBestNode.from;
            System.out.print(" " + (theBestNode.row + 1));
        }
        System.out.println("");
        System.out.println(best);
    }

    private void search(int row, int column) {
        //Goes from column
        for (int i = column - 2; i >= 0; i--) {
            for (int j = row - 1; j >= 0; j--) {
                ArrayList<Node> nodes = new ArrayList<>();
                nodes.add(matrix[j][i].top);
                nodes.add(matrix[j][i].middle);
                nodes.add(matrix[j][i].bottom);

                //Sorts on row value
                Collections.sort(nodes, Comparator.comparing(Node::getRow));

                int best = Integer.MAX_VALUE;

                //Checks for smallest path
                for (int k = 0; k < nodes.size(); k++) {
                    if (nodes.get(k).value < best) {
                        best = nodes.get(k).value;
                        matrix[j][i].from = nodes.get(k);
                    }
                }
                matrix[j][i].value += best;
            }
        }
    }


    private class Node {
        int value,row;
        Node top, middle, bottom, from;

        public Node(int value, int row) {
            this.value = value;
            this.row = row;
        }

        public int getRow() {
            return row;
        }
    }
}
