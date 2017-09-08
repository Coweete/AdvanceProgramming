package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BoxOfBricks {

    public static void main(String[] args) throws IOException {
        BoxOfBricks boxOfBricks = new BoxOfBricks();
        boxOfBricks.boxes();
    }

    public void boxes() throws IOException {
        String in,temp[];
        int stacks,nbrBricks = 0,optimalValue,diff = 0,sets = 0;
        int[] bricks;

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        stacks = Integer.parseInt(in = input.readLine());

        while(stacks != 0){
            sets++;
            bricks = new int[stacks];
            temp = new String[stacks];
            temp = input.readLine().split(" ");

            for (int i = 0; i < stacks ; i++) {
                bricks[i] = Integer.parseInt(temp[i]);
                nbrBricks = bricks[i] + nbrBricks;
            }

            optimalValue = nbrBricks / stacks;

            for (int i = 0; i < stacks ; i++) {
                diff += Math.abs(optimalValue - bricks[i]);
            }

            diff = diff /2;
            System.out.println("Set #" + sets);
            System.out.println("The minimum number of moves is " + diff + ".\n");
            stacks = Integer.parseInt(in = input.readLine());
            diff = 0;
            optimalValue = 0;
            nbrBricks = 0;

        }

        
        
    }
}
