package mobi.mpk.kurskmeetup.presentation.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

public class MeetupsTabFragment extends Fragment implements OnDataLoadListener<List<Meetup>> {
    private MeetupsService service;
    private List<Meetup> meetups;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_meetups_tab, container, false);
        service = ApiMeetupsService.getInstance();
        service.registerObserver(this);
        showLoading();
        service.getMeetups();
        return fragment;
    }

    @Override
    public void onDestroyView() {
        service.unregisterObserver(this);
        super.onDestroyView();
    }

    public void showLoading() {
        showFragment(MessageFragment.newInstance(getString(R.string.loading_meetups)));
    }

    public void showList() {
        showFragment(MeetupsListFragment.newInstance());
    }

    public void showEmpty() {
        showFragment(MessageFragment.newInstance(getString(R.string.no_meetups)));
    }

    public void showError(String title, String msg) {
        Fragment errorFragment = MessageFragment.newInstance(title, msg);
        showFragment(errorFragment);
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.meetupstab_container, fragment);
        transaction.commit();
    }

    public List<Meetup> getMeetups() {
        return meetups;
    }

    public static MeetupsTabFragment newInstance() {
        return new MeetupsTabFragment();
    }

    @Override
    public void onSuccess(List<Meetup> data) {
        meetups = data;
        showList();
    }

    @Override
    public void onFailure(Throwable throwable) {
        Context context = getContext();
        if (throwable instanceof BadResponse) {
            showError(
                    context.getString(R.string.error_title),
                    context.getString(R.string.error_bad_response)
            );
        } else if (throwable instanceof UnknownHostException) {
            showError(
                    context.getString(R.string.error_title),
                    context.getString(R.string.error_connection)
            );
        } else {
            showError(
                    context.getString(R.string.error_title),
                    context.getString(R.string.error_internal)
            );
        }
    }
}
