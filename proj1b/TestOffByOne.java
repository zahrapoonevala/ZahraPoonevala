import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testTrue() {
        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('l', 'm'));
        assertTrue(offByOne.equalChars('A', 'B'));
        assertTrue(offByOne.equalChars('B', 'A'));
    }

    @Test
    public void testFalse() {
        assertFalse(offByOne.equalChars('b', 'e'));
        assertFalse(offByOne.equalChars('z', 'x'));
        assertFalse(offByOne.equalChars('d', 'd'));
        assertFalse(offByOne.equalChars('A', 'a'));
        assertFalse(offByOne.equalChars('Z', 'X'));
    }

    @Test
    public void testSpecial() {
        assertTrue(offByOne.equalChars('%', '&'));
    }
}