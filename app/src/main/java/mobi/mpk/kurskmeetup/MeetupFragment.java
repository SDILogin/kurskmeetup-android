package mobi.mpk.kurskmeetup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import mobi.mpk.kurskmeetup.models.Meetup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Александр on 08.04.2016.
 */
public class MeetupFragment extends Fragment {
    private static String baseUrl = "http://private-anon-5d9ca48f1-kurskmeetupapi.apiary-mock.com/";

    private View fragmentView;
    private KurskMeetupApi api;
    private ListView meetupsList;
    private MeetupListAdapter listAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_meetup, container, false);
        meetupsList = (ListView) fragmentView.findViewById(R.id.meetups_list);
        listAdapter = new MeetupListAdapter(getContext());
        meetupsList.setAdapter(listAdapter);

        // TODO make passing api from MainActivity
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        api = retrofit.create(KurskMeetupApi.class);

        Call<List<Meetup>> call = api.listMeetups();
        call.enqueue(new ListMeetupsCallback());
        return fragmentView;
    }

    public static MeetupFragment newInstance() {
        return new MeetupFragment();
    }

    private class ListMeetupsCallback implements Callback<List<Meetup>> {

        @Override
        public void onResponse(Call<List<Meetup>> call, Response<List<Meetup>> response) {
            if (response.isSuccessful()) {
                listAdapter.addAll(response.body());
            }
        }

        @Override
        public void onFailure(Call<List<Meetup>> call, Throwable t) {

        }
    }

}
