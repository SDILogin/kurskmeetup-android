package mobi.mpk.kurskmeetup.domain;

import java.util.List;

import mobi.mpk.kurskmeetup.domain.dto.Meetup;

public interface MeetupsRepository {

    public List<Meetup> getMeetups();
    public Meetup getMeetup(int id);

}
