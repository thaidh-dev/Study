package lab.pkg5;

public class baithem2 {
    public static void main(String[] args) {
        int thai[][] = {{1,2,3,4}, {2,3,4,5}, {3,4,5,6}, {4,5,6,7}};
        int z = 3;
        for (int x = thai.length - 1; x >= 0; x -= 1) {
            for (int y = 0; y < thai[x].length - z; y++) {
                System.out.print(""+thai[x][y]);
                System.out.print(" ");
            }
            System.out.println("");
            z -= 1;
        }
    }
    
}
