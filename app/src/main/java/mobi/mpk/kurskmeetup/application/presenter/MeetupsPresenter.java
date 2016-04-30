package mobi.mpk.kurskmeetup.application.presenter;

import mobi.mpk.kurskmeetup.application.presenter.dto.MeetupDto;
import mobi.mpk.kurskmeetup.application.view.MeetupsView;
import mobi.mpk.kurskmeetup.domain.dto.Meetup;

public interface MeetupsPresenter extends BasePresenter<MeetupsView> {
    void onMeetupClick(MeetupDto meetup);

    void onListPulled();
}
