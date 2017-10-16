package DataStructuresAndGraphTraversals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BorgMazev4 {

    private Scanner scanner;
    private Vertices[][] board;
    private ArrayList<Vertices> nodes;

    public static void main(String[] args) {

    }

    public void start() throws IOException{
        scanner = new Scanner(System.in);
        int testCases,boardx,boardy;
        String[] res;
        testCases = Integer.parseInt(scanner.nextLine());
        nodes = new ArrayList<>();

        //Main loop
        for (int i = 0; i < testCases; i++) {
            res = scanner.nextLine().split(" ");
            boardx = Integer.parseInt(res[0]);
            boardy = Integer.parseInt(res[1]);
            createMaze(boardx,boardy);


        }
    }

    public void createMaze(int x,int y){
        board = new Vertices[x][y];

        String res;
        for (int i = 0; i < y; i++) {
            res = scanner.nextLine();
            for (int j = 0; j < x; j++) {
                if (res.charAt(j) == 'S'){

                }else if (res.charAt(j) == 'A'){

                }else if(res.charAt(j) == ' '){

                }else {

                }
            }
        }

    }

    public void Dijsktra(){

    }

    private class Vertices{
        int x,y;
        int weight;
        String id;
        boolean visited;

        public Vertices(int x, int y, int weight, String id) {
            this.x = x;
            this.y = y;
            this.weight = weight;
            this.id = id;
            visited = false;
        }

        public int getWeight(){
            return  this.weight;
        }

    }

}
