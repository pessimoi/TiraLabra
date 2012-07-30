package tiralabra;

import java.util.Random;
import java.util.Scanner;

public class MapCreator {

    private Random random = new Random();
    private Scanner scanner = new Scanner(System.in);
    private int height;
    private int width;
    private int empty;

    public MapCreator(int height, int width, int empty) {
        this.height = height;
        this.width = width;
        this.empty = empty;
    }

    public MapCreator(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public char[][] generateMap() {

        //CREATING THE BASE FOR THE MAP
        char[][] map = new char[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                map[i][j] = '#';
            }
        }

        map[0][0] = '*';
        map[height - 1][width - 1] = '*';

        printMap(map);


        //CREATING THE EMPTY AREAS
        double laskuri = (height * width) * (empty * 0.01);
        System.out.println("TYHJIÄ: " + laskuri);
        while (laskuri > 0) {
            int h = random.nextInt(height);
            int w = random.nextInt(width);
            if ((h != 0 || w != 0) && (h != map.length - 1 || w != map[0].length - 1)) {
                map[h][w] = '.';
            }
            laskuri--;
        }

        printMap(map);

        return map;
    }

    public char[][] creatOwn() {
        char map[][] = new char[height][width];
        System.out.println("Syötä kartan palaset yksitellen, tyhjä vastaa vapaata kohtaa, mikä tahansa muu vastaa seinää");

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                String tile = scanner.nextLine();
                if (tile.length() == 0) {
                    map[i][j] = '.';
                } else {
                    map[i][j] = '#';
                }
                printMap(map);
            }
        }
        return map;
    }

    public void printMap(char[][] map) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println("");
        }
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setEmpty(int empty) {
        this.empty = empty;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getEmpty() {
        return empty;
    }
}
