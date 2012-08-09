package tiralabra;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Pessi Moilanen
 */
public class NodeTest {

    private Node node;
    private int y, x, h, w;

    @Before
    public void setUp() throws Exception {
        y = 0;
        x = 1;
        h = 2;
        w = 3;
        node = new Node(y, x, h, w);
    }

    @Test
    public void y_should_be_same_than_constructor_parameter() {
        assertEquals(y, node.getY());
    }

    @Test
    public void x_should_be_same_than_constructor_parameter() {
        assertEquals(x, node.getX());
    }

    @Test
    public void default_value_should_be_integer_max_value() {
        assertEquals(Integer.MAX_VALUE, node.getValue());
    }

    @Test
    public void default_reitti_should_be_empty_string() {
        assertEquals("", node.getReitti());
    }

    @Test
    public void togo_should_calculate_nicely() {
        int distance = ((h - y) + (w - x));
        assertEquals(distance, node.getDistanceToCorner());
    }

    @Test
    public void setting_value_should_change_value() {
        int newValue = 5;
        node.setValue(newValue);
        assertEquals(newValue, node.getValue());
    }

    @Test
    public void setting_reitti_should_change_reitti() {
        String aiempi = "OA";
        char askel = 'A';
        String uusi = aiempi + askel;
        node.setReitti(aiempi, askel);
        assertEquals(uusi, node.getReitti());
    }
}