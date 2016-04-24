package mobi.mpk.kurskmeetup.domain;

import java.util.List;

import mobi.mpk.kurskmeetup.data.OnDataLoadListener;
import mobi.mpk.kurskmeetup.domain.dto.Meetup;
import mobi.mpk.kurskmeetup.domain.dto.People;

public interface MeetupsService {

    List<Meetup> getMeetups();
    void registerMeetupsObserver(OnDataLoadListener<List<Meetup>> observer);
    void unregisterMeetupsObserver(OnDataLoadListener<List<Meetup>> observer);

}
