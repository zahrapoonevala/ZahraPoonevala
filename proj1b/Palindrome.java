public class Palindrome {

    public Deque<Character> wordToDeque(String word){
        LinkedListDeque<Character> stringWord = new LinkedListDeque<Character>();
        char[] wordArray = word.toCharArray();

        for(char i: wordArray ){
            stringWord.addLast(i);
        }

        return stringWord;
    }

    public boolean isPalindrome(String word){
        Deque<Character> fixedWord = wordToDeque(word);
        int counter = 0;
        if(fixedWord.size() == 0 || fixedWord.size() == 1){
            return true;
        }else if (word == null){
            return false;
        }
        while(counter < fixedWord.size() ){
            if (fixedWord.removeFirst() != fixedWord.removeLast()){
                return false;
            }

            counter ++;
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> fixedWord = wordToDeque(word);
        int counter = 0;
        if(fixedWord.size() == 0 || fixedWord.size() == 1){
            return true;
        }else if (word == null){
            return false;
        }
        while(counter < fixedWord.size() ){
            if (!cc.equalChars(fixedWord.removeFirst(), fixedWord.removeLast())) {
                return false;
            }

            counter++;
        }

        return true;
    }
}
