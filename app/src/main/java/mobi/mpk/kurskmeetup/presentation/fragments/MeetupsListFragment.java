package mobi.mpk.kurskmeetup.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.UnknownHostException;
import java.util.List;

import mobi.mpk.kurskmeetup.R;
import mobi.mpk.kurskmeetup.application.ApiMeetupsService;
import mobi.mpk.kurskmeetup.data.BadResponse;
import mobi.mpk.kurskmeetup.data.OnDataLoadListener;
import mobi.mpk.kurskmeetup.domain.MeetupsService;
import mobi.mpk.kurskmeetup.domain.dto.Meetup;
import mobi.mpk.kurskmeetup.presentation.adapters.MeetupListAdapter;

public class MeetupsListFragment extends Fragment implements OnDataLoadListener<List<Meetup>> {
    private MeetupListAdapter listAdapter;
    private MeetupsService service;

    private ListView meetupsList;
    private SwipeRefreshLayout refreshLayout;
    private TextView msgText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_meetups_list, container, false);
        meetupsList = (ListView) fragmentView.findViewById(R.id.meetups_list);
        refreshLayout = (SwipeRefreshLayout) fragmentView.findViewById(R.id.refresh_meetups);
        msgText = (TextView) fragmentView.findViewById(R.id.meetups_msgtxt);

        listAdapter = new MeetupListAdapter(getContext());
        meetupsList.setAdapter(listAdapter);
        ApiMeetupsService.getInstance().registerObserver(this);

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

        service = ApiMeetupsService.getInstance();
        service.registerObserver(this);
        setLoading(true);
        return fragmentView;
    }

    @Override
    public void onDetach() {
        service.unregisterObserver(this);
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

    public void showMsg(String msg) {
        msgText.setText(msg);
        Log.d(this.getClass().getSimpleName(), "" + refreshLayout.isRefreshing());
        meetupsList.setVisibility(View.INVISIBLE);
        msgText.setVisibility(View.VISIBLE);
    }

    public void showError(Throwable throwable) {
        if (listAdapter.size() > 0) {
            Toast.makeText(getContext(), getErrorText(throwable), Toast.LENGTH_SHORT).show();
        } else {
            showMsg(getErrorText(throwable));
        }
    }

    public void showList() {
        msgText.setVisibility(View.INVISIBLE);
        meetupsList.setVisibility(View.VISIBLE);
    }

    public static MeetupsListFragment newInstance() {
        return new MeetupsListFragment();
    }

    @Override
    public void onSuccess(List<Meetup> data) {
        setLoading(false);
        if (data.size() > 0) {
            showList();
            listAdapter.clear();
            listAdapter.addAll(data);
        } else {
            showMsg(getString(R.string.no_meetups));
        }
    }

    @Override
    public void onFailure(Throwable throwable) {
        setLoading(false);
        showError(throwable);
    }

    public interface UpdateListener {
        void update();
    }

    public String getErrorText(Throwable throwable) {
        if (throwable instanceof BadResponse) {
            return (getString(R.string.error_bad_response));
        } else if (throwable instanceof UnknownHostException) {
            return (getString(R.string.error_connection));
        } else {
            return (getString(R.string.error_internal));
        }
    }

}
