package DataStructuresAndGraphTraversals;

import java.io.IOException;
import java.util.Scanner;

public class BorgMazev4 {

    private Scanner scanner;

    public static void main(String[] args) {

    }

    public void start() throws IOException{
        scanner = new Scanner(System.in);
        int testCases,boardx,boardy;
        String[] res;
        testCases = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < testCases; i++) {
            res = scanner.nextLine().split(" ");
            boardx = Integer.parseInt(res[0]);
            boardy = Integer.parseInt(res[1]);
            createMaze(boardx,boardy);

        }
    }

    public void createMaze(int x,int y){

    }

    public void Dijsktra(){

    }


}
