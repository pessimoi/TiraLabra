package tiralabra;

public class Star {

    private static char[][] kartta;

    public Star(char[][] kartta) {
        this.kartta = kartta;
    }

    public void kaynnisty() {
        
        
        Node[][] pohja = luoPohja(kartta);
        

        int koko = (kartta.length * kartta[0].length);
        PriorityQue jono = new PriorityQue(koko);
        Node solmu = pohja[0][0];
        jono.lisaa(solmu);

        while (true) {
            solmu = jono.pop();

            int nytY = solmu.getY();
            int nytX = solmu.getX();

            if (nytY == pohja.length - 1 && nytX == pohja[0].length - 1) {
                break;
            }
            
            //ALAS
            if (nytY < kartta.length - 1 && kartta[nytY + 1][nytX] != '#' && pohja[nytY + 1][nytX].getValue() == Integer.MAX_VALUE) {
                Node alas = pohja[nytY + 1][nytX];
                alas.setValue(solmu.getValue() + 1);
                jono.lisaa(alas);
            }

            //OIKEA
            if (nytX < kartta[0].length - 1 && kartta[nytY][nytX + 1] != '#' && pohja[nytY][nytX + 1].getValue() == Integer.MAX_VALUE) {
                Node oikea = pohja[nytY][nytX + 1];
                oikea.setValue(solmu.getValue() + 1);
                jono.lisaa(oikea);
            }

            //VASEN

            //YLÖS
            System.out.println("Node: " + solmu.getValue());
            
            if (jono.isEmpty()) {
                break;
            }
        }

        Node vika = pohja[pohja.length - 1][pohja[0].length - 1];
        System.out.println("Y: " + vika.getY() + " X: " + vika.getX());
        System.out.println("MATKAN PITUUS: " + vika.getValue());
//        System.out.println("SHIT IS DONE");
//        System.out.println("");
//        Node eka = new Node(0, 0, 4, 4);
//        Node toka = new Node(1, 1, 4, 4);
//        Node kolmas = new Node(2, 2, 4, 4);
//        Node neljas = new Node(3, 3, 4, 4);
//
//        System.out.println("");
//        jono.lisaa(neljas);
//        jono.printQue();
//        System.out.println("");
//        jono.lisaa(eka);
//        jono.printQue();
//        System.out.println("");
//        jono.pop();
//        jono.pop();
//        jono.printQue();
//        System.out.println("");
//        jono.lisaa(toka);
//        jono.printQue();
//        System.out.println("");
//        jono.lisaa(kolmas);
//        jono.printQue();
//        jono.pop();
//        jono.pop();
//        jono.printQue();

//        jono.lisaa(toka);
//        jono.lisaa(toka);
    }

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
}
