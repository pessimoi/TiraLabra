package tiralabra;

import java.util.*;

public class TiraLabra {

    private static Scanner lukija = new Scanner(System.in);
    private static Random arpoja = new Random();

    public static void main(String[] args) {
        System.out.println("moi");

        System.out.println("Anna korkeus: ");
        int korkeus = 4;
//        int korkeus = Integer.parseInt(lukija.nextLine());
        System.out.println("Anna leveys: ");
        int leveys = 5;
//        int leveys = Integer.parseInt(lukija.nextLine());


        System.out.println("Anna tyhjien kohtien määrä pinta-alasta prosenteissa 0-100");
        int seinia = 100;
//        int seinia = Integer.parseInt(lukija.nextLine());

        char[][] kartta = new char[korkeus][leveys];
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                kartta[i][j] = '#';
            }
        }

        kartta[0][0] = 'A';
        kartta[korkeus - 1][leveys - 1] = 'L';

        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                System.out.print(kartta[i][j]);
            }
            System.out.println("");
        }

        double laskuri = (korkeus * leveys) * (seinia * 0.01);
        System.out.println("TYHJIÄ: " + laskuri);
        while (laskuri > 0) {
            int h = arpoja.nextInt(korkeus);
            int w = arpoja.nextInt(leveys);
            if ((h != 0 || w != 0) && (h != kartta.length-1 || w != kartta[0].length-1)) {
                kartta[h][w] = '.';
            }
            laskuri--;
        }

        System.out.println("");

        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                System.out.print(kartta[i][j]);
            }
            System.out.println("");
        }

        System.out.println("");
        Star star = new Star(kartta);
        star.kaynnisty();

    }
}