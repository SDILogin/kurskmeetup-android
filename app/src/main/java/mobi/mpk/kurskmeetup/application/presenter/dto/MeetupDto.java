package mobi.mpk.kurskmeetup.application.presenter.dto;

import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import mobi.mpk.kurskmeetup.domain.dto.Meetup;
import mobi.mpk.kurskmeetup.domain.dto.Topic;

public class MeetupDto {
    private final static DateFormat dateFormat =
            new SimpleDateFormat("HH:mm d MMMM yyyy", Locale.getDefault());

    private String place;
    private String topics;
    private String datetime;

    /**
     * Configure MeetupDto instance with Meetup instance
     *
     * @param meetup data source
     * @return new MeetupDto instance, that configured with meetup instance fields
     */
    public static MeetupDto from(@NonNull Meetup meetup) {
        if (meetup == null) {
            throw new RuntimeException("Meetup is null");
        }

        MeetupDto meetupDtoInstance = new MeetupDto();

        // place
        meetupDtoInstance.setPlace(meetup.getPlace());

        // topics
        List<Topic> listOfTopics = meetup.getTopics();
        if (listOfTopics == null || listOfTopics.isEmpty()) {
            meetupDtoInstance.setTopics("Topics not set");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            for (Topic topic : listOfTopics) {
                stringBuilder.append(topic.getTitle());
                stringBuilder.append("\n");
            }

            meetupDtoInstance.setTopics(stringBuilder.toString().trim());
        }

        // date
        Date date = meetup.getDatetime();
        if (date != null) {
            meetupDtoInstance.setDatetime(dateFormat.format(date));
        } else {
            meetupDtoInstance.setDatetime("Date not set");
        }

        return meetupDtoInstance;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

}
