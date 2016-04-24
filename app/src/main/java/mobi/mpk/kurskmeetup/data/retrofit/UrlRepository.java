package mobi.mpk.kurskmeetup.data.retrofit;

import java.util.List;

import mobi.mpk.kurskmeetup.data.AsyncRepository;
import mobi.mpk.kurskmeetup.data.BadResponse;
import mobi.mpk.kurskmeetup.data.OnDataLoadListener;
import mobi.mpk.kurskmeetup.domain.dto.Meetup;
import mobi.mpk.kurskmeetup.domain.dto.People;
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
        getObjects(call, callback);
    }

    @Override
    public void getPeople(final OnDataLoadListener<List<People>> callback) {
        Call<List<People>> call = api.listPeople();
        getObjects(call, callback);
    }

    private <T> void getObjects(Call<T> call, final OnDataLoadListener<T> callback) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(new BadResponse("Response HTTP code: " +
                            response.code() + response.message()));
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

}
