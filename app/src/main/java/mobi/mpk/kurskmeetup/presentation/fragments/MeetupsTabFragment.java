package mobi.mpk.kurskmeetup.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.net.UnknownHostException;
import java.util.List;

import mobi.mpk.kurskmeetup.R;
import mobi.mpk.kurskmeetup.application.ApiMeetupsService;
import mobi.mpk.kurskmeetup.data.BadResponse;
import mobi.mpk.kurskmeetup.data.OnDataLoadListener;
import mobi.mpk.kurskmeetup.domain.MeetupsService;
import mobi.mpk.kurskmeetup.domain.dto.Meetup;

public class MeetupsTabFragment extends Fragment implements OnDataLoadListener<List<Meetup>>, MeetupsListFragment.UpdateListener {
    private MeetupsService service;
    private MeetupsListFragment listFragment;
    private MessageFragment messageFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_meetups_tab, container, false);
        listFragment = MeetupsListFragment.newInstance();
        messageFragment = MessageFragment.newInstance();
        service = ApiMeetupsService.getInstance();
        service.registerObserver(listFragment);
        showLoading();
        service.getMeetups();
        return fragment;
    }

    @Override
    public void onDestroyView() {
        service.unregisterObserver(listFragment);
        super.onDestroyView();
    }

    public void showLoading() {
        showList();
    }

    public void showList() {
        showFragment(listFragment);
    }

    public void showEmpty() {
        messageFragment.setMsg(getString(R.string.no_meetups), "");
        showFragment(messageFragment);
    }

    public void showError(String title, String msg) {
        messageFragment.setMsg(title, msg);
        showFragment(messageFragment);
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.meetupstab_container, fragment);
        transaction.commit();
    }

    public static MeetupsTabFragment newInstance() {
        return new MeetupsTabFragment();
    }

    @Override
    public void onSuccess(List<Meetup> data) {
        showList();
    }

    @Override
    public void onFailure(Throwable throwable) {
        if (throwable instanceof BadResponse) {
            showError(
                    getString(R.string.error_title),
                    getString(R.string.error_bad_response)
            );
        } else if (throwable instanceof UnknownHostException) {
            showError(
                    getString(R.string.error_title),
                    getString(R.string.error_connection)
            );
        } else {
            showError(
                    getString(R.string.error_title),
                    getString(R.string.error_internal)
            );
        }
    }

    @Override
    public void update() {
        service.getMeetups();
    }
}
