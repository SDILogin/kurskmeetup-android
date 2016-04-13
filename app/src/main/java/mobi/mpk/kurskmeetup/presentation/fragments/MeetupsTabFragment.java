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
import mobi.mpk.kurskmeetup.data.apiary.ApiaryAsyncRepository;
import mobi.mpk.kurskmeetup.domain.AsyncRepository;
import mobi.mpk.kurskmeetup.domain.OnDataLoadListener;
import mobi.mpk.kurskmeetup.domain.dto.Meetup;

public class MeetupsTabFragment extends Fragment implements OnDataLoadListener<List<Meetup>> {
    private static final String SUCCESS_FRAGMENT = "Meetups";
    private static final String FAILURE_FRAGMENT = "Meetups";
    private List<Meetup> meetups;

    private Fragment current;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AsyncRepository repository = new ApiaryAsyncRepository();
        repository.getMeetups(this);
        return inflater.inflate(R.layout.fragment_meetupstab, container, false);
    }

    @Override
    public void onSuccess(List<Meetup> data) {
        meetups = data;
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.meetupstab_container, MeetupsFragment.newInstance());
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
