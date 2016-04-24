package mobi.mpk.kurskmeetup.data;

import java.util.List;

import mobi.mpk.kurskmeetup.domain.dto.Meetup;
import mobi.mpk.kurskmeetup.domain.dto.People;

public interface AsyncRepository {

    void getMeetups(OnDataLoadListener<List<Meetup>> callback);
    void getPeople(OnDataLoadListener<List<People>> callback);

}
