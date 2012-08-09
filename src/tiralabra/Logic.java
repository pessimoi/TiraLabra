package tiralabra;

/**
 * Logic kaikelle
 *
 * @author Pessi Moilanen
 */
public class Logic {

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
    public Logic(char[][] kartta, int tyyppi) {
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

            tarkastaAlas(nytY, nytX, solmu, jono);
            tarkastaOikea(nytX, nytY, solmu, jono);
            tarkastaVasen(nytX, nytY, solmu, jono);
            tarkastaYlos(nytY, nytX, solmu, jono);

            if (jono.isEmpty()) {
                break;
            }
        }
    }

    /**
     * Katsoo kyseisen solmun yläpuolelle
     *
     * @param nytY Tämänhetkisen solmun y-sijainti
     * @param nytX Tämänhetkisen solmun x-sijainti
     * @param solmu Tämänhetkinen solmu
     * @param jono Tärkeysjono
     */
    private void tarkastaYlos(int nytY, int nytX, Node solmu, PriorityQueue jono) {

        if (nytY > 0 && kartta[nytY - 1][nytX] != '#' && pohja[nytY - 1][nytX].getValue() == Integer.MAX_VALUE) {
            Node ylos = pohja[nytY - 1][nytX];
            ylos.setReitti(solmu.getReitti(), 'Y');
            ylos.setValue(solmu.getValue() + 1);
            jono.lisaa(ylos);
        }
    }

    /**
     * Katsoo kyseisen solmun vasemmallepuolelle
     *
     * @param nytY Tämänhetkisen solmun y-sijainti
     * @param nytX Tämänhetkisen solmun x-sijainti
     * @param solmu Tämänhetkinen solmu
     * @param jono Tärkeysjono
     */
    private void tarkastaVasen(int nytX, int nytY, Node solmu, PriorityQueue jono) {
        if (nytX > 0 && kartta[nytY][nytX - 1] != '#' && pohja[nytY][nytX - 1].getValue() == Integer.MAX_VALUE) {
            Node vasen = pohja[nytY][nytX - 1];
            vasen.setReitti(solmu.getReitti(), 'V');
            vasen.setValue(solmu.getValue() + 1);
            jono.lisaa(vasen);
        }
    }

    /**
     * Katsoo kyseisen solmun oikeallepuolelle
     *
     * @param nytY Tämänhetkisen solmun y-sijainti
     * @param nytX Tämänhetkisen solmun x-sijainti
     * @param solmu Tämänhetkinen solmu
     * @param jono Tärkeysjono
     */
    private void tarkastaOikea(int nytX, int nytY, Node solmu, PriorityQueue jono) {
        if (nytX < kartta[0].length - 1 && kartta[nytY][nytX + 1] != '#' && pohja[nytY][nytX + 1].getValue() == Integer.MAX_VALUE) {
            Node oikea = pohja[nytY][nytX + 1];
            oikea.setReitti(solmu.getReitti(), 'O');
            oikea.setValue(solmu.getValue() + 1);
            jono.lisaa(oikea);
        }
    }

    /**
     * Katsoo kyseisen solmun alapuolelle
     *
     * @param nytY Tämänhetkisen solmun y-sijainti
     * @param nytX Tämänhetkisen solmun x-sijainti
     * @param solmu Tämänhetkinen solmu
     * @param jono Tärkeysjono
     */
    private void tarkastaAlas(int nytY, int nytX, Node solmu, PriorityQueue jono) {
        if (nytY < kartta.length - 1 && kartta[nytY + 1][nytX] != '#' && pohja[nytY + 1][nytX].getValue() == Integer.MAX_VALUE) {
            Node alas = pohja[nytY + 1][nytX];
            alas.setReitti(solmu.getReitti(), 'A');
            alas.setValue(solmu.getValue() + 1);
            jono.lisaa(alas);
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

        char[][] reittiKartta = new char[kartta.length][kartta[0].length];

        for (int i = 0; i < kartta.length; i++) {
            for (int j = 0; j < kartta[0].length; j++) {
                reittiKartta[i][j] = kartta[i][j];
            }
        }

        int x = 0;
        int y = 0;

        for (int i = 0; i < reitti.length(); i++) {
            y = ylos(reitti, i, reittiKartta, y, x);
            y = alas(reitti, i, reittiKartta, y, x);
            x = oikea(reitti, i, reittiKartta, y, x);
            x = vasen(reitti, i, reittiKartta, y, x);
        }
        reittiKartta[0][0] = '*';
        reittiKartta[y][x] = '*';
        printPath(reittiKartta);
    }

    /**
     * Katsoo onko reitin tietyssä kohdassa menty vasemmalle
     *
     * @param reitti Kuljettu reitti
     * @param i Reitin askeleen indeksi
     * @param reittiKartta Kartta johon lyhin reitti piirretään
     * @param y taulukon korkeusindeksi
     * @param x taulukon leveysindeksi
     * @return uusi leveysinfrkdi
     */
    private int vasen(String reitti, int i, char[][] reittiKartta, int y, int x) {
        if (reitti.charAt(i) == 'V') {
            reittiKartta[y][x] = '-';
            x--;
        }
        return x;
    }

    /**
     * Katsoo onko reitin tietyssä kohdassa menty ylös
     *
     * @param reitti Kuljettu reitti
     * @param i Reitin askeleen indeksi
     * @param reittiKartta Kartta johon lyhin reitti piirretään
     * @param y taulukon korkeusindeksi
     * @param x taulukon leveysindeksi
     * @return uusi leveysinfrkdi
     */
    private int ylos(String reitti, int i, char[][] reittiKartta, int y, int x) {
        if (reitti.charAt(i) == 'Y') {
            reittiKartta[y][x] = '|';
            y--;
        }
        return y;
    }

    /**
     * Katsoo onko reitin tietyssä kohdassa menty alas
     *
     * @param reitti Kuljettu reitti
     * @param i Reitin askeleen indeksi
     * @param reittiKartta Kartta johon lyhin reitti piirretään
     * @param y taulukon korkeusindeksi
     * @param x taulukon leveysindeksi
     * @return uusi leveysinfrkdi
     */
    private int alas(String reitti, int i, char[][] reittiKartta, int y, int x) {
        if (reitti.charAt(i) == 'A') {
            reittiKartta[y][x] = '|';
            y++;
        }
        return y;
    }

    /**
     * Katsoo onko reitin tietyssä kohdassa menty oikealle
     *
     * @param reitti Kuljettu reitti
     * @param i Reitin askeleen indeksi
     * @param reittiKartta Kartta johon lyhin reitti piirretään
     * @param y taulukon korkeusindeksi
     * @param x taulukon leveysindeksi
     * @return uusi leveysinfrkdi
     */
    private int oikea(String reitti, int i, char[][] reittiKartta, int y, int x) {
        if (reitti.charAt(i) == 'O') {
            reittiKartta[y][x] = '-';
            x++;
        }
        return x;
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
