package mobi.mpk.kurskmeetup.application;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import mobi.mpk.kurskmeetup.data.AsyncRepository;
import mobi.mpk.kurskmeetup.data.OnDataLoadListener;
import mobi.mpk.kurskmeetup.domain.MeetupsService;
import mobi.mpk.kurskmeetup.domain.dto.Meetup;

public class ApiMeetupsService implements MeetupsService {
    private AsyncRepository repository;
    private List<Meetup> meetups;
    private List<OnDataLoadListener<List<Meetup>>> observers;

    @Inject
    public ApiMeetupsService(AsyncRepository repository) {
        this.repository = repository;
        observers = new LinkedList<>();
    }

    @Override
    public List<Meetup> getMeetups() {
        repository.getMeetups(new UpdatingCallback());
        return meetups;
    }

    @Override
    public void registerObserver(OnDataLoadListener<List<Meetup>> observer) {
        observers.add(observer);
    }

    @Override
    public void unregisterObserver(OnDataLoadListener<List<Meetup>> observer) {
        observers.remove(observer);
    }

    private class UpdatingCallback implements OnDataLoadListener<List<Meetup>> {

        @Override
        public void onSuccess(List<Meetup> data) {
            meetups = data;
            notifySuccessObservers(data);
        }

        @Override
        public void onFailure(Throwable throwable) {
            notifyFailureObservers(throwable);
        }
    }

    private void notifySuccessObservers(List<Meetup> data) {
        for (OnDataLoadListener<List<Meetup>> observer : observers) {
            observer.onSuccess(data);
        }
    }

    private void notifyFailureObservers(Throwable throwable) {
        for (OnDataLoadListener<List<Meetup>> observer : observers) {
            observer.onFailure(throwable);
        }
    }

}
