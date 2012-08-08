package tiralabra;

import java.util.*;

/**
 * Saa ohjelman käyntiin
 *
 * @author Pessi Moilanen
 */
public class TiraLabra {

    /**
     * Suorittaa alkukyselyt
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

        System.out.println("");
        System.out.println("");
        System.out.println("A*");
        System.out.println("");

        Logiikka star = new Logiikka(map, 0);
        star.kaynnisty();
        star.createPath();

        System.out.println("");
        System.out.println("");
        System.out.println("Dijkstra");
        System.out.println("");

        Logiikka dijkstra = new Logiikka(map, 1);
        dijkstra.kaynnisty();
        dijkstra.createPath();

    }
}