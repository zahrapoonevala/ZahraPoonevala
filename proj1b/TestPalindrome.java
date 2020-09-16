import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }


    @Test
    public void testPalindrome() {
        assertTrue(palindrome.isPalindrome("racecar"));
        assertTrue(palindrome.isPalindrome("madam"));
        assertTrue(palindrome.isPalindrome("fiif"));
        assertTrue(palindrome.isPalindrome("BBaBB"));
    }

    @Test
    public void testNotPalindrome() {
        assertFalse(palindrome.isPalindrome("horse"));
        assertFalse(palindrome.isPalindrome("berkeley"));
        assertFalse(palindrome.isPalindrome("fiiifif"));
        assertFalse(palindrome.isPalindrome("Bb"));
    }

    @Test
    public void testCornerCases() {
        assertTrue(palindrome.isPalindrome("A"));
        assertTrue(palindrome.isPalindrome(""));

    }

    @Test
    public void testPalindromeObo() {
        OffByOne obo = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake", obo));
        assertTrue(palindrome.isPalindrome("adghcb", obo));
        assertTrue(palindrome.isPalindrome("xyxy", obo));
        assertTrue(palindrome.isPalindrome("bbac", obo));

    }

    @Test
    public void testNotPalindromeObo() {
        OffByOne obo = new OffByOne();
        assertFalse(palindrome.isPalindrome("zz", obo));
        assertFalse(palindrome.isPalindrome("zyzyzx", obo));
        assertFalse(palindrome.isPalindrome("horse", obo));
    }

    @Test
    public void testCornerCasesObo() {
        OffByOne obo = new OffByOne();
        assertTrue(palindrome.isPalindrome("A", obo));
        assertTrue(palindrome.isPalindrome("", obo));

    }


}