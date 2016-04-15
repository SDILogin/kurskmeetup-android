package mobi.mpk.kurskmeetup.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import mobi.mpk.kurskmeetup.R;
import mobi.mpk.kurskmeetup.application.MeetupsService;
import mobi.mpk.kurskmeetup.domain.OnDataLoadListener;
import mobi.mpk.kurskmeetup.domain.dto.Meetup;

public class MeetupsTabFragment extends Fragment implements OnDataLoadListener<List<Meetup>> {
    private List<Meetup> meetups;
    private View fragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragment = inflater.inflate(R.layout.fragment_meetups_tab, container, false);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.meetupstab_container, LoadingMeetupsFragment.newInstance());
        transaction.commit();
        MeetupsService.getInstance().getMeetups(this);
        return fragment;
    }

    @Override
    public void onSuccess(List<Meetup> data) {
        meetups = data;
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.meetupstab_container, MeetupsListFragment.newInstance());
        transaction.commit();
    }

    @Override
    public void onFailure(Throwable throwable) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.meetupstab_container, EmptyMeetupsFragment.newInstance());
        transaction.commit();
    }

    public List<Meetup> getMeetups() {
        return meetups;
    }

    public static MeetupsTabFragment newInstance() {
        return new MeetupsTabFragment();
    }

}
