package mobi.mpk.kurskmeetup.utils;

import java.util.List;

import mobi.mpk.kurskmeetup.models.Meetup;
import retrofit2.Call;
import retrofit2.http.GET;

public interface KurskMeetupApi {

    @GET("meetups")
    Call<List<Meetup>> listMeetups();

}
