package mobi.mpk.kurskmeetup.domain;

import java.util.List;

import mobi.mpk.kurskmeetup.data.OnDataLoadListener;
import mobi.mpk.kurskmeetup.domain.dto.Meetup;

public interface MeetupsService {

    List<Meetup> getMeetups();
    void registerObserver(OnDataLoadListener<List<Meetup>> observer);
    void unregisterObserver(OnDataLoadListener<List<Meetup>> observer);

}
