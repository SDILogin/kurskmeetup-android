package mobi.mpk.kurskmeetup.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import mobi.mpk.kurskmeetup.utils.ApiBuilder;
import mobi.mpk.kurskmeetup.utils.KurskMeetupApi;
import mobi.mpk.kurskmeetup.adapters.MeetupListAdapter;
import mobi.mpk.kurskmeetup.R;
import mobi.mpk.kurskmeetup.models.Meetup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class MeetupFragment extends Fragment {
    private ListView meetupsList;
    private MeetupListAdapter listAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_meetup, container, false);
        meetupsList = (ListView) fragmentView.findViewById(R.id.meetups_list);
        listAdapter = new MeetupListAdapter(getContext());
        meetupsList.setAdapter(listAdapter);

        KurskMeetupApi api = ApiBuilder.getApi();
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
