package mobi.mpk.kurskmeetup.application.view;

import java.util.List;

import mobi.mpk.kurskmeetup.domain.dto.Meetup;

public interface MeetupsView extends BaseView {
    void showMeetups(List<Meetup> meetups);
    void showMessage(String message);

    void showProgressBar();
    void hideProgressBar();

    void onError(Throwable throwable);
}
