package mobi.mpk.kurskmeetup.domain;

import java.util.List;

import mobi.mpk.kurskmeetup.data.OnDataLoadListener;
import mobi.mpk.kurskmeetup.domain.dto.Meetup;
import mobi.mpk.kurskmeetup.domain.dto.People;

public interface MeetupsService {

    List<Meetup> getMeetups();
    List<People> getPeople();
    void registerObserver(OnDataLoadListener<List<Meetup>> observer);
    void unregisterObserver(OnDataLoadListener<List<Meetup>> observer);

}
