package Arithmetics.DC.GreedyAlgorithms;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Jounne on 2017-10-06.
 */
public class BigMod {

    private Scanner scanner;
    private boolean flag = true;

    public static void main(String[] args) {
        BigMod bigMod = new BigMod();
        try {
            bigMod.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start()throws IOException{
        scanner = new Scanner(System.in);
        long b,p,m;
        while (flag){
            try{
                b = Integer.parseInt(scanner.nextLine());
                p = Integer.parseInt(scanner.nextLine());
                m = Integer.parseInt(scanner.nextLine());
                System.out.println(bigmod(b,p,m));
                scanner.nextLine();
            }catch (Exception e){
                break;
            }
        }
    }

    public long bigmod(long x,long y,long N){
        if ( y == 0){
            return 1;
        }
        long z = bigmod(x,(y/2),N);
        if ((y%2) == 0){
            return (((int) Math.pow(z,2))%N);
        }else{
            return ((x * ((int) Math.pow(z,2)))%N);
        }
    }
}
