package Tentaplug;

public class Testing {

    private int counter = 0;
    private double[][] table;

    public static void main(String[] args) {
        Testing testing = new Testing();
        int[] array = testing.createArray();
        int index = testing.findIndex(array,0,array.length

        );
        System.out.println("Index:" + index);
        System.out.println("Value in index:" + array[index]);
    }


    public int exp(int a,int b){
        System.out.println("Counter:" + counter++);
        if(b == 0){
            return 1;
        }

        int z = exp(a,b/2);
        if ((b%2) == 0){
            return (int) Math.pow(z,2);
        }else{
            return (int) (Math.pow(z,2) * a);
        }
    }


    public void calcProb(double a,double b){
        table = new double[(int)a+1][(int)b+1];
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                table[i][j] = Integer.MAX_VALUE;
            }
        }
        System.out.println("Prob sum:" + p(a,b));
    }

    private double p(double i,double j){

        if (i > 0 && j == 0){
            return 0;
        }

        if (i == 0 &&  j > 0){
            return 1;
        }

        if (table[(int)i][(int)j] != Integer.MAX_VALUE){
            return table[(int)i][(int)j];
        }else {
            return (table[(int)i][(int)j] = ((p(i-1,j) + p(i,j-1)) / 2));
        }
    }

    public int findIndex(int[] list,int a,int b){
        System.out.println("Counter:" + counter++);
        if (a == b){
            return a;
        }

        if ((b-a) == 1){
            return (list[a]>list[b] ? a:b);
        }

        int k = ((b-a)/2)+a;

        if (list[k] < list[k-1]){
            return findIndex(list,a,k);
        }else {
            return findIndex(list,k,b);
        }

    }

    private int[] createArray(){
        int[] array = new int[201];
        for (int i = 0; i < 100; i++) {
            array[i] = i;
            System.out.print(i + ",");
        }
        System.out.println("");
        for (int i = 200; i > 100; i--) {
            array[i] = 200 - i;
            System.out.print(i + ",");
        }
        return array;
    }
}
