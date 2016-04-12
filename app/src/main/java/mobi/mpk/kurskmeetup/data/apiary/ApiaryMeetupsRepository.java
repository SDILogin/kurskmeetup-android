package mobi.mpk.kurskmeetup.data.apiary;

import android.util.Log;

import java.util.LinkedList;
import java.util.List;

import mobi.mpk.kurskmeetup.data.ApiBuilder;
import mobi.mpk.kurskmeetup.data.KurskMeetupApi;
import mobi.mpk.kurskmeetup.domain.MeetupsRepository;
import mobi.mpk.kurskmeetup.domain.dto.Meetup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiaryMeetupsRepository implements MeetupsRepository {
    private List<Meetup> meetups;

    @Override
    public List<Meetup> getMeetups() {
        KurskMeetupApi api = ApiBuilder.getApi();
        Call<List<Meetup>> call = api.listMeetups();
        call.enqueue(new ListMeetupsCallback());
        return meetups;
    }

    @Override
    public Meetup getMeetup(int id) {
        return null;
    }


    private class ListMeetupsCallback implements Callback<List<Meetup>> {

        @Override
        public void onResponse(Call<List<Meetup>> call, Response<List<Meetup>> response) {
            if (response.isSuccessful()) {
                meetups = response.body();
            }
        }

        @Override
        public void onFailure(Call<List<Meetup>> call, Throwable t) {
            Log.d("MeetupsFragment", "Error occurred: " + t.getMessage());
        }
    }

}
