package tiralabra;

public class PriorityQue {

    private int koko;
    private Node[] jono;

    public PriorityQue(int size) {
        this.koko = 0;
        this.jono = new Node[koko];
    }

    public void lisaa(Node node) {

        int num = 0;

        if (koko > 0) {
            while (true) {
                int uudesta = node.getToGo();
                if (uudesta < jono[num].getToGo()) {
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

    public Node pop() {
        Node palaute = jono[0];

        Node[] kopio = new Node[jono.length];
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

    public boolean isEmpty() {
        if (koko == 0) {
            return true;
        }
        return false;
    }

    public void printQue() {
        for (int i = 0; i < jono.length; i++) {
            System.out.println(jono[i].getToGo());
        }
        if (isEmpty()) {
            System.out.println("TYHJÄ");
        }
    }
}
