package mobi.mpk.kurskmeetup.data.retrofit;

import java.util.List;

import mobi.mpk.kurskmeetup.domain.dto.Meetup;
import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

public interface KurskMeetupApi {

    @GET("meetups")
    Observable<List<Meetup>> listMeetups();

}
