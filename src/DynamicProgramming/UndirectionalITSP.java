package DynamicProgramming;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class UndirectionalITSP {

    private Scanner scanner;
    private ArrayList<int[]> tour;
    private int[][] savedValues;
    private HashMap<Integer,Integer> path;

    public static void main(String[] args) {
        UndirectionalITSP itsp = new UndirectionalITSP();
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
        tour = new ArrayList<int[]>();
        int sum = shortestPath(0,0,array);
        System.out.println(sum);
        System.out.println("Array size: " + tour.size());
        for (int i = 0; i < tour.size(); i++) {
            //System.out.print(tour.get(i) + " ");
            int[] temp = tour.get(i);
            System.out.print("[" + temp[0] + "," + temp[1] + "]");
        }
        System.out.println("\nSaved values:");
        printArray(savedValues);
    }

    public int[][] createArray(int x,int y){
        int[][] array = new int[x][y];
        savedValues = new int[x][y];
        String[] input;
        for (int i = 0; i < array.length; i++) {
            input = scanner.nextLine().split(" ");
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = Integer.parseInt(input[j]);
            }
        }
        return array;
    }

    public int shortestPath(int a,int b,int[][] array){
        int[] tempa;
        if (b > array.length){
            tempa = new int[]{a, b};
            tour.add(tempa);
            return 0;
        }
        b++;
        int count,count1,count2,temp;
        if ((a+1) >= array.length) {
            temp = 0;
        }else {
            temp = a + 1;
        }
        count = shortestPath(temp,b,array);
        if ((a-1) < 0) {
            temp = array.length-1;
        }else {
            temp = a -1;
        }
        count1 = shortestPath(temp,b,array);
        count2 = shortestPath(a,b,array);

        if (savedValues[a][b-1] == 0){
            savedValues[a][b-1] = Math.min(count,Math.min(count1,count2)) + array[a][b-1];
        }
        tempa = new int[]{a,b-1};
        tour.add(tempa);
        return savedValues[a][b-1];
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
}
