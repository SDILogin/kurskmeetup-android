package mobi.mpk.kurskmeetup.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import javax.inject.Inject;

import mobi.mpk.kurskmeetup.Injector;
import mobi.mpk.kurskmeetup.R;
import mobi.mpk.kurskmeetup.domain.MeetupsService;
import mobi.mpk.kurskmeetup.presentation.adapters.PeopleListAdapter;

public class PeopleFragment extends Fragment {
    private PeopleListAdapter listAdapter;
    private GridView gridView;
    @Inject
    MeetupsService service;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Injector.INSTANCE.getComponent().inject(this);
        View view = inflater.inflate(R.layout.fragment_people, container, false);
        listAdapter = new PeopleListAdapter(getContext());
        gridView = (GridView) view.findViewById(R.id.peoples_grid);
        gridView.setAdapter(listAdapter);
        listAdapter.addAll(service.getPeople());
        return view;
    }

    public static PeopleFragment newInstance() {
        return new PeopleFragment();
    }

}
