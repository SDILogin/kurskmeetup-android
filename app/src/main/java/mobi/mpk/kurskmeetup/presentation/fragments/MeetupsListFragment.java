package mobi.mpk.kurskmeetup.presentation.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import mobi.mpk.kurskmeetup.Injector;
import mobi.mpk.kurskmeetup.R;
import mobi.mpk.kurskmeetup.application.presenter.MeetupsPresenter;
import mobi.mpk.kurskmeetup.application.presenter.dto.MeetupDto;
import mobi.mpk.kurskmeetup.application.view.MeetupsView;
import mobi.mpk.kurskmeetup.domain.dto.Meetup;
import mobi.mpk.kurskmeetup.presentation.adapters.MeetupListAdapter;
import mobi.mpk.kurskmeetup.presentation.views.EmptyViewSwipeRefreshLayout;

public class MeetupsListFragment extends Fragment implements MeetupsView, AdapterView.OnItemSelectedListener {

    @Inject
    MeetupsPresenter presenter;

    @Inject
    Context context;

    @BindView(R.id.meetups_list)
    ListView meetupsList;

    @BindView(R.id.refresh_meetups)
    EmptyViewSwipeRefreshLayout refreshLayout;

    @BindView(R.id.meetups_msgtxt)
    TextView msgText;

    @BindView(R.id.meetups_scrollwrap)
    ScrollView scrollView;

    private MeetupListAdapter listAdapter;

    public static MeetupsListFragment newInstance() {
        return new MeetupsListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Injector.INSTANCE.getMeetupsComponent().inject(this);

        View fragmentView = inflater.inflate(R.layout.fragment_meetups_list, container, false);

        ButterKnife.bind(this, fragmentView);

        listAdapter = new MeetupListAdapter(getContext());
        meetupsList.setAdapter(listAdapter);

        refreshLayout.setOnRefreshListener(() -> presenter.onListPulled());
        refreshLayout.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_blue_light
        );

        meetupsList.setEmptyView(scrollView);
        meetupsList.setOnItemSelectedListener(this);

        return fragmentView;
    }

    @Override
    public void onPause() {
        presenter.onViewDetached(this);

        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.onViewAttached(this);
    }

    @Override
    public void showMeetups(List<Meetup> meetups) {
        listAdapter.clear();
        listAdapter.addAll(meetups);
    }

    @Override
    public void showProgressBar() {
        refreshLayout.post(() -> refreshLayout.setRefreshing(true));
    }

    @Override
    public void hideProgressBar() {
        refreshLayout.post(() -> refreshLayout.setRefreshing(false));
    }

    @Override
    public void showEmptyMessage() {
        msgText.setText(R.string.no_meetups);
    }

    @Override
    public void showBadResponseMessage() {
        msgText.setText(R.string.error_bad_response);
    }

    @Override
    public void showConnectionErrorMessage() {
        msgText.setText(R.string.error_connection);
    }

    @Override
    public void showInternalErrorMessage() {
        msgText.setText(R.string.error_internal);
    }

    @Override
    public void showLoadingMessage() {
        msgText.setText(R.string.loading_meetups);
    }

    @Override
    public void onError(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Object object = parent.getSelectedItem();
        if (object instanceof MeetupDto) {
            presenter.onMeetupClick((MeetupDto) object);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
