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
import mobi.mpk.kurskmeetup.application.MeetupsService;
import mobi.mpk.kurskmeetup.domain.dto.Meetup;
import mobi.mpk.kurskmeetup.presentation.adapters.MeetupListAdapter;

public class MeetupsListFragment extends Fragment {
    private ListView meetupsList;
    private MeetupListAdapter listAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_meetups_list, container, false);
        meetupsList = (ListView) fragmentView.findViewById(R.id.meetups_list);
        listAdapter = new MeetupListAdapter(getContext());
        meetupsList.setAdapter(listAdapter);

        List<Meetup> loadedData = MeetupsService.getInstance().getMeetups();
        if (loadedData != null) {
            listAdapter.addAll(loadedData);
        }
        return fragmentView;
    }

    public static MeetupsListFragment newInstance() {
        return new MeetupsListFragment();
    }

}
