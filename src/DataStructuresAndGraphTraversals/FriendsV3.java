package DataStructuresAndGraphTraversals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FriendsV3 {

    private Scanner scanner;

    public static void main(String[] args) {
        FriendsV3 friendsV3 = new FriendsV3();
        try {
            friendsV3.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException{
        scanner = new Scanner(System.in);
        int testCases;
        String input[];
        testCases = Integer.parseInt(scanner.nextLine());



        for (int i = 0; i < testCases; i++) {
            int citizens = 0, pairs = 0;
            input = scanner.nextLine().split(" ");
            citizens = Integer.parseInt(input[0]);
            pairs = Integer.parseInt(input[1]);
            System.out.println("Citizens: " + citizens + " Pairs: " + pairs);
            ArrayList<Node> citizensList = new ArrayList<>();

            for (int j = 1; j < citizens+1; j++) {
                citizensList.add(new Node(j));
            }

            int citizen,friend;
            for (int j = 0; j < pairs; j++) {
                input = scanner.nextLine().split(" ");
                citizen = Integer.parseInt(input[0]);
                friend = Integer.parseInt(input[1]);

                citizensList.get(citizen-1).friends.add(friend);
                citizensList.get(friend-1).friends.add(citizen);
            }

            //skall bort skriver ut vänner för alla invånare
            for (int j = 0; j < citizens; j++) {
                System.out.println("ID:"+citizensList.get(j).id + "Friends:" +citizensList.get(j).friends.size());
                for (int k = 0; k < citizensList.get(j).friends.size(); k++) {
                    System.out.println("Friends: " +citizensList.get(j).friends.get(k));
                }
            }

            for (int j = 0; j < citizens; j++) {
                if (!citizensList.get(j).visited) {
                    checkForFriends(citizensList.get(j),citizensList);
                }
            }

            //skall bort skriver ut vänner för alla invånare
            for (int j = 0; j < citizens; j++) {
                System.out.println("ID:"+citizensList.get(j).id + "Friends:" +citizensList.get(j).friends.size());
                for (int k = 0; k < citizensList.get(j).friends.size(); k++) {
                    System.out.println("Friends: " +citizensList.get(j).friends.get(k));
                }
            }


        }
    }

    private void checkForFriends(Node node,ArrayList<Node> citizensList) {
        node.visited = true;
        System.out.println("Friends id: "+ node.id +",list size: " +
                    node.friends.size());
        for (int i = 0; i < node.friends.size(); i++) {
            if (!citizensList.get(node.friends.get(i)).visited);
                checkForFriends(citizensList.get(node.friends.get(i)),citizensList);
                addFriends(citizensList.get(node.friends.get(i)).friends,node);
        }
    }

    public void addFriends(ArrayList<Integer> friends, Node node){
        for (int i = 0; i < friends.size(); i++) {
            if (!node.friends.contains(friends.get(i))){
                node.friends.add(friends.get(i));
            }
        }
    }

    private class Node{
        int id;
        boolean visited;
        ArrayList<Integer> friends;

        public Node(int id){
            this.id = id;
            friends = new ArrayList<>();
            visited = false;
        }
    }
}
