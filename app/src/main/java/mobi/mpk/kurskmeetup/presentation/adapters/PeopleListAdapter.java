package mobi.mpk.kurskmeetup.presentation.adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import mobi.mpk.kurskmeetup.R;
import mobi.mpk.kurskmeetup.domain.dto.People;

public class PeopleListAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<People> people;

    public PeopleListAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        people = new LinkedList<>();
    }

    @Override
    public int getCount() {
        return people.size();
    }

    @Override
    public People getItem(int position) {
        return people.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_people, null);
        }
        People people1 = getItem(position);
        if (people1 != null) {
            ImageView imageView = (ImageView) convertView.findViewById(R.id.people_img);
            TextView textView = (TextView) convertView.findViewById(R.id.people_name);
            // TODO set custom img
            imageView.setImageResource(R.drawable.person_placeholder);
            textView.setText(people1.getName());
        }
        return convertView;
    }

    public void add(People people) {
        this.people.add(people);
        notifyDataSetChanged();
    }

    public void addAll(Collection<? extends People> collection) {
        people.addAll(collection);
        notifyDataSetChanged();
    }

    public int size() {
        return people.size();
    }

    public void clear() {
        people.clear();
    }
}
