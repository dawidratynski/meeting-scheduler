import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Calendar calendar1 = new Calendar();

        calendar1.working_hours = new TimeBlock("09:00", "19:55");
        calendar1.planned_meeting = new ArrayList<>(Arrays.asList(
                new TimeBlock("09:00", "10:30"),
                new TimeBlock("12:00", "13:00"),
                new TimeBlock("16:00", "18:00")
        ));

        Calendar calendar2 = new Calendar();

        calendar2.working_hours = new TimeBlock("10:00", "18:30");
        calendar2.planned_meeting = new ArrayList<>(Arrays.asList(
                new TimeBlock("10:00", "11:30"),
                new TimeBlock("12:30", "14:30"),
                new TimeBlock("14:30", "15:00"),
                new TimeBlock("16:00", "17:00")
        ));

        String meeting_duration = "00:30";

        MeetingScheduler scheduler = new MeetingScheduler();
        System.out.println(scheduler.findAllAvailableMeetings(calendar1, calendar2, meeting_duration));
    }
}