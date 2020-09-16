public class OffByN implements CharacterComparator {
    private int N;

    public OffByN(int N) {
        this.N = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int number = Math.abs(x - y);

        if (number == N) {
            return true;
        }

        return false;
    }
}
