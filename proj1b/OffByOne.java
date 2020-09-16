public class OffByOne implements CharacterComparator {

    @Override
    public boolean equalChars(char x, char y) {
        int number = Math.abs(x - y);

        if (number == 1){
            return true;
        }

        return false;

    }
}
