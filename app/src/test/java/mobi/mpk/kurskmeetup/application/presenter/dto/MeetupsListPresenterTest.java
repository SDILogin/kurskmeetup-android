package mobi.mpk.kurskmeetup.application.presenter.dto;

import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import mobi.mpk.kurskmeetup.application.presenter.MeetupsListPresenter;
import mobi.mpk.kurskmeetup.domain.dto.Meetup;
import mobi.mpk.kurskmeetup.domain.dto.Topic;

import static org.junit.Assert.*;

public class MeetupsListPresenterTest {
    private MeetupsListPresenter presenter;

    public MeetupsListPresenterTest() {
        presenter = new MeetupsListPresenter();
    }

    @Test
    public void testGetDto() throws Exception {
        Meetup meetup = new Meetup();
        meetup.setPlace("Place text");
        List<Topic> topics = new LinkedList<>();
        for (int i = 1; i <= 2; i++) {
            Topic topic = new Topic();
            topic.setTitle("Title " + i);
            topics.add(topic);
        }
        meetup.setTopics(topics);
        GregorianCalendar date = new GregorianCalendar(2010, 9, 8, 7, 6, 5);
        meetup.setDatetime(date.getTime());

        MeetupDto dto = presenter.getDto(meetup);

        assertEquals("Place text", dto.getPlace());
        assertEquals("Title 1\nTitle 2", dto.getTopics());
        assertEquals("07:06 8 октября 2010", dto.getDatetime());
    }
}