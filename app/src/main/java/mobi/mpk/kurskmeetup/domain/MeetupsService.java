package mobi.mpk.kurskmeetup.domain;

import java.util.List;

import mobi.mpk.kurskmeetup.data.OnDataLoadListener;
import mobi.mpk.kurskmeetup.domain.dto.Meetup;
import rx.Observable;

public interface MeetupsService {
    Observable<List<Meetup>> getMeetups();
}
