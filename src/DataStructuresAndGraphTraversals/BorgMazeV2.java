package DataStructuresAndGraphTraversals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BorgMazeV2 {

    private Scanner scanner;
    private int x, y,finalDistance,counter;
    private Tile[][] board;
    private Tile startTile;
    private ArrayList<Tile> aliens;


    public static void main(String[] args) {
        BorgMazeV2 borgMazeV2 = new BorgMazeV2();
        try {
            borgMazeV2.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException{
        scanner = new Scanner(System.in);
        String res = scanner.nextLine();
        int textCases = Integer.parseInt(res);

        for (int i = 0; i < textCases; i++) {
            res = scanner.nextLine();
            String[] input = res.split(" ");
            x = Integer.parseInt(input[0]);
            y = Integer.parseInt(input[1]);
            counter = 0;
            aliens = new ArrayList<>();
            createBoard(x,y);

            for (int j = 0; j < board.length; j++) {
                System.out.print("[ ");
                for (int k = 0; k < board[j].length; k++) {
                    System.out.print(board[j][k].id + ",");
                }
                System.out.println("]");
            }


        }

    }

    private void createBoard(int x,int y){
        board = new Tile[y][x];
        String input;

        for (int i = 0; i < y; i++) {
            input = scanner.nextLine();

            for (int j = 0; j < x; j++) {
                if (input.charAt(j) == 'S'){
                    startTile = new Tile('S',0);
                    startTile.x = j;
                    startTile.y = i;
                    startTile.name = "S";
                    board[i][j] = startTile;
                }else if (input.charAt(j) == 'A'){
                    Tile temp = new Tile('A',-1);
                    temp.name = "A" + counter;
                    temp.x = j;
                    temp.y = i;
                    board[i][j] = temp;
                    counter++;
                    aliens.add(temp);
                }else{
                    Tile temp = new Tile(input.charAt(j),-1);
                    temp.x = j;
                    temp.y = i;
                    board[i][j] = temp;
                }
            }
        }
    }


    private class Tile{
        char id;
        String name;
        int weight;
        int x,y;

        public Tile(char id,int weight){
            this.id = id;
            this.weight = weight;
        }

    }
}
