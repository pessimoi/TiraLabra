package tiralabra;

/**
 * Logiikka kaikelle
 *
 * @author Pessi Moilanen
 */
public class Logiikka {

    private static Node[][] pohja;
    private static char[][] kartta;
    private static char[][] path;
    private static String steps;
    private static int type;

    /**
     * Alustaa asiat
     *
     * @param kartta Sisältää char[][]tyyppisen kartan, josta reitti tullaan
     * etsimään
     * @param tyyppi Sisältää tiedon siitä mitä algoritmia tullaan käyttämään,
     * Dijkstraa vai A*
     */
    public Logiikka(char[][] kartta, int tyyppi) {
        this.kartta = kartta;
        this.path = new char[kartta.length][kartta[0].length];
        this.steps = "";
        this.pohja = luoPohja(kartta);
        this.type = tyyppi;
    }

    /**
     * Laittaa halutun hakualgoritmin pyörimään, alkaa käymään karttaa läpi,
     * hyödyntän priorityqueuea
     */
    public void kaynnisty() {

        int koko = (kartta.length * kartta[0].length);
        PriorityQueue jono = new PriorityQueue(koko, type);
        Node solmu = pohja[0][0];
        jono.lisaa(solmu);

        while (true) {
            solmu = jono.pop();

            int nytY = solmu.getY();
            int nytX = solmu.getX();

            if (nytY == pohja.length - 1 && nytX == pohja[0].length - 1) {
                System.out.println("LÖYTYI: " + solmu.getReitti());
                break;
            }

            //ALAS
            if (nytY < kartta.length - 1 && kartta[nytY + 1][nytX] != '#' && pohja[nytY + 1][nytX].getValue() == Integer.MAX_VALUE) {
                Node alas = pohja[nytY + 1][nytX];
                alas.setReitti(solmu.getReitti(), 'A');
                alas.setValue(solmu.getValue() + 1);
                jono.lisaa(alas);
            }

            //OIKEA
            if (nytX < kartta[0].length - 1 && kartta[nytY][nytX + 1] != '#' && pohja[nytY][nytX + 1].getValue() == Integer.MAX_VALUE) {
                Node oikea = pohja[nytY][nytX + 1];
                oikea.setReitti(solmu.getReitti(), 'O');
                oikea.setValue(solmu.getValue() + 1);
                jono.lisaa(oikea);
            }

            //VASEN
            if (nytX > 0 && kartta[nytY][nytX - 1] != '#' && pohja[nytY][nytX - 1].getValue() == Integer.MAX_VALUE) {
                Node vasen = pohja[nytY][nytX - 1];
                vasen.setReitti(solmu.getReitti(), 'V');
                vasen.setValue(solmu.getValue() + 1);
                jono.lisaa(vasen);
            }
            //YLÖS
            if (nytY > 0 && kartta[nytY - 1][nytX] != '#' && pohja[nytY - 1][nytX].getValue() == Integer.MAX_VALUE) {
                Node ylos = pohja[nytY - 1][nytX];
                ylos.setReitti(solmu.getReitti(), 'Y');
                ylos.setValue(solmu.getValue() + 1);
                jono.lisaa(ylos);
//                System.out.println("Node: " + solmu.getValue());
            }
            if (jono.isEmpty()) {
                break;
            }
        }
    }

    /**
     * Luo Node[][], jonka avulla algoritmi tulee toimimaan.
     *
     * @param kartta Sisältää char[][]tyyppisen kartan, josta reitti tullaan
     * etsimään
     * @return palauttaa Node[][], joka alustettu käymällä läpi kartta
     */
    private Node[][] luoPohja(char[][] kartta) {
        Node[][] pohja = new Node[kartta.length][kartta[0].length];
        for (int i = 0; i < kartta.length; i++) {
            for (int j = 0; j < kartta[0].length; j++) {
                Node roska = new Node(i, j, kartta.length - 1, kartta[0].length - 1);
                pohja[i][j] = roska;
                if (i == 0 && j == 0) {
                    roska.setValue(0);
                }
            }
        }

        return pohja;
    }

    /**
     * Piirtää lyhimmän reitin samalle pohjalle, mistä lyhintä reittiä
     * yritettiin hakea
     */
    public void createPath() {
        Node vika = pohja[pohja.length - 1][pohja[0].length - 1];
        String reitti = vika.getReitti();

        char[][] perkele = new char[kartta.length][kartta[0].length];

        for (int i = 0; i < kartta.length; i++) {
            for (int j = 0; j < kartta[0].length; j++) {
                perkele[i][j] = kartta[i][j];
            }
        }

        int x = 0;
        int y = 0;

        for (int i = 0; i < reitti.length(); i++) {
            //ylös
            if (reitti.charAt(i) == 'Y') {
                perkele[y][x] = '|';
                y--;
            }

            //alas
            if (reitti.charAt(i) == 'A') {
                perkele[y][x] = '|';
                y++;
            }

            //oikea
            if (reitti.charAt(i) == 'O') {
                perkele[y][x] = '-';
                x++;
            }

            //vasen
            if (reitti.charAt(i) == 'V') {
                perkele[y][x] = '-';
                x--;
            }
        }
        perkele[0][0] = '*';
        perkele[y][x] = '*';
        printPath(perkele);
    }

    /**
     * Tulostaa kartan
     *
     * @param kartta
     */
    public void printPath(char[][] kartta) {

        int height = kartta.length;
        int width = kartta[0].length;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(kartta[i][j]);
            }
            System.out.println("");
        }
    }
}
