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
import mobi.mpk.kurskmeetup.domain.dto.Meetup;
import mobi.mpk.kurskmeetup.presentation.ListFragment;

public class MeetupsTabFragment extends Fragment implements ListFragment<Meetup> {

    private List<Meetup> meetups;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_meetups_tab, container, false);
        MeetupsService service = MeetupsService.getInstance(this, getContext());
        showLoading();
        service.loadMeetups();
        return fragment;
    }

    @Override
    public void showLoading() {
        showFragment(LoadingMeetupsFragment.newInstance());
    }

    @Override
    public void showList(List<Meetup> meetups) {
        this.meetups = meetups;
        showFragment(MeetupsListFragment.newInstance());
    }

    @Override
    public void showEmpty() {
        showFragment(EmptyMeetupsFragment.newInstance());
    }

    @Override
    public void showError(String title, String msg) {
        Fragment errorFragment = ErrorMeetupsFragment.newInstance(title, msg);
        showFragment(errorFragment);
    }

    public List<Meetup> getMeetups() {
        return meetups;
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.meetupstab_container, fragment);
        transaction.commit();
    }

    public static MeetupsTabFragment newInstance() {
        return new MeetupsTabFragment();
    }

}
