package Arithmetics.DC.GreedyAlgorithms;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Jounne on 2017-10-08.
 */
public class WineTradingInGergovia {


    private Scanner scanner;

    public static void main(String[] args) {
        WineTradingInGergovia wg = new WineTradingInGergovia();
        try {
            wg.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException{
        long inhabitants,leftIndex,rigthIndex,totalwork;
        long[] leftWork,rigthWork;
        String[] input,lv,rv;
        scanner = new Scanner(System.in);
        boolean flag = true;

        inhabitants = Long.parseLong(scanner.nextLine());

        while (flag){
            leftIndex = inhabitants / 2;
            rigthIndex = inhabitants - leftIndex;
            lv = new String[(int) leftIndex];
            rv = new String[(int)rigthIndex];
            input = scanner.nextLine().split(" ");
            for (int i = 0; i < leftIndex; i++) {
                lv[i] = input[i];
            }
            for (int i = (int)leftIndex; i < input.length; i++) {
                rv[i-(int)leftIndex] = input[i];
            }
            leftWork = convertArray(lv,0,lv.length);
            rigthWork = convertArray(rv,0,rv.length);
            leftWork = calculateLeft(leftWork);
            rigthWork = calculateRight(rigthWork);
            totalwork = Math.abs(leftWork[1]) + leftWork[0] + rigthWork[0];
            System.out.println(totalwork);
            inhabitants = Long.parseLong(scanner.nextLine());
            if (inhabitants == 0){
                break;
            }
        }

    }

    public long[] convertArray(String[] text,long startIndex,long endIndex){
        long[] array = new long[(int)endIndex];
        for (int i =(int) startIndex; i <(int) endIndex; i++) {
            array[i-(int)startIndex] = Long.parseLong(text[i]);
        }
        return array;
    }


    public long[] calculateLeft(long[] array){
        long work = 0;
        long[] res = new long[2];
        for (int i = 0; i < array.length-1; i++) {
            work = work + Math.abs(array[i]);
            array[i + 1] = array[i] + array[i + 1];
        }
        res[0] = work;
        res[1] = array[array.length-1];
        return res;
    }

    public long[] calculateRight(long[] array){
        long work = 0;
        long[] res = new long[2];
        for (int i = array.length-1; i > 0 ; i--) {
            work = work + Math.abs(array[i]);
            array[i - 1] = array[i] + array[i - 1];
        }
        res[0] = work;
        res[1] = array[0];
        return res;
    }
}
