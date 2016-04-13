package mobi.mpk.kurskmeetup.application;

import android.support.v4.app.Fragment;

import mobi.mpk.kurskmeetup.data.apiary.ApiaryAsyncRepository;
import mobi.mpk.kurskmeetup.domain.AsyncRepository;
import mobi.mpk.kurskmeetup.presentation.fragments.MeetupsListFragment;

public class MeetupsService {
    private static MeetupsService instance;
    private AsyncRepository repository;

    private MeetupsService() {
        repository = new ApiaryAsyncRepository();
    }

    public Fragment getFragment() {
        /*List<Meetup> meetups = repository.getMeetups(null);
        if (meetups.size() < 1 && false) {
            return EmptyMeetupsFragment.newInstance();
        }*/
        return MeetupsListFragment.newInstance();
    }

    public static MeetupsService getInstance() {
        if (instance == null) {
            instance = new MeetupsService();
        }
        return instance;
    }

}
