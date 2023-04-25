public record TimeBlock(String start, String end) {

    @Override
    public String toString() {
        return "[" + start + ", " + end + "]";
    }
}
