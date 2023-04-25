public class IntPair implements Comparable<IntPair>{

    public int x;
    public int y;

    public IntPair(int x, int y){
        this.x = x;
        this.y = y;
    }

    public IntPair getSwapped(){
        return new IntPair(y,x);
    }

    @Override
    public int compareTo(IntPair other) {
        // For this algorithm we only need pairs sorted by first element
        return x - other.x;
    }

    @Override
    public String toString(){
        return "<" + x + ", " + y + ">";
    }
}
