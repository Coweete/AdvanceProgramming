package Arithmetics.DC.GreedyAlgorithms;

import java.io.IOException;
import java.util.Scanner;

public class WineTradingInGergovia1 {

    private Scanner scanner;

    public static void main(String[] args) {
        WineTradingInGergovia1 wg = new WineTradingInGergovia1();
        try {
            wg.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException{
        long inhabitants,leftIndex,rigthIndex,totalwork;
        long[] leftWork,rigthWork;
        String[] input;
        scanner = new Scanner(System.in);
        boolean flag = true;

        inhabitants = Long.parseLong(scanner.nextLine());

        while (flag){
            leftIndex = inhabitants / 2;
            //rigthIndex = inhabitants - leftIndex;
            //leftWork = new long[(int) leftIndex];
            //rigthWork= new long[(int) rigthIndex];
            input = scanner.nextLine().split(" ");
            leftWork = convertArray(input,0,leftIndex);
            rigthWork = convertArray(input,leftIndex,input.length);
            calculateLeft(leftWork);
            calculateRight(rigthWork);
            //System.out.println(totalwork);
            inhabitants = Long.parseLong(scanner.nextLine());
            if (inhabitants == 0){
                break;
            }
        }

    }

    public long[] convertArray(String[] text,long startIndex,long endIndex){
        long[] array = new long[(int)endIndex];

        for (int i =(int) startIndex; i <(int) endIndex; i++) {
            array[i] = Long.parseLong(text[i]);
            System.out.println(text[i]);
        }
        System.out.println("End/n");
        return array;
    }

    public long[] calculateLeft(long[] array){
        long sum = 0,work = 0;
        long[] res = new long[2];
        for (int i = 0; i < array.length-1; i++) {
            work += array[i];
            sum += array[i] + array[i+1];
            System.out.println("Left sun in loop:" + sum);
            System.out.println("Left work in loop: " + work);
        }
        System.out.println("Left sun:" + sum);
        System.out.println("Left work: " + work);
        return res;
    }

    public long[] calculateRight(long[] array){
        long sum = 0,work = 0;
        long[] res = new long[2];
        for (int i = array.length-1; i > 0 ; i--) {
            work += array[i];
            sum += array[i] + array[i-1];
            System.out.println("Right sun in loop:" + sum);
            System.out.println("Right work in loop: " + work);
        }
        System.out.println("Right sun:" + sum);
        System.out.println("Right work: " + work);
        return res;
    }

}
