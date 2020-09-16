public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> stringWord = new LinkedListDeque<Character>();
        char[] wordArray = word.toCharArray();

        for (char i : wordArray) {
            stringWord.addLast(i);
        }

        return stringWord;
    }

    public boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }
        Deque<Character> fixedWord1 = wordToDeque(word);
        int counter = 0;

        if (fixedWord1.size() == 0 || fixedWord1.size() == 1) {
            return true;
        }

        while (counter < fixedWord1.size()) {
            if (fixedWord1.removeFirst() != fixedWord1.removeLast()) {
                return false;
            }

            counter++;
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null) {
            return false;
        }
        Deque<Character> fixedWord = wordToDeque(word);
        int counter = 0;

        if (fixedWord.size() == 0 || fixedWord.size() == 1) {
            return true;
        }

        while (counter < fixedWord.size()) {
            if (!cc.equalChars(fixedWord.removeFirst(), fixedWord.removeLast())) {
                return false;
            }

            counter++;
        }

        return true;
    }
}
