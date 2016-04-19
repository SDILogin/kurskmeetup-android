package mobi.mpk.kurskmeetup.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import mobi.mpk.kurskmeetup.R;
import mobi.mpk.kurskmeetup.application.ApiMeetupsService;
import mobi.mpk.kurskmeetup.data.OnDataLoadListener;
import mobi.mpk.kurskmeetup.domain.dto.Meetup;
import mobi.mpk.kurskmeetup.presentation.adapters.MeetupListAdapter;

public class MeetupsListFragment extends Fragment implements OnDataLoadListener<List<Meetup>> {
    private ListView meetupsList;
    private MeetupListAdapter listAdapter;
    private SwipeRefreshLayout refreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_meetups_list, container, false);
        meetupsList = (ListView) fragmentView.findViewById(R.id.meetups_list);
        listAdapter = new MeetupListAdapter(getContext());
        meetupsList.setAdapter(listAdapter);
        ApiMeetupsService.getInstance().registerObserver(this);

        refreshLayout = (SwipeRefreshLayout) fragmentView.findViewById(R.id.refresh_meetups);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ((UpdateListener) getParentFragment()).update();
            }
        });
        refreshLayout.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_blue_light
        );
        setLoading(true);
        return fragmentView;
    }

    @Override
    public void onDetach() {
        ApiMeetupsService.getInstance().unregisterObserver(this);
        super.onDetach();
    }

    public void setLoading(boolean loading) {
        if (loading) {
            refreshLayout.post(new Runnable() {
                @Override public void run() {
                    refreshLayout.setRefreshing(true);
                }
            });
        } else {
            refreshLayout.setRefreshing(false);
        }
    }

    public static MeetupsListFragment newInstance() {
        return new MeetupsListFragment();
    }

    @Override
    public void onSuccess(List<Meetup> data) {
        setLoading(false);
        listAdapter.clear();
        listAdapter.addAll(data);
    }

    @Override
    public void onFailure(Throwable throwable) {
        setLoading(false);
    }

    public interface UpdateListener {
        void update();
    }

}
