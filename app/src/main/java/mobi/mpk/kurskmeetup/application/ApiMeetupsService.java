package mobi.mpk.kurskmeetup.application;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mobi.mpk.kurskmeetup.data.AsyncRepository;
import mobi.mpk.kurskmeetup.domain.MeetupsService;
import mobi.mpk.kurskmeetup.domain.dto.Meetup;
import rx.Observable;

public class ApiMeetupsService implements MeetupsService {
    private AsyncRepository repository;
    private List<Meetup> meetups = new ArrayList<>();

    @Inject
    public ApiMeetupsService(AsyncRepository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<List<Meetup>> getMeetups() {
        return Observable.merge(
                getCachedData(),
                repository.getMeetups().doOnNext(this::save)
        );
    }

    private Observable<List<Meetup>> getCachedData() {
        return Observable.just(meetups);
    }

    private void save(List<Meetup> data) {
        if (data != null) {
            meetups = data;
        }
    }
}
