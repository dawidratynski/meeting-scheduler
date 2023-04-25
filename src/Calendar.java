import java.util.ArrayList;
import java.util.List;

public class Calendar {

    public TimeBlock working_hours;
    public List<TimeBlock> planned_meeting;

    private final int DAY_LENGTH_IN_MINUTES = 60 * 24;

    public List<IntPair> getUnavailableTimesAsIntPairList(TimeBlockParser parser){
        List<IntPair> result = new ArrayList<>();

        IntPair previousNight = parser.parseMeetingFromBlockToIntPair(working_hours);
        previousNight.y -= DAY_LENGTH_IN_MINUTES;
        result.add(previousNight.getSwapped());

        for(TimeBlock meet : planned_meeting){
            result.add(parser.parseMeetingFromBlockToIntPair(meet));
        }

        IntPair nextNight = parser.parseMeetingFromBlockToIntPair(working_hours);
        nextNight.x += DAY_LENGTH_IN_MINUTES;
        result.add(nextNight.getSwapped());

        return result;
    }
}
