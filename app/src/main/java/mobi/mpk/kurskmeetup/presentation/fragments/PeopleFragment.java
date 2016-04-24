package mobi.mpk.kurskmeetup.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.UnknownHostException;
import java.util.List;

import javax.inject.Inject;

import mobi.mpk.kurskmeetup.Injector;
import mobi.mpk.kurskmeetup.R;
import mobi.mpk.kurskmeetup.data.BadResponse;
import mobi.mpk.kurskmeetup.data.OnDataLoadListener;
import mobi.mpk.kurskmeetup.domain.PeopleService;
import mobi.mpk.kurskmeetup.domain.dto.People;
import mobi.mpk.kurskmeetup.presentation.adapters.PeopleListAdapter;
import mobi.mpk.kurskmeetup.presentation.views.EmptyViewSwipeRefreshLayout;

public class PeopleFragment extends Fragment implements OnDataLoadListener<List<People>> {
    private PeopleListAdapter listAdapter;
    @Inject
    PeopleService service;

    private GridView gridView;
    private EmptyViewSwipeRefreshLayout refreshLayout;
    private TextView msgText;
    private ScrollView scrollView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Injector.INSTANCE.getComponent().inject(this);
        View view = inflater.inflate(R.layout.fragment_people, container, false);
        gridView = (GridView) view.findViewById(R.id.peoples_grid);
        refreshLayout = (EmptyViewSwipeRefreshLayout) view.findViewById(R.id.refresh_people);
        msgText = (TextView) view.findViewById(R.id.people_msgtxt);
        scrollView = (ScrollView) view.findViewById(R.id.people_scrollwrap);

        listAdapter = new PeopleListAdapter(getContext());
        gridView.setAdapter(listAdapter);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updatePeople();
            }
        });
        refreshLayout.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_blue_light
        );

        gridView.setEmptyView(scrollView);
        service.registerPeopleObserver(this);
        setLoading(true);
        service.getPeople();
        return view;
    }

    @Override
    public void onDetach() {
        service.unregisterPeopleObserver(this);
        super.onDetach();
    }

    public static PeopleFragment newInstance() {
        return new PeopleFragment();
    }

    public void setLoading(boolean loading) {
        if (loading) {
            refreshLayout.post(new Runnable() {
                @Override public void run() {
                    refreshLayout.setRefreshing(true);
                }
            });
        } else {
            refreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    refreshLayout.setRefreshing(false);
                }
            });
        }
    }

    public void updatePeople() {
        service.getPeople();
    }

    public void setEmptyMessage(String msg) {
        msgText.setText(msg);
    }

    public void showError(Throwable throwable) {
        if (listAdapter.size() > 0) {
            Toast.makeText(getContext(), getErrorText(throwable), Toast.LENGTH_SHORT).show();
        } else {
            setEmptyMessage(getErrorText(throwable));
        }
    }

    @Override
    public void onSuccess(List<People> data) {
        setLoading(false);
        if (data.size() > 0) {
            listAdapter.clear();
            listAdapter.addAll(data);
        } else {
            setEmptyMessage(getString(R.string.no_people));
        }
    }

    @Override
    public void onFailure(Throwable throwable) {
        setLoading(false);
        showError(throwable);
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
