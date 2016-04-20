package mobi.mpk.kurskmeetup.application.presenter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import mobi.mpk.kurskmeetup.application.presenter.dto.MeetupDto;
import mobi.mpk.kurskmeetup.domain.dto.Meetup;
import mobi.mpk.kurskmeetup.domain.dto.Topic;

public class MyMeetupsPresenter implements MeetupsPresenter {
    private static DateFormat dateFormat;

    static {
        dateFormat = new SimpleDateFormat("HH:mm d MMMM yyyy", Locale.getDefault());
    }

    @Override
    public MeetupDto getDto(Meetup meetup) {
        MeetupDto dto = new MeetupDto();
        dto.setPlace(meetup.getPlace());
        StringBuilder str = new StringBuilder();
        for (Topic topic : meetup.getTopics()) {
            str.append(topic.getTitle());
            str.append("\n");
        }
        dto.setTopics(str.substring(0, str.length() - 1));
        dto.setDatetime(dateFormat.format(meetup.getDatetime()));
        return dto;
    }

}
