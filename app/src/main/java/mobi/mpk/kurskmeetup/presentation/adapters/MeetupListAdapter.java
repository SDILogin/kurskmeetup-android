package mobi.mpk.kurskmeetup.presentation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import mobi.mpk.kurskmeetup.Injector;
import mobi.mpk.kurskmeetup.R;
import mobi.mpk.kurskmeetup.application.presenter.MeetupsPresenter;
import mobi.mpk.kurskmeetup.application.presenter.dto.MeetupDto;
import mobi.mpk.kurskmeetup.domain.dto.Meetup;

public class MeetupListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private List<Meetup> meetups;
    @Inject
    MeetupsPresenter presenter;

    public MeetupListAdapter(Context context) {
        Injector.INSTANCE.getComponent().inject(this);
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        meetups = new LinkedList<>();
    }

    @Override
    public int getCount() {
        return meetups.size();
    }

    @Override
    public Meetup getItem(int position) {
        return meetups.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_meetup, null);
        }
        Meetup meetup = getItem(position);
        if (meetup != null) {
            TextView titleView = (TextView) convertView.findViewById(R.id.meetup_title);
            TextView subView = (TextView) convertView.findViewById(R.id.meetup_subtext);
            TextView dateView = (TextView) convertView.findViewById(R.id.meetup_date);
            MeetupDto meetupDto = presenter.getDto(meetup);
            titleView.setText(meetupDto.getPlace());
            subView.setText(meetupDto.getTopics());
            dateView.setText(meetupDto.getDatetime());
        }
        return convertView;
    }

    public void add(Meetup meetup) {
        meetups.add(meetup);
        notifyDataSetChanged();
    }

    public void addAll(Collection<? extends Meetup> collection) {
        meetups.addAll(collection);
        notifyDataSetChanged();
    }

    public int size() {
        return meetups.size();
    }

    public void clear() {
        meetups.clear();
    }

}
