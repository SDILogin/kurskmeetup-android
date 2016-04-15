package mobi.mpk.kurskmeetup.domain;

import java.util.List;

import mobi.mpk.kurskmeetup.domain.dto.Meetup;

public interface AsyncRepository {

    public void getMeetups(OnDataLoadListener<List<Meetup>> callback);
    public void getMeetup(int id, OnDataLoadListener<Meetup> callback);

}
