package mobi.mpk.kurskmeetup;

import java.util.List;

import mobi.mpk.kurskmeetup.models.Meetup;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Александр on 09.04.2016.
 */
public interface KurskMeetupApi {

    @GET("/meetups")
    Call<List<Meetup>> listMeetups();

}
