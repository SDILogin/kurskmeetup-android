package mobi.mpk.kurskmeetup.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import mobi.mpk.kurskmeetup.domain.OnDataLoadListener;
import mobi.mpk.kurskmeetup.domain.dto.Meetup;

public class MeetupsTabFragment extends Fragment implements OnDataLoadListener<List<Meetup>> {
    private static final String SUCCESS_FRAGMENT = "Meetups";
    private static final String FAILURE_FRAGMENT = "Meetups";

    private Fragment current;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        current = EmptyMeetupsFragment.newInstance();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(current, FAILURE_FRAGMENT);
        transaction.commit();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onSuccess(List<Meetup> data) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.remove(current);
        current = MeetupsFragment.newInstance();
        transaction.add(current, SUCCESS_FRAGMENT);
        transaction.commit();
    }

    @Override
    public void onFailure(Throwable throwable) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.remove(current);
        current = EmptyMeetupsFragment.newInstance();
        transaction.add(current, FAILURE_FRAGMENT);
        transaction.commit();
    }

    public static MeetupsTabFragment newInstance() {
        return new MeetupsTabFragment();
    }

}
