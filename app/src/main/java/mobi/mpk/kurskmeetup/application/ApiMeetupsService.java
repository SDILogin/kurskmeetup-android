package mobi.mpk.kurskmeetup.application;

import java.util.LinkedList;
import java.util.List;

import mobi.mpk.kurskmeetup.data.AsyncRepository;
import mobi.mpk.kurskmeetup.data.OnDataLoadListener;
import mobi.mpk.kurskmeetup.data.Urls;
import mobi.mpk.kurskmeetup.data.retrofit.UrlRepository;
import mobi.mpk.kurskmeetup.domain.MeetupsService;
import mobi.mpk.kurskmeetup.domain.dto.Meetup;

public class ApiMeetupsService implements MeetupsService {
    private static ApiMeetupsService instance;
    private AsyncRepository repository;
    private List<Meetup> meetups;
    private List<OnDataLoadListener<List<Meetup>>> observers;

    private ApiMeetupsService(AsyncRepository repository) {
        this.repository = repository;
        observers = new LinkedList<>();
    }

    public static ApiMeetupsService getInstance(AsyncRepository repository) {
        if (instance == null) {
            instance = new ApiMeetupsService(repository);
        } else {
            if (repository != instance.repository) {
                instance.setRepository(repository);
            }
        }
        return instance;
    }

    public static ApiMeetupsService getInstance() {
        if (instance == null) {
            instance = new ApiMeetupsService(new UrlRepository(Urls.APIARY));
        }
        return instance;
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

    public void setRepository(AsyncRepository repository) {
        this.repository = repository;
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
