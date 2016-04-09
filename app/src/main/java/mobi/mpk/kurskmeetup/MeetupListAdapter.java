package mobi.mpk.kurskmeetup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import mobi.mpk.kurskmeetup.models.Meetup;

/**
 * Created by Александр on 09.04.2016.
 */
public class MeetupListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private List<Meetup> meetupList;

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
            TextView textView = (TextView) convertView.findViewById(R.id.meetup_textview);
            textView.setText(meetup.getPlace());
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

}
