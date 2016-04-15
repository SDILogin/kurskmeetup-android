package mobi.mpk.kurskmeetup.application;

import android.support.annotation.Nullable;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import mobi.mpk.kurskmeetup.data.apiary.ApiaryAsyncRepository;
import mobi.mpk.kurskmeetup.domain.AsyncRepository;
import mobi.mpk.kurskmeetup.domain.OnDataLoadListener;
import mobi.mpk.kurskmeetup.domain.OnUpdateListener;
import mobi.mpk.kurskmeetup.domain.dto.Meetup;

public class MeetupsService {
    private static MeetupsService instance;
    private AsyncRepository repository;
    private List<Meetup> meetups;
    private boolean updating;

    private MeetupsService() {
        repository = new ApiaryAsyncRepository();
        updating = false;
    }

    @Nullable
    public List<Meetup> getMeetups() {
        return meetups;
    }

    public void updateMeetups(OnUpdateListener callback) {
        repository.getMeetups(new UpdatingCallback(callback));
    }

    public static MeetupsService getInstance() {
        if (instance == null) {
            instance = new MeetupsService();
        }
        return instance;
    }

    private class UpdatingCallback implements OnDataLoadListener<List<Meetup>> {
        private OnUpdateListener callback;

        public UpdatingCallback(OnUpdateListener callback) {
            this.callback = callback;
        }

        @Override
        public void onSuccess(List<Meetup> data) {
            updating = false;
            meetups = data;
            callback.onSuccess();
        }

        @Override
        public void onFailure(Throwable throwable) {
            updating = false;
            callback.onFailure(throwable);
        }
    }

}
