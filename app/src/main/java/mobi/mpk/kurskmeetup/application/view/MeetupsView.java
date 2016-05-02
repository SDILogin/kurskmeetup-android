package mobi.mpk.kurskmeetup.application.view;

import java.util.List;

import mobi.mpk.kurskmeetup.domain.dto.Meetup;

public interface MeetupsView extends BaseView {
    // data
    void showMeetups(List<Meetup> meetups);

    // progress
    void showProgressBar();
    void hideProgressBar();

    // messages
    void showEmptyMessage();
    void showBadResponseMessage();
    void showConnectionErrorMessage();
    void showInternalErrorMessage();
    void showLoadingMessage();
}
