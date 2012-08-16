package tiralabra;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Saa ohjelman käyntiin
 *
 * @author Pessi Moilanen
 */
public class TiraLabra {

    /**
     * Suorittaa alkukyselyt ja suoritusta
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        MapCreator mapCreator;
        char[][] map;



        System.out.println("Moi! ");

        System.out.println("Anna korkeus: ");
        int height = Integer.parseInt(scanner.nextLine());

        System.out.println("Anna leveys: ");
        int width = Integer.parseInt(scanner.nextLine());

        System.out.println("Jos haluat syöttää oman kartan, paina 'Enter' ---> Muulloin generoidaan automaattisesti!");
        String input = scanner.nextLine();

        if (input.length() == 0) {
            mapCreator = new MapCreator(height, width);
            map = mapCreator.creatOwn();

        } else {
            System.out.println("Anna arvottavien tyhjien kohtien määrä pinta-alasta prosenteissa 0-100");
            int empty = Integer.parseInt(scanner.nextLine());
            mapCreator = new MapCreator(height, width, empty);
            map = mapCreator.generateMap();
        }

        System.out.println();
        System.out.println();
        System.out.println("Dijkstra");
        System.out.println();


        Logic dijkstra = new Logic(map, 1);
        long start = System.nanoTime();
        dijkstra.kaynnisty();
        long end = System.nanoTime();
        long elapsedTime = end - start;
        double seconds = (double) elapsedTime / 1000000000.0;
        dijkstra.createPath();
        System.out.println("Aikaa kulunut: " + seconds + "s");


        System.out.println();
        System.out.println();
        System.out.println("A*");
        System.out.println();


        Logic star = new Logic(map, 0);
        start = System.nanoTime();
        star.kaynnisty();
        end = System.nanoTime();
        elapsedTime = end - start;
        seconds = (double) elapsedTime / 1000000000.0;
        star.createPath();
        System.out.println("Aikaa kulunut: " + seconds + "s");

    }
}