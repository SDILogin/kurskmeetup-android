package mobi.mpk.kurskmeetup.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import mobi.mpk.kurskmeetup.R;
import mobi.mpk.kurskmeetup.data.apiary.ApiaryAsyncRepository;
import mobi.mpk.kurskmeetup.domain.AsyncRepository;
import mobi.mpk.kurskmeetup.domain.OnDataLoadListener;
import mobi.mpk.kurskmeetup.domain.dto.Meetup;
import mobi.mpk.kurskmeetup.presentation.adapters.MeetupListAdapter;

public class MeetupsFragment extends Fragment {
    private ListView meetupsList;
    private MeetupListAdapter listAdapter;
    private AsyncRepository repository;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_meetup, container, false);
        meetupsList = (ListView) fragmentView.findViewById(R.id.meetups_list);
        listAdapter = new MeetupListAdapter(getContext());
        meetupsList.setAdapter(listAdapter);

        repository = new ApiaryAsyncRepository();
        repository.getMeetups(new Callback());
        return fragmentView;
    }

    public static MeetupsFragment newInstance() {
        return new MeetupsFragment();
    }

    private class Callback implements OnDataLoadListener<List<Meetup>> {

        @Override
        public void onSuccess(List<Meetup> data) {
            listAdapter.addAll(data);
        }

        @Override
        public void onFailure(Throwable throwable) {
            // TODO switch to error fragment
        }
    }

}
