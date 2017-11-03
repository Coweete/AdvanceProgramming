package ProgChlng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class TheGrandDinner {

    private BufferedReader bufferedReader;

    public static void main(String[] args) {
        TheGrandDinner theGrandDinner = new TheGrandDinner();

        try {
            theGrandDinner.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int nbrOfTable, nbrOfgroups, counter;

        String[] input = bufferedReader.readLine().split(" ");

        while (true) {
            nbrOfgroups = Integer.parseInt(input[0]);
            nbrOfTable = Integer.parseInt(input[1]);

            System.out.println("nbrOfGroups:" + nbrOfgroups);
            System.out.println("nbrOfTables:" + nbrOfTable);

            ArrayList<Table> tables1 = new ArrayList<>();
            ArrayList<Members> members = new ArrayList<>();

            input = bufferedReader.readLine().split(" ");
            for (int i = 0; i < input.length; i++) {
                members.add(new Members(i +1, Integer.parseInt(input[i])));
            }

            input = bufferedReader.readLine().split(" ");
            for (int i = 0; i < input.length; i++) {
                tables1.add(new Table(i + 1, Integer.parseInt(input[i])));
            }

            System.out.println("Members size:" + members.size());
            System.out.println("tables size:" + tables1.size());
            printTableArray(tables1);

            Collections.sort(members, Comparator.comparing(Members::getNbrOfMembers));
            Collections.reverse(members);

            System.out.println("Biggest group:" + members.get(0).nbrOfMembers);
            //Might be wrong statement!
            if (members.get(0).nbrOfMembers > tables1.size()) {
                //cant produce arrangement
                System.out.println("0");
            } else {
                for (int i = 0; i < members.size(); i++) {
                    counter = 0;
                    for (int j = 0; j < members.get(i).nbrOfMembers; j++) {
                        if (counter == tables1.size()) {
                            //Not valid should end here
                            System.out.println("0");
                            break;
                        } else {
                            if (tables1.get(counter).tableList.size() <= tables1.get(counter).maxAmount) {
                                tables1.get(counter).tableList.add(members.get(i).teamNumber);
                                members.get(i).memberAtTable.add(tables1.get(counter).tableNumber);
                                counter++;
                            } else {
                                counter++;
                                j = j-1;
                            }
                        }
                    }
                }
            }

            Collections.sort(members,Comparator.comparing(Members::getTeamNumber));

            System.out.println("Print table");
            printTableArray(tables1);
            System.out.println("Print members at tables");
            printInnerMemberArray(members);


            //To end loop
            input = bufferedReader.readLine().split(" ");
            nbrOfgroups = Integer.parseInt(input[0]);
            nbrOfTable = Integer.parseInt(input[1]);
            if (nbrOfgroups == 0 && nbrOfTable == 0) {
                break;
            }
        }
    }


    private void printTableArray(ArrayList<Table> tables) {
        for (int i = 0; i < tables.size(); i++) {
            System.out.println("Table:" + (tables.get(i).tableNumber +1));
            for (int j = 0; j < tables.get(i).tableList.size(); j++) {
                System.out.print(tables.get(i).tableList.get(j) + ",");
            }
            System.out.println("");
        }
    }

    private void printMemberArray(ArrayList<Members> members){
        for (int i = 0; i < members.size(); i++) {
            System.out.print("MemberGroup:" + members.get(i).teamNumber + ",nbrOfmembers:" + members.get(i).nbrOfMembers);
        }
    }

    private void printInnerMemberArray(ArrayList<Members> members){
        for (int i = 0; i < members.size(); i++) {
            System.out.println("Membergroup:" + members.get(i).teamNumber);
            for (int j = 0; j < members.get(i).memberAtTable.size(); j++) {
                System.out.print(members.get(i).memberAtTable.get(j) + ",");
            }
            System.out.println("");
        }
    }

    private class Members {
        int teamNumber;
        int nbrOfMembers;
        ArrayList<Integer> memberAtTable = new ArrayList<>();

        public Members(int teamNumber, int nbrOfMembers) {
            this.nbrOfMembers = nbrOfMembers;
            this.teamNumber = teamNumber;
        }

        public int getTeamNumber() {
            return teamNumber;
        }

        public int getNbrOfMembers() {
            return nbrOfMembers;
        }
    }

    private class Table {
        int tableNumber;
        int maxAmount;
        ArrayList<Integer> tableList = new ArrayList<>();

        public Table(int tableNumber, int maxAmount) {
            this.tableNumber = tableNumber;
            this.maxAmount = maxAmount;
        }
    }
}
