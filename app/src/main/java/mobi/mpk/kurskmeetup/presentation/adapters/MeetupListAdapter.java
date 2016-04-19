package mobi.mpk.kurskmeetup.presentation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import mobi.mpk.kurskmeetup.R;
import mobi.mpk.kurskmeetup.domain.dto.Meetup;
import mobi.mpk.kurskmeetup.domain.dto.Topic;

public class MeetupListAdapter extends BaseAdapter {
    private static final DateFormat dateFormat;
    private LayoutInflater inflater;
    private Context context;
    private List<Meetup> meetupList;

    static {
        dateFormat = new SimpleDateFormat("HH:mm dd MMMM yyyy", Locale.getDefault());
    }

    public MeetupListAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        meetupList = new LinkedList<>();
    }

    @Override
    public int getCount() {
        return meetupList.size();
    }

    @Override
    public Meetup getItem(int position) {
        return meetupList.get(position);
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
            titleView.setText(meetup.getPlace());
            StringBuilder str = new StringBuilder();
            for (Topic topic : meetup.getTopics()) {
                str.append(topic.getTitle() + "\n");
            }
            subView.setText(str.substring(0, str.length() - 1));
            dateView.setText(dateFormat.format(meetup.getDatetime()));
        }
        return convertView;
    }

    public void add(Meetup meetup) {
        meetupList.add(meetup);
        notifyDataSetChanged();
    }

    public void addAll(Collection<? extends Meetup> collection) {
        meetupList.addAll(collection);
        notifyDataSetChanged();
    }

    public void clear() {
        meetupList.clear();
    }

}
