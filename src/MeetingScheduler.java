import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MeetingScheduler {

    private final TimeBlockParser parser = new TimeBlockParser();

    private boolean isSorted(List<IntPair> tab){
        for(int i = 0; i < tab.size() - 1; i++){
            if(tab.get(i).compareTo(tab.get(i+1)) > 0){
                return false;
            }
        }
        return true;
    }

    private List<IntPair> mergeUnavailableTimes(List<IntPair> a, List<IntPair> b){
        List<IntPair> result = new ArrayList<>();

        int a_index = 0;
        int b_index = 0;
        while(a_index < a.size() || b_index < b.size()){
            if(a_index < a.size() && (b_index == b.size() || a.get(a_index).x <= b.get(b_index).x)){
                result.add(a.get(a_index));
                a_index++;
            }
            else{
                result.add(b.get(b_index));
                b_index++;
            }
        }

        // This is only needed in case planned_meeting isn't sorted
        if(!isSorted(result)){
            Collections.sort(result);
        }


        // Merge overlapping unavailable blocks
        List<IntPair> result_squished = new ArrayList<>();

        for(IntPair meet : result){
            if(result_squished.isEmpty() || result_squished.get(result_squished.size() - 1).y < meet.x){
                result_squished.add(meet);
            }
            else {
                result_squished.get(result_squished.size() - 1).y = Integer.max(result_squished.get(result_squished.size() - 1).y, meet.y);
            }
        }

        return result_squished;
    }

    private List<IntPair> getAvailableTimes(List<IntPair> blocks, int duration){
        List<IntPair> result = new ArrayList<>();
        for(int ix = 0; ix < blocks.size() - 1; ix++){
            int startTime = blocks.get(ix).y;
            if(startTime + duration <= blocks.get(ix + 1).x){
                result.add(new IntPair(startTime, blocks.get(ix + 1).x));
            }
        }
        return result;
    }

    private List<TimeBlock> findAllAvailableMeetingsAsList(Calendar a, Calendar b, String duration_string){
        int duration = parser.getTimeFromString(duration_string);
        List<IntPair> unavailableTimeSlots = mergeUnavailableTimes(a.getUnavailableTimesAsIntPairList(parser), b.getUnavailableTimesAsIntPairList(parser));
        List<IntPair> availableTimeSlots = getAvailableTimes(unavailableTimeSlots, duration);

        List<TimeBlock> result = new ArrayList<>();
        for(IntPair x : availableTimeSlots){
            result.add(parser.parseMeetingFromIntPairToTimeBlock(x));
        }

        return result;
    }

    public String findAllAvailableMeetings(Calendar a, Calendar b, String duration_string){
        return findAllAvailableMeetingsAsList(a, b, duration_string).toString();
    }
}
