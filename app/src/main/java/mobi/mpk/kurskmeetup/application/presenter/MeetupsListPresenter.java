package mobi.mpk.kurskmeetup.application.presenter;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

import mobi.mpk.kurskmeetup.application.presenter.dto.MeetupDto;
import mobi.mpk.kurskmeetup.application.view.MeetupsView;
import mobi.mpk.kurskmeetup.domain.MeetupsService;
import mobi.mpk.kurskmeetup.domain.dto.Meetup;
import rx.android.schedulers.AndroidSchedulers;

public class MeetupsListPresenter implements MeetupsPresenter {

    private final MeetupsView emptyMeetupsView = new EmptyMeetupView();

    private WeakReference<MeetupsView> view;
    private MeetupsService interactor;

    @Inject
    public MeetupsListPresenter(MeetupsService interactor){
        this.interactor = interactor;
    }

    @Override
    public void onMeetupClick(MeetupDto meetup) {
        getView().onError("Not implemented");
    }

    @Override
    public void onListPulled() {
        getView().showProgressBar();
        this.interactor.getMeetups()
                .observeOn(AndroidSchedulers.mainThread())
                .filter(listOfMeetups -> listOfMeetups != null && !listOfMeetups.isEmpty())
                .subscribe(
                        listOfMeetups -> {
                            getView().hideProgressBar();
                            getView().showMeetups(listOfMeetups);
                        },

                        error -> {
                            getView().hideProgressBar();
                            getView().onError(error);
                        }
                );
    }

    @Override
    public MeetupsView getView() {
        if (view != null && view.get() != null) {
            return view.get();
        } else {
            return emptyMeetupsView;
        }
    }

    @Override
    public void onViewAttached(MeetupsView meetupsView) {
        this.view = new WeakReference<>(meetupsView);

        getView().showProgressBar();
        getView().showMessage("Loading...");
        this.interactor.getMeetups()
                .observeOn(AndroidSchedulers.mainThread())
                .filter(listOfMeetups -> listOfMeetups != null && !listOfMeetups.isEmpty())
                .subscribe(
                        listOfMeetups -> {
                            getView().hideProgressBar();
                            getView().showMeetups(listOfMeetups);
                        },

                        error -> {
                            getView().hideProgressBar();
                            getView().onError(error);
                        }
                );
    }

    @Override
    public void onViewDetached(MeetupsView meetupsView) {
        this.view.clear();
    }

    /**
     * Dummy view to avoid ui modification after actual view was detached
     */
    private class EmptyMeetupView implements MeetupsView {

        @Override
        public void showMeetups(List<Meetup> meetups) {

        }

        @Override
        public void showMessage(String message) {

        }

        @Override
        public void showProgressBar() {

        }

        @Override
        public void hideProgressBar() {

        }

        @Override
        public void onError(Throwable throwable) {

        }

        @Override
        public void onError(String message) {

        }
    }
}
