package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NotSoMobile {

    private boolean result;
    private BufferedReader reader;

    public static void main(String[] args) throws IOException {
        NotSoMobile notSoMobile = new NotSoMobile();
        notSoMobile.mobile();
    }

    public void mobile() throws IOException {
        int testCases;
        int numbers[] = new int[4];
        String input[];
        String res = "as";
        reader = new BufferedReader(new InputStreamReader(System.in));

        testCases = Integer.parseInt(reader.readLine());
        System.out.println("TestCases " + testCases);


        for (int i = 0; i < testCases; i++) {
            res = reader.readLine();
            //System.out.println("test: " + testMobile());
            input = reader.readLine().split(" ");
            numbers = getNumbers(input);
            System.out.println("Test Result: " + test2(numbers[0],numbers[1],numbers[2],numbers[3]));
            System.out.println("Endresult: " + result);
            result = true;
        }


    }


    public int test2(int wr,int dr,int wl,int dl) throws IOException {
        int wghr = 0, wghl = 0,total = 0;
        String res[] = new String[4];
        int numbers[] = new int[4];

        if(wl == 0){
            System.out.println("Vänster ");
            res = reader.readLine().split(" ");
            numbers = getNumbers(res);
            wghl = test2(numbers[0],numbers[1],numbers[2],numbers[3]);
        }

        if(wr == 0){
            System.out.println("HÖGER ");
            res = reader.readLine().split(" ");
            numbers = getNumbers(res);
            wghr = test2(numbers[0],numbers[1],numbers[2],numbers[3]);
        }


        System.out.println("Check1: " + (wghr * dr) + " = " + (wghl * dl));

        if(!((wghr * dr) == (wghl * dl))){
            result = false;
        }


        total = wghl + wghr + wl + wr;
        System.out.println("Total: " + total);
        return total;

    }

    public int[] getNumbers(String array[]){
        int numbers[] = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            numbers[i] = Integer.parseInt(array[i]);
        }
        return numbers;
    }

}

//        public int testMobile(BufferedReader bufferedReader) throws IOException {
//        String res[] = new String[4];
//        res = bufferedReader.readLine().split(" ");
//        int numbers[] = getNumbers(res);
//        int weigthl = 0,weigthr = 0;
//
//        if(numbers[0] == 0){
//            System.out.println("Left ");
//            weigthl = testMobile(bufferedReader);
//            System.out.println( weigthl);
//        }
//
//        if(numbers[2] == 0){
//            System.out.println("Right ");
//            weigthr = testMobile(bufferedReader);
//            System.out.println( weigthr);
//        }
//
//        if(!((weigthl * numbers[1]) == (weigthr * numbers[3]))){
//            result = false;
//        }
//
//
//        return  weigthl + weigthr;
//
//    }

