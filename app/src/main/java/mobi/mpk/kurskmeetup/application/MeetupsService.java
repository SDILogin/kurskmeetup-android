package mobi.mpk.kurskmeetup.application;

import android.support.v4.app.Fragment;

import java.util.List;

import mobi.mpk.kurskmeetup.data.apiary.ApiaryMeetupsRepository;
import mobi.mpk.kurskmeetup.domain.MeetupsRepository;
import mobi.mpk.kurskmeetup.domain.dto.Meetup;
import mobi.mpk.kurskmeetup.presentation.fragments.EmptyMeetupsFragment;
import mobi.mpk.kurskmeetup.presentation.fragments.MeetupsFragment;

public class MeetupsService {
    private static MeetupsService instance;
    private MeetupsRepository repository;

    private MeetupsService() {
        repository = new ApiaryMeetupsRepository();
    }

    public Fragment getFragment() {
        List<Meetup> meetups = repository.getMeetups();
        if (meetups.size() < 1 && false) {
            return EmptyMeetupsFragment.newInstance();
        }
        return MeetupsFragment.newInstance();
    }

    public static MeetupsService getInstance() {
        if (instance == null) {
            instance = new MeetupsService();
        }
        return instance;
    }

}
