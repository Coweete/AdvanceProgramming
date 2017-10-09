
package Arithmetics.DC.GreedyAlgorithms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by Jounne on 2017-10-09.
 */

public class AllinAll {

    private Scanner scanner;

    public static void main(String[] args) {
        AllinAll allinAll = new AllinAll();
        try {
            allinAll.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void start() throws IOException {
        String input;
        int counter = 0;
        ArrayList<Character> array = new ArrayList<>();
        String[] strings;
        scanner = new Scanner(System.in);
        input = scanner.nextLine();

        while (true) {
            try {
                strings = input.split(" ");
                for (int i = 0; i < strings[1].length(); i++) {
                    if (strings[0].charAt(counter) == strings[1].charAt(i)) {
                        array.add(strings[0].charAt(counter));
                        if ((counter+1) >= strings[0].length()){
                            break;
                        }else {
                            counter++;
                        }
                    }
                }
                if (strings[0].length() == array.size()) {
                    System.out.println("Yes");
                } else {
                    System.out.println("No");
                }
                input = scanner.nextLine();

            }catch (Exception e){
                break;
            }
            array.clear();
            counter = 0;
        }

    }

}
