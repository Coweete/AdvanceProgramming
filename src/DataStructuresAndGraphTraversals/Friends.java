package DataStructuresAndGraphTraversals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Friends {

    private Scanner scanner;

    public static void main(String[] args) {
        Friends friends = new Friends();
        try {
            friends.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException{
        scanner = new Scanner(System.in);
        int testCases;
        int citizens = 0, pairs = 0,biggestGroupOffFriends = 1;
        String input[];
        testCases = Integer.parseInt(scanner.nextLine());


        for (int i = 0; i < testCases; i++) {

            input = scanner.nextLine().split(" ");
            citizens = Integer.parseInt(input[0]);
            pairs = Integer.parseInt(input[1]);
            HashMap<Integer,Node> citizenTestList = new HashMap<>();

            for (int j = 1; j < citizens+1; j++) {
                citizenTestList.put(j,new Node(j));
            }


            int citizen,friend;
            for (int j = 1; j < pairs +1; j++) {
                input = scanner.nextLine().split(" ");
                citizen = Integer.parseInt(input[0]);
                friend = Integer.parseInt(input[1]);

                if (!citizenTestList.get(citizen).friends.contains(friend)){
                    citizenTestList.get(citizen).friends.add(friend);
                    citizenTestList.get(friend).friends.add(citizen);
                }

            }

            for (int j = 1; j < citizenTestList.size(); j++) {
                if (!citizenTestList.get(j).visited){
                    findFriends(citizenTestList.get(j),citizenTestList);
                }
                if (biggestGroupOffFriends < citizenTestList.get(j).friends.size()){
                    biggestGroupOffFriends = citizenTestList.get(j).friends.size();
                }

            }

            citizenTestList.clear();
            System.out.println(biggestGroupOffFriends);
            biggestGroupOffFriends = 1;
        }
    }

    private void findFriends(Node node, HashMap<Integer,Node> list){
        node.visited = true;
        for (int i = 0; i < list.get(node.id).friends.size(); i++) {
            if (!list.get(node.friends.get(i)).visited){
                findFriends(list.get(node.friends.get(i)),list);
                addNewFriends(node,list.get(node.friends.get(i)).friends);
            }
        }

    }

    private void addNewFriends(Node node, ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (!node.friends.contains(list.get(i))){
                node.friends.add(list.get(i));
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
