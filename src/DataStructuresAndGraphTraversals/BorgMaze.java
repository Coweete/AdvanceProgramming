package DataStructuresAndGraphTraversals;



import java.io.IOException;
import java.util.*;

public class BorgMaze {

    private Scanner scanner;
    private int x, y,finalDistance;

    public static void main(String[] args) {
        BorgMaze borgMaze = new BorgMaze();
        try {
            borgMaze.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException {

        scanner = new Scanner(System.in);
        String res = scanner.nextLine();
        int testCases = Integer.parseInt(res),counter = 0;
        ArrayList<Tile> aliens = new ArrayList<>();

        for (int i = 0; i < testCases; i++) {
            res = scanner.nextLine();
            String[] strings = res.split(" ");
            x = Integer.parseInt(strings[0]);
            y = Integer.parseInt(strings[1]);

            System.out.println("X: " + x);
            System.out.println("Y: " + y);

            Tile[][] board = new Tile[y][x];
            Tile startTile = null;


            for (int j = 0; j < y; j++) {
                res = scanner.nextLine();

                for (int k = 0; k < x; k++) {
                    Tile node = new Tile(res.charAt(k), -1);
                    board[j][k] = node;
                    board[j][k].x = k;
                    board[j][k].y = j;

                    if (res.charAt(k) == 'S') {
                        startTile = new Tile(res.charAt(k), 0);
                        startTile.x = k;
                        startTile.y = j;
                        startTile.name = "S";
                    }else if (res.charAt(k) == 'A') {
                        Tile temp = new Tile(res.charAt(k), -1);
                        temp.x = k;
                        temp.y = j;
                        temp.name = "A" + counter;
                        aliens.add(temp);
                        counter++;
                    }
                }

            }

            //MORE THREADS ?

            calculateWeight(board,startTile,aliens);
            for (int j = 0; j < aliens.size()-1; j++) {
                ArrayList<Tile> tiles = new ArrayList<>();
                tiles.addAll(aliens);
                Tile temp = tiles.remove(j);
                tiles.add(startTile);
                calculateWeight(board,temp,tiles);
            }

            //Minimal Spanning tree ?



        }
    }

    private void calculateWeight(Tile[][] board, Tile startNode, ArrayList<Tile> endNodes) {
        LinkedList<Tile> queue = new LinkedList<>();
        startNode.weigth = 0;
        queue.add(startNode);

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                board[i][j].weigth = -1;
            }
        }

        board[startNode.y][startNode.x].weigth = 0;


        while (!queue.isEmpty()) {
            Tile tile = queue.pop();
            //System.out.println("Active tile: " + tile.id + ",weigth " + tile.weigth + "x: " + tile.x + "y: " + tile.y + "\n");

            if ((tile.x + 1 < x) && !(board[tile.y][tile.x + 1].id == '#') && (board[tile.y][tile.x + 1].weigth == -1)) {
                board[tile.y][tile.x + 1].weigth = tile.weigth + 1;
                Tile newTile = new Tile(board[tile.y][tile.x + 1].id, tile.weigth + 1);
                newTile.x = tile.x + 1;
                newTile.y = tile.y;
                queue.add(newTile);
            }
            if ((tile.x - 1 >= 0) && !(board[tile.y][tile.x - 1].id == '#') && (board[tile.y][tile.x - 1].weigth == -1)) {
                board[tile.y][tile.x - 1].weigth = tile.weigth + 1;
                Tile newTile = new Tile(board[tile.y][tile.x - 1].id, tile.weigth + 1);
                newTile.x = tile.x - 1;
                newTile.y = tile.y;
                queue.add(newTile);
            }
            if ((tile.y - 1 >= 0) && !(board[tile.y - 1][tile.x].id == '#') && (board[tile.y - 1][tile.x].weigth == -1)) {
                board[tile.y - 1][tile.x].weigth = tile.weigth + 1;
                Tile newTile = new Tile(board[tile.y - 1][tile.x].id, tile.weigth + 1);
                newTile.x = tile.x;
                newTile.y = tile.y - 1;
                queue.add(newTile);
            }
            if ((tile.y + 1 < y) && !(board[tile.y + 1][tile.x].id == '#') && (board[tile.y + 1][tile.x].weigth == -1)) {
                board[tile.y + 1][tile.x].weigth = tile.weigth + 1;
                Tile newTile = new Tile(board[tile.y + 1][tile.x].id, tile.weigth + 1);
                newTile.x = tile.x;
                newTile.y = tile.y + 1;
                queue.add(newTile);
            }
        }

        for (int i = 0; i < endNodes.size(); i++) {
            Tile temp = endNodes.get(i);
            System.out.println(startNode.name + " " + board[temp.y][temp.x].weigth + " " + temp.name);
            if (startNode.distance != null && temp.distance.containsKey(startNode.name)){
                if (temp.distance.get(startNode.name) < temp.weigth){
                    startNode.distance.replace(temp.name,temp.weigth);
                    temp.distance.replace(startNode.name,startNode.weigth);
                }
            }else{
                startNode.distance.put(temp.name,temp.weigth);
                temp.distance.put(startNode.name,startNode.weigth);
            }
        }

        for (int j = 0; j < board.length; j++) {
            System.out.print("[ ");
            for (int k = 0; k < board[j].length; k++) {
                System.out.print(board[j][k].weigth + ",");
            }
            System.out.println("]");
        }
    }

    private class Tile {
        char id;
        String name;
        int weigth;
        int x, y;
        Map<String,Integer> distance = new HashMap<>();


        public Tile(char id, int weight) {
            this.id = id;
            this.weigth = weight;
        }
    }

}
