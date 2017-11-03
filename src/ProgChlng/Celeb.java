package ProgChlng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Celeb {

    private BufferedReader reader;

    public static void main(String[] args) {
        Celeb celeb = new Celeb();

        try {
            celeb.test();
        } catch (Exception e) {
            System.out.println("hello");
        }
    }

    public void test() throws IOException{
        reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while ((input = reader.readLine()) != null){
            System.out.println(input);
        }
    }
}
