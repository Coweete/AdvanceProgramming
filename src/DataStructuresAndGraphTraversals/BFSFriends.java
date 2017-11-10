package DataStructuresAndGraphTraversals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BFSFriends {

    private BufferedReader bufferedReader;

    public static void main(String[] args) {
        BFSFriends bfsFriends = new BFSFriends();
        try {
            bfsFriends.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void start() throws IOException{
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int citizens,pair;
        int testCases = Integer.parseInt(bufferedReader.readLine());
        String[] input;

        for (int i = 0; i < testCases; i++) {
            input = bufferedReader.readLine().split(" ");
            citizens = Integer.parseInt(input[0]);
            pair = Integer.parseInt(input[1]);
            int[][] array = ajd_matrix(citizens,pair);
            printArray(array);
        }
    }

    private int[][] ajd_matrix(int citizens,int pair) throws IOException{
        int[][] array = new int[citizens][citizens];
        String[] input;
        for (int i = 0; i < pair; i++) {
            input = bufferedReader.readLine().split(" ");
            array[Integer.parseInt(input[0])-1][Integer.parseInt(input[1]) -1] = 1;
        }
        return array;
    }

    private void printArray(int[][] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print("[");
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j] + ",");
            }
            System.out.print("]\n");
        }
    }

    private class Friends{
        boolean visited;
    }
}
