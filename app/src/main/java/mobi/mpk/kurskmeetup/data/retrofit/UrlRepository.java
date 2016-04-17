package mobi.mpk.kurskmeetup.data.retrofit;

import java.util.List;

import mobi.mpk.kurskmeetup.data.AsyncRepository;
import mobi.mpk.kurskmeetup.data.BadResponse;
import mobi.mpk.kurskmeetup.data.OnDataLoadListener;
import mobi.mpk.kurskmeetup.domain.dto.Meetup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UrlRepository implements AsyncRepository {

    private KurskMeetupApi api;

    public UrlRepository(String url) {
        api = new ApiBuilder(url).build();
    }

    @Override
    public void getMeetups(final OnDataLoadListener<List<Meetup>> callback) {
        Call<List<Meetup>> call = api.listMeetups();
        call.enqueue(new Callback<List<Meetup>>() {
            @Override
            public void onResponse(Call<List<Meetup>> call, Response<List<Meetup>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(new BadResponse("Response HTTP code: " +
                                       response.code() +
                                       response.message()));
                }
            }

            @Override
            public void onFailure(Call<List<Meetup>> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    @Override
    public void getMeetup(int id, OnDataLoadListener<Meetup> callback) {

    }

}
