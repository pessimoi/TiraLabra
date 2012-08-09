package tiralabra;

/**
 * Alkio ja sen olemus
 *
 * @author Pessi Moilanen
 */
public class Node {

    private int x;
    private int y;
    private int distanceToCorner;
    private int value;
    private String reitti;

    /**
     *
     * @param y Kyseisen alkion y-koordinaatti taulukossa
     * @param x Kyseisen alkion x-koordinaatti taulukossa
     * @param h Taulukon korkeus
     * @param w Taulukon leveys
     */
    public Node(int y, int x, int h, int w) {
        this.x = x;
        this.y = y;
        this.value = Integer.MAX_VALUE;
        this.distanceToCorner = (h - y) + (w - x);
        this.reitti = "";
    }

    /**
     * Asettaa lyhimmän reitin kyseiseen alkioon
     *
     * @param val Lyhin reitti alkioon
     */
    public void setValue(int val) {
        this.value = val;
    }

    /**
     * Palauttaa lyhimmän reitin kyseiseen alkioon
     *
     * @return Lyhin reitti alkioon
     */
    public int getValue() {
        return value;
    }

    /**
     * Palauttaa alkion x-koordinaatin
     *
     * @return alkion x-koordinaatti
     */
    public int getX() {
        return x;
    }

    /**
     * Palauttaa alkion y-koordinaatin
     *
     * @return alkion y-koordinaatti
     */
    public int getY() {
        return y;
    }

    /**
     * Palauttaa Kuljetun matkan ja jäljellä olevan matkan summan
     *
     * @return
     */
    public int getDistanceToCorner() {
        return distanceToCorner + reitti.length();
    }

    /**
     * Palauttaa reitin
     *
     * @return reitti
     */
    public String getReitti() {
        return reitti;
    }

    /**
     * Päivittää reittiä kyseiseen jäsenen suunnan perusteella josta tähän on
     * saavuttu
     *
     * @param aiempi Edellisen jäsenen sisältämä reitti
     * @param suunta Edellisestä jäsenestä kyseiseen jäseneen siirryty askel
     */
    public void setReitti(String aiempi, char suunta) {
        reitti = aiempi + suunta;
    }
}
