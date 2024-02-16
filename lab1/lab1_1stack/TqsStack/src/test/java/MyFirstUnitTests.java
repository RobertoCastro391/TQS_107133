import UA.DETI.TQS.TqsStack;
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyFirstJUnitJupiterTests {
    // Exercício 1.1
    // Testes para a classe TqsStack

    private TqsStack<Integer> stack;
    
    @BeforeEach
    void init() {
        stack = new TqsStack<Integer>();
    }
    
    // a)
    @Test
    void testEmpty() {
        assertEquals(true, stack.isEmpty());
    }

    // b)
    @Test
    void testSize() {
        assertEquals(0, stack.size());
    }

    // c)
    @Test
    void testPush() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(false, stack.isEmpty());
        assertEquals(3, stack.size());
    }

    // d)
    @Test
    void testPushAndPop() {
        stack.push(1);
        assertEquals(1, stack.pop());
    }

    // e)
    @Test
    void testPushAndPeeks() {
        Integer x = 1;
        stack.push(x);
        Integer size = stack.size();
        assertEquals(x, stack.peek());
        assertEquals(size, stack.size());
    }

    // f)
    @Test
    void testPushAndPopAndSize() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        stack.pop();
        stack.pop();
        stack.pop();

        assertEquals(true, stack.isEmpty());
        assertEquals(0, stack.size());
    }

    // g)
    @Test
    void tryPopFromEmptyStack() {
        assertThrows(NoSuchElementException.class, () -> {
            stack.pop();
        });
    }

    // h)
    @Test
    void tryPeekFromEmptyStack() {
        assertThrows(NoSuchElementException.class, () -> {
            stack.peek();
        });
    }

    // i)
    @Test
    void testPushAndPopAndSizeMax() {
        TqsStack<Integer> stack = new TqsStack<Integer>(2);
        stack.push(1);
        stack.push(2);
        
        assertThrows(IllegalStateException.class, () -> {
            stack.push(3);
        });
    }
}