package tiralabra;

public class Node {

    private int x;
    private int y;
    private int toGo;
    private int value;
    private String reitti;

    public Node(int y, int x, int h, int w) {
        this.x = x;
        this.y = y;
        this.value = Integer.MAX_VALUE;
        this.toGo = (h - y) + (w - x);
        this.reitti = "";
    }

    public void setValue(int val) {
        this.value = val;
    }

    public int getValue() {
        return value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getToGo() {
        return toGo + reitti.length();
    }

    public String getReitti() {
        return reitti;
    }

    public void setReitti(String aiempi, char suunta) {
        reitti = aiempi + suunta;
    }
}
