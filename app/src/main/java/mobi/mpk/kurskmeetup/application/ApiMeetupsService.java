package mobi.mpk.kurskmeetup.application;

import java.util.LinkedList;
import java.util.List;

import mobi.mpk.kurskmeetup.data.AsyncRepository;
import mobi.mpk.kurskmeetup.data.OnDataLoadListener;
import mobi.mpk.kurskmeetup.domain.MeetupsService;
import mobi.mpk.kurskmeetup.domain.PeopleService;
import mobi.mpk.kurskmeetup.domain.dto.Meetup;
import mobi.mpk.kurskmeetup.domain.dto.People;

public class ApiMeetupsService implements MeetupsService, PeopleService {
    private AsyncRepository repository;
    private List<Meetup> meetups;
    private List<People> people;
    private List<OnDataLoadListener<List<Meetup>>> meetupsObservers;
    private List<OnDataLoadListener<List<People>>> peopleObservers;

    public ApiMeetupsService(AsyncRepository repository) {
        this.repository = repository;
        meetupsObservers = new LinkedList<>();
        peopleObservers = new LinkedList<>();
    }

    @Override
    public List<Meetup> getMeetups() {
        repository.getMeetups(new UpdatingMeetupsCallback());
        return meetups;
    }

    @Override
    public List<People> getPeople() {
        repository.getPeople(new UpdatingPeopleCallback());
        return people;
    }

    @Override
    public void registerPeopleObserver(OnDataLoadListener<List<People>> observer) {
        peopleObservers.add(observer);
    }

    @Override
    public void unregisterPeopleObserver(OnDataLoadListener<List<People>> observer) {
        peopleObservers.remove(observer);
    }

    @Override
    public void registerMeetupsObserver(OnDataLoadListener<List<Meetup>> observer) {
        meetupsObservers.add(observer);
    }

    @Override
    public void unregisterMeetupsObserver(OnDataLoadListener<List<Meetup>> observer) {
        meetupsObservers.remove(observer);
    }

    private class UpdatingMeetupsCallback implements OnDataLoadListener<List<Meetup>> {

        @Override
        public void onSuccess(List<Meetup> data) {
            meetups = data;
            notifySuccessMeetupsObservers(data);
        }

        @Override
        public void onFailure(Throwable throwable) {
            notifyFailureMeetupsObservers(throwable);
        }
    }

    private class UpdatingPeopleCallback implements OnDataLoadListener<List<People>> {

        @Override
        public void onSuccess(List<People> data) {
            people = data;
            notifySuccessPeopleObservers(data);
        }

        @Override
        public void onFailure(Throwable throwable) {
            notifyFailurePeopleObservers(throwable);
        }
    }

    private void notifySuccessMeetupsObservers(List<Meetup> data) {
        for (OnDataLoadListener<List<Meetup>> observer : meetupsObservers) {
            observer.onSuccess(data);
        }
    }

    private void notifyFailureMeetupsObservers(Throwable throwable) {
        for (OnDataLoadListener<List<Meetup>> observer : meetupsObservers) {
            observer.onFailure(throwable);
        }
    }

    private void notifySuccessPeopleObservers(List<People> data) {
        for (OnDataLoadListener<List<People>> observer : peopleObservers) {
            observer.onSuccess(data);
        }
    }

    private void notifyFailurePeopleObservers(Throwable throwable) {
        for (OnDataLoadListener<List<People>> observer : peopleObservers) {
            observer.onFailure(throwable);
        }
    }

}
