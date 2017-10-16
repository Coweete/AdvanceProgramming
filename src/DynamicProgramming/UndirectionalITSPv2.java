package DynamicProgramming;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UndirectionalITSPv2{

        private Scanner scanner;
        private ArrayList<Integer> tour;
        private Node tempNode;


        public static void main(String[] args) {
            UndirectionalITSPv2 itsp = new UndirectionalITSPv2();
            try {
                itsp.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void start() throws IOException{
            scanner = new Scanner(System.in);
            String[] input = scanner.nextLine().split(" ");
            int[][] array = createArray(Integer.parseInt(input[0]),Integer.parseInt(input[1]));
            printArray(array);
            System.out.println("Array.length: " + array.length);
            System.out.println("Array[0].length: " + array[0].length);
            tour = new ArrayList<>();
        }

        public int[][] createArray(int x,int y){
            int[][] array = new int[x][y];
            Node[][] nodeArray = new Node[x][y];
            String[] input;
            int[] as = new int[2];
            int temp;

            for (int i = 0; i < array.length; i++) {
                input = scanner.nextLine().split(" ");
                for (int j = 0; j < array[i].length; j++) {
                    tempNode = new Node(i+j);
                    array[i][j] = Integer.parseInt(input[j]);
                    if ((x+1) >= array.length){
                        temp = 0;
                    }else{
                        temp = x +1;
                    }
                    as[0] = temp;
                    as[1] = j+1;
                    tempNode.top = as;
                    if ((x-1) < 0){
                        temp = array.length-1;
                    }else {
                        temp = x -1;
                    }
                    as[0] = temp;
                    as[1] = j+1;
                    tempNode.bottom = as;
                    as[0] = i;
                    as[1] = j+1;
                    tempNode.middle = as;
                    nodeArray[i][j] = tempNode;
                }
            }
            return array;
        }

        public void topSort(int startx,int starty,int[][] array){

        }


        public void printArray(int[][] array){
            for (int i = 0; i < array.length; i++) {
                System.out.print("[");
                for (int j = 0; j < array[i].length; j++) {
                    if (j == array.length){
                        System.out.print(array[i][j]);
                    }else {
                        System.out.print(array[i][j] + " ");
                    }
                }
                System.out.println("]");
            }
        }

        private class Node{
            int[] top;
            int[] middle;
            int[] bottom;
            int id;
            int weight;

            public Node(int id){
                this.id = id;
                top = new int[2];
                middle = new int[2];
                bottom = new int[2];
            }
        }
}
