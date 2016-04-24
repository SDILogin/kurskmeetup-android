package mobi.mpk.kurskmeetup.domain;

import java.util.List;

import mobi.mpk.kurskmeetup.data.OnDataLoadListener;
import mobi.mpk.kurskmeetup.domain.dto.People;

public interface PeopleService {

    List<People> getPeople();
    void registerPeopleObserver(OnDataLoadListener<List<People>> observer);
    void unregisterPeopleObserver(OnDataLoadListener<List<People>> observer);

}
