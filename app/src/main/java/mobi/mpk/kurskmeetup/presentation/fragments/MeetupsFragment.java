package mobi.mpk.kurskmeetup.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import mobi.mpk.kurskmeetup.R;
import mobi.mpk.kurskmeetup.domain.MeetupsRepository;
import mobi.mpk.kurskmeetup.presentation.adapters.MeetupListAdapter;

public class MeetupsFragment extends Fragment {
    private ListView meetupsList;
    private MeetupListAdapter listAdapter;
    private MeetupsRepository repository;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_meetup, container, false);
        meetupsList = (ListView) fragmentView.findViewById(R.id.meetups_list);
        listAdapter = new MeetupListAdapter(getContext());
        meetupsList.setAdapter(listAdapter);

        listAdapter.addAll(repository.getMeetups());
        return fragmentView;
    }

    public static MeetupsFragment newInstance() {
        return new MeetupsFragment();
    }

}
