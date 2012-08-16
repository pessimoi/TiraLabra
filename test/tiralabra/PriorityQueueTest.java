package tiralabra;

import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Pessi Moilanen
 */
public class PriorityQueueTest {

    private PriorityQueue priorityQueue;
    private Node node;
    private int size, type;
    
    @Before
    public void setUpClass() throws Exception {
        type = 0;
        size = 25;
        priorityQueue = new PriorityQueue(size, type);
        node = new Node(0,0,5,5);
    }
    
    @Test
    public void isempty_returns_true_when_empty_when_created(){
        assertEquals(true, priorityQueue.isEmpty());
    }
    
    @Test
    public void isempty_returns_false_when_not_empty(){
        priorityQueue.lisaa(node);
        assertEquals(false, priorityQueue.isEmpty());
    }

    @Test
    public void isempty_returns_true_when_added_node_and_then_popped(){
        priorityQueue.lisaa(node);
        priorityQueue.pop();
        assertEquals(true,priorityQueue.isEmpty());
    }
}
