package tiralabra;

public class Star {

    private static char[][] kartta;

    public Star(char[][] kartta) {
        this.kartta = kartta;
    }

    public void kaynnisty() {
        int[][] pohja = luoPohja(kartta);
    }

    private int[][] luoPohja(char[][] kartta) {
        int[][] pohja = new int[kartta.length][kartta[0].length];
        for (int i = 0; i < kartta.length; i++) {
            for (int j = 0; j < kartta[0].length; j++) {
                pohja[i][j] = Integer.MAX_VALUE;
            }
        }
        pohja[0][0] = 0;
        return pohja;
    }
}
