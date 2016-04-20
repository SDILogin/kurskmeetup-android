package mobi.mpk.kurskmeetup.application.presenter;

import mobi.mpk.kurskmeetup.application.presenter.dto.MeetupDto;
import mobi.mpk.kurskmeetup.domain.dto.Meetup;

public interface MeetupsPresenter {

    MeetupDto getDto(Meetup meetup);

}
