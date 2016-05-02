package mobi.mpk.kurskmeetup.application.presenter;

import java.lang.ref.WeakReference;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Observable;

import javax.inject.Inject;

import mobi.mpk.kurskmeetup.application.presenter.dto.MeetupDto;
import mobi.mpk.kurskmeetup.application.view.MeetupsView;
import mobi.mpk.kurskmeetup.data.BadResponse;
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
        // update view
        getView().showProgressBar();

        // execute
        loadMeetups();
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

        // update view
        getView().showProgressBar();
        getView().showLoadingMessage();

        // execute command
        loadMeetups();
    }

    @Override
    public void onViewDetached(MeetupsView meetupsView) {
        this.view.clear();
    }

    private void loadMeetups() {
        this.interactor.getMeetups()
                .observeOn(AndroidSchedulers.mainThread())
                .filter(listOfMeetups -> listOfMeetups != null && !listOfMeetups.isEmpty())
                .subscribe(
                        // on next
                        listOfMeetups -> getView().showMeetups(listOfMeetups),

                        // on error
                        this::obtainThrowableError,

                        // on complete
                        () -> getView().hideProgressBar()
                );
    }

    private void obtainThrowableError(Throwable throwable) {
        if (throwable instanceof BadResponse) {
            getView().showBadResponseMessage();
        } else if (throwable instanceof UnknownHostException) {
            getView().showConnectionErrorMessage();
        } else {
            getView().showInternalErrorMessage();
        }
    }

    /**
     * Dummy view to avoid ui modification after actual view was detached
     *
     * This class must be moved to another package
     */
    private static class EmptyMeetupView implements MeetupsView {

        @Override
        public void showMeetups(List<Meetup> meetups) {

        }

        @Override
        public void showProgressBar() {

        }

        @Override
        public void hideProgressBar() {

        }

        @Override
        public void showEmptyMessage() {

        }

        @Override
        public void showBadResponseMessage() {

        }

        @Override
        public void showConnectionErrorMessage() {

        }

        @Override
        public void showInternalErrorMessage() {

        }

        @Override
        public void showLoadingMessage() {

        }

        @Override
        public void onError(String message) {

        }
    }
}
