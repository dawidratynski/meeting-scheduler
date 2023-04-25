public class TimeBlockParser {

    public int getTimeFromString(String timeString){
        return Integer.parseInt(timeString.substring(3,5)) + 60 * Integer.parseInt(timeString.substring(0,2));
    }

    public IntPair parseMeetingFromBlockToIntPair(TimeBlock meeting){
        return new IntPair(getTimeFromString(meeting.start()), getTimeFromString(meeting.end()));
    }

    public TimeBlock parseMeetingFromIntPairToTimeBlock(IntPair meeting){
        String start = String.format("%02d", meeting.x / 60) + ":" + String.format("%02d", meeting.x % 60);
        String end = String.format("%02d", meeting.y / 60) + ":" + String.format("%02d", meeting.y % 60);
        return new TimeBlock(start, end);
    }
}
