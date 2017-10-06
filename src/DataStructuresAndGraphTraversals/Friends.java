package DataStructuresAndGraphTraversals;

import javax.xml.soap.Node;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Queue;

public class Friends {

    private int pairs,citizens,citizen,friend,biggestFriends = 1;
    private ArrayList<Node> citizensList;


    public static void main(String[] args) {
        Friends friends = new Friends();
        try {
            friends.findFriends();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void findFriends() throws IOException{

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCases;
        String[] input;
        testCases = Integer.parseInt(reader.readLine());

        for (int i = 0; i < testCases; i++) {
           String res = reader.readLine();
           input = res.split(" ");
           citizens = Integer.parseInt(input[0]);
           pairs = Integer.parseInt(input[1]);

           System.out.println("Citizens: " + citizens + " Pairs: " + pairs);
           citizensList = new ArrayList<Node>();

            for (int j = 0; j < citizens; j++) {
                Node n = new Node(j);
                citizensList.add(n);
            }

           for (int j = 0; j < pairs; j++) {
               res = reader.readLine();
               input = res.split(" ");
               citizen = Integer.parseInt(input[0]);
               friend = Integer.parseInt(input[1]);

               citizensList.get(citizen-1).friends.add(friend);
               citizensList.get(friend-1).friends.add(citizen);
           }

            for (int j = 0; j < citizens; j++) {
                if(!citizensList.get(j).visited){
                    checkForFriends(citizensList.get(j));
                }
                System.out.println("Citizen List " + j + " " + citizensList.size());
                if (biggestFriends < citizensList.get(j).friends.size()){
                    System.out.println("In If statement: " + citizensList.get(j).friends.size());
                    biggestFriends = citizensList.get(j).friends.size();
                }
            }
            System.out.println("Biggest friends:" + biggestFriends);
            citizensList.clear();
            biggestFriends = 1;
        }

    }

    private void checkForFriends(Node node) {
        node.visited = true;
        System.out.println("Node id:" + node.id);
        for (int i = 0; i < node.friends.size(); i++) {
            if(!citizensList.get(node.friends.get(i)).visited){
                checkForFriends(citizensList.get(node.friends.get(i)));
                addFriends(citizensList.get(node.friends.get(i)).friends,node);
            }
        }
    }

    public void checkForFriends(int index){
        citizensList.get(index).visited = true;
        for (int i = 0; i < citizensList.get(index).friends.size(); i++) {
        }
    }

    public void addFriends(ArrayList<Integer> friends,Node node){
        for (int i = 0; i < friends.size(); i++) {
            if (!node.friends.contains(friends.get(i))){
                node.friends.add(friends.get(i));
            }
        }
    }

    class Node{
        int id;
        boolean visited;
        ArrayList<Integer> friends;

        public Node(int id){
            this.id = id;
            friends = new ArrayList<>();
        }
    }
}
