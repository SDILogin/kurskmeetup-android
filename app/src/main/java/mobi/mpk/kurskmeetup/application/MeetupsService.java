package mobi.mpk.kurskmeetup.application;

import android.support.v4.app.Fragment;

import java.util.List;

import mobi.mpk.kurskmeetup.data.apiary.ApiaryAsyncRepository;
import mobi.mpk.kurskmeetup.domain.AsyncRepository;
import mobi.mpk.kurskmeetup.domain.OnDataLoadListener;
import mobi.mpk.kurskmeetup.domain.dto.Meetup;
import mobi.mpk.kurskmeetup.presentation.fragments.MeetupsListFragment;

public class MeetupsService {
    private static MeetupsService instance;
    private AsyncRepository repository;
    private List<Meetup> meetups;

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

    public void getMeetups(OnDataLoadListener<List<Meetup>> callback) {
        if (meetups == null) {
            updateMeetups(callback);
        } else {
            callback.onSuccess(meetups);
        }
    }

    public void updateMeetups(OnDataLoadListener<List<Meetup>> callback) {
        repository.getMeetups(new BufferingCallback(callback));
    }

    public static MeetupsService getInstance() {
        if (instance == null) {
            instance = new MeetupsService();
        }
        return instance;
    }

    private class BufferingCallback implements OnDataLoadListener<List<Meetup>> {
        private OnDataLoadListener<List<Meetup>> callback;

        public BufferingCallback(OnDataLoadListener<List<Meetup>> callback) {
            this.callback = callback;
        }

        @Override
        public void onSuccess(List<Meetup> data) {
            meetups = data;
            callback.onSuccess(data);
        }

        @Override
        public void onFailure(Throwable throwable) {
            callback.onFailure(throwable);
        }
    }

}
