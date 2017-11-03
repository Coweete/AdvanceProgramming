package ProgChlng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MinimalCoverage {

    private BufferedReader bufferedReader;

    public static void main(String[] args) {
        MinimalCoverage minimalCoverage = new MinimalCoverage();

        try {
            minimalCoverage.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int testCases, m, index, currentSpot, longest, temp;

        testCases = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < testCases; i++) {
            bufferedReader.readLine();

            index = 0;
            currentSpot = 0;
            longest = 0;
            temp = 0;

            m = Integer.parseInt(bufferedReader.readLine());
            ArrayList<Pair> list = createList();
            ArrayList<Pair> path = new ArrayList<>();
            Collections.sort(list, Comparator.comparing(Pair::getStartX));

            while (currentSpot < m) {

                System.out.println("in while");
                System.out.println("Current spot:" + currentSpot);

                for (int j = index; j < list.size(); j++) {
                    System.out.println("Index:" + index);

                    if (list.get(i).startX > currentSpot) {
                        System.out.println("Break");
                        currentSpot = longest;
                        break;
                    }

                    if (!(list.get(i).endX <= currentSpot)) {
                        System.out.println("Hello");
                        temp = list.get(i).endX;
                        if (temp > longest)
                            longest = temp;
                    }
                }

            }


        }

    }

    public ArrayList<Pair> createList() throws IOException {
        ArrayList<Pair> list = new ArrayList<>();
        int[] array = new int[2];
        while (true) {
            String[] input = bufferedReader.readLine().split(" ");
            array[0] = Integer.parseInt(input[0]);
            array[1] = Integer.parseInt(input[1]);
            if (array[0] == 0 && array[1] == 0) {
                break;
            } else {
                list.add(new Pair(array[0], array[1]));
            }
        }
        return list;
    }

    public void printArray(ArrayList<Pair> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println("start:" + list.get(i).startX + ",end:" + list.get(i).endX);
        }
    }

    private class Pair {
        int startX;
        int endX;

        public Pair(int startX, int endX) {
            this.startX = startX;
            this.endX = endX;
        }

        public int getStartX() {
            return startX;
        }

        public int getEndX() {
            return endX;
        }
    }
}
