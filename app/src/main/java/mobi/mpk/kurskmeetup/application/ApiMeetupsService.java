package mobi.mpk.kurskmeetup.application;

import java.util.LinkedList;
import java.util.List;

import mobi.mpk.kurskmeetup.data.AsyncRepository;
import mobi.mpk.kurskmeetup.data.OnDataLoadListener;
import mobi.mpk.kurskmeetup.domain.MeetupsService;
import mobi.mpk.kurskmeetup.domain.dto.Meetup;
import mobi.mpk.kurskmeetup.domain.dto.People;

public class ApiMeetupsService implements MeetupsService {
    private AsyncRepository repository;
    private List<Meetup> meetups;
    private static List<People> people;
    private List<OnDataLoadListener<List<Meetup>>> observers;

    // TODO replace by real implementation
    static {
        people = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            People people1 = new People();
            people1.setName("Full Name " + i);
            people.add(people1);
        }
    }

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
    public List<People> getPeople() {
        return people;
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
