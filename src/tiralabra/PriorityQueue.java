package tiralabra;

/**
 * Tärkeysjono
 *
 * @author Pessi Moilanen
 */
public class PriorityQueue {

    private int koko;
    private Node[] jono;
    private int type;

    /**
     * Alustaa asiat
     *
     * @param size Jonon koko
     * @param type Jonon tyyppi, erilainen käyttäytyminen Dijkstrassa ja A*
     */
    public PriorityQueue(int size, int type) {
        this.koko = 0;
        this.jono = new Node[koko];
        this.type = type;
    }

    /**
     * Lisää jonoon jäsenen
     *
     * @param node Uusi jäsen joka lisätään
     */
    public void lisaa(Node node) {

        int num = 0;

        if (koko > 0) {
            while (true) {
                int uudesta = node.getValue();
                int vertaus = node.getValue();
                if (type == 1) {
                    uudesta = uudesta + node.getToGo();
                    vertaus = vertaus + jono[num].getToGo();
                }

                if (uudesta < vertaus) {
                    break;
                } else if (num == koko - 1) {
                    num++;
                    break;
                }
                num++;
            }

            Node[] kopio = new Node[jono.length];
            for (int i = 0; i < kopio.length; i++) {
                kopio[i] = jono[i];
            }

            jono = new Node[koko + 1];

            int j = 0;
            for (int i = 0; i < jono.length; i++) {
                if (i == num) {
                    jono[i] = node;
                } else {
                    jono[i] = kopio[j];
                    j++;
                }
            }

        } else {
            jono = new Node[1];
            jono[0] = node;
        }

        koko++;
    }

    /**
     * Palauttaa jonon ensimmäisen ja poistaa sen jonosta
     *
     * @return Jonon ensimmäinen jäsen
     */
    public Node pop() {
        Node palaute = jono[0];

        Node[] kopio = new Node[koko];
        for (int i = 0; i < kopio.length; i++) {
            kopio[i] = jono[i];
        }

        jono = new Node[koko - 1];
        for (int i = 1; i < kopio.length; i++) {
            jono[i - 1] = kopio[i];
        }

        koko--;
        return palaute;
    }

    /**
     * Tarkistaa onko tyhjä
     *
     * @return True tai False
     */
    public boolean isEmpty() {
        return (koko == 0);
    }

    /**
     * Tulostaa jonon
     */
    public void printQue() {
        for (int i = 0; i < jono.length; i++) {
            System.out.println(jono[i].getToGo());
        }
        if (isEmpty()) {
            System.out.println("TYHJÄ");
        }
    }
}
