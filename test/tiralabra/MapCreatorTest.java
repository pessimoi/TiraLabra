/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Pessi Moilanen
 */
public class MapCreatorTest {

    private MapCreator mapCreator;
    private int height, width, empty;

    @Before
    public void setUpClass() throws Exception {

        height = 3;
        width = 4;
        empty = 100;
        mapCreator = new MapCreator(height, width, empty);
    }

    @Test
    public void get_empty_as_set_on_constructor() {
        int amount = 100;
        assertEquals(amount, mapCreator.getEmpty());
    }

    @Test
    public void set_empty_changes_empty() {
        int amount = 200;
        mapCreator.setEmpty(amount);
        assertEquals(amount, mapCreator.getEmpty());
    }

    @Test
    public void get_height_as_set_on_constructor() {
        int h = 3;
        assertEquals(h, mapCreator.getHeight());
    }

    @Test
    public void set_height_changes_height() {
        int h = 6;
        mapCreator.setHeight(h);
        assertEquals(h, mapCreator.getHeight());
    }

    @Test
    public void get_width_as_set_on_constructor(){
        int w = 4;
        assertEquals(w, mapCreator.getWidth());
    }

    @Test
    public void set_width_changes_width(){
        int w = 8;
        mapCreator.setWidth(w);
        assertEquals(w, mapCreator.getWidth());
    }
    
}
