package DataStructuresAndGraphTraversals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FriendsV2 {

    private Scanner scanner;
    private int citizens,pairs;

    public static void main(String[] args) {
        FriendsV2 friendsV2 = new FriendsV2();

        try {
            friendsV2.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException{
        scanner = new Scanner(System.in);
        int testCases;
        String[] input;
        testCases = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < testCases; i++) {
            input = scanner.nextLine().split(" ");
            citizens = Integer.parseInt(input[0]);
            pairs = Integer.parseInt(input[1]);

            ArrayList<Friend> citizenList = new ArrayList<>();
            for (int j = 0; j < citizens; j++) {
                citizenList.add(new Friend(j,false));
            }

            System.out.println("Citizens: " + citizens + " Pairs: " + pairs);
            int[][] test = createAdjacencyMatrix(citizens,pairs);




        }

    }

    public void findFriends(int[][] matrix, Friend friend){
        friend.visited = true;



    }

    public int[][] createAdjacencyMatrix(int citizens,int pairs){
        int[][] friends = new int[citizens +1][ citizens +1];
        String[] input;
        for (int i = 0; i < pairs; i++) {
            input = scanner.nextLine().split(" ");
            friends[Integer.parseInt(input[0])][Integer.parseInt(input[1])] = 1;
        }
        return friends;
    }



    public void print2Darray(int[][] test){
        for (int j = 0; j < test.length; j++) {
            System.out.print("[");
            for (int k = 0; k < test[j].length; k++) {
                System.out.print("" + test[k][j] +",");
            }
            System.out.println("]");
        }
    }

    private class Friend{

        int id;
        boolean visited;
        ArrayList<Integer> friends;

        public Friend(int id,boolean visited){
            this.id = id;
            this.visited = visited;
        }
    }
}
