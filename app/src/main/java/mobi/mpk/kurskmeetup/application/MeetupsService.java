package mobi.mpk.kurskmeetup.application;

import android.content.Context;

import java.net.UnknownHostException;
import java.util.List;

import mobi.mpk.kurskmeetup.R;
import mobi.mpk.kurskmeetup.data.apiary.ApiaryAsyncRepository;
import mobi.mpk.kurskmeetup.data.apiary.BadResponse;
import mobi.mpk.kurskmeetup.domain.AsyncRepository;
import mobi.mpk.kurskmeetup.domain.OnDataLoadListener;
import mobi.mpk.kurskmeetup.domain.dto.Meetup;
import mobi.mpk.kurskmeetup.presentation.ListFragment;

public class MeetupsService {
    private static MeetupsService instance;
    private AsyncRepository repository;
    private ListFragment<Meetup> view;
    private List<Meetup> meetups;
    private Context context;

    private MeetupsService() {
        repository = new ApiaryAsyncRepository();
    }

    public void loadMeetups() {
        if (meetups == null) {
            updateMeetups();
        } else {
            if (meetups.size() > 0) {
                view.showList(meetups);
            } else {
                view.showEmpty();
            }
        }
    }

    public void updateMeetups() {
        repository.getMeetups(new UpdatingCallback());
    }

    public static MeetupsService getInstance(ListFragment<Meetup> view, Context context) {
        if (instance == null) {
            instance = new MeetupsService();
        }
        instance.setView(view);
        instance.setContext(context);
        return instance;
    }

    private void setContext(Context context) {
        this.context = context;
    }

    private void setView(ListFragment<Meetup> view) {
        this.view = view;
    }

    private class UpdatingCallback implements OnDataLoadListener<List<Meetup>> {

        @Override
        public void onSuccess(List<Meetup> data) {
            meetups = data;
            if (meetups != null && meetups.size() > 0) {
                view.showList(data);
            } else {
                view.showEmpty();
            }
        }

        @Override
        public void onFailure(Throwable throwable) {
            if (throwable instanceof BadResponse) {
                view.showError(
                    context.getString(R.string.error_title),
                    context.getString(R.string.error_bad_response)
                );
            } else if (throwable instanceof UnknownHostException) {
                view.showError(
                    context.getString(R.string.error_title),
                    context.getString(R.string.error_connection)
                );
            } else {
                view.showError(
                    context.getString(R.string.error_title),
                    context.getString(R.string.error_internal)
                );
            }
        }
    }

}
