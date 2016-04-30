package mobi.mpk.kurskmeetup.presentation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mobi.mpk.kurskmeetup.R;
import mobi.mpk.kurskmeetup.application.presenter.dto.MeetupDto;
import mobi.mpk.kurskmeetup.domain.dto.Meetup;

public class MeetupListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Meetup> meetups = new ArrayList<>();

    public MeetupListAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            convertView.setTag(new ViewHolder(convertView));
        }

        Meetup meetup = getItem(position);
        if (meetup != null) {
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            MeetupDto meetupDto = MeetupDto.from(meetup);
            viewHolder.title.setText(meetupDto.getPlace());
            viewHolder.subText.setText(meetupDto.getTopics());
            viewHolder.date.setText(meetupDto.getDatetime());
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

    static class ViewHolder {
        @BindView(R.id.meetup_title)
        TextView title;

        @BindView(R.id.meetup_subtext)
        TextView subText;

        @BindView(R.id.meetup_date)
        TextView date;

        public ViewHolder(View v) {
            ButterKnife.bind(this, v);
        }
    }
}
