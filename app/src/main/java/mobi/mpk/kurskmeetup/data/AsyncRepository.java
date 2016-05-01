package mobi.mpk.kurskmeetup.data;

import java.util.List;

import mobi.mpk.kurskmeetup.domain.dto.Meetup;
import rx.Observable;

public interface AsyncRepository {
    Observable<List<Meetup>> getMeetups();
    Observable<Meetup> getMeetup(int id);
}
