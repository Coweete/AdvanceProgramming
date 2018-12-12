package NotInClass;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class OptimalParking {

    public static void main(String[] args) {
        OptimalParking optimalParking = new OptimalParking();
        try {
            optimalParking.parking();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void parking() throws Exception{
        String temp[];
        String in,output;
        int testcases,parkingDistance,numberOfShops;
        ArrayList<Integer> shops = new ArrayList<>();

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        testcases = Integer.parseInt(input.readLine());


        for (int i = 0; i < testcases; i++) {
            numberOfShops = Integer.parseInt(input.readLine());
            temp = input.readLine().split(" ");
            parkingDistance = 0;

            for (int j = 0; j < numberOfShops; j++) {
                shops.add(Integer.parseInt(temp[j]));

            }
            Collections.sort(shops);

            for (int j = 0; j < shops.size() - 1; j++) {
                parkingDistance += (shops.get(j + 1) - shops.get(j));
            }
            System.out.println(parkingDistance*2);
            shops = new ArrayList<>();
        }
    }


}
