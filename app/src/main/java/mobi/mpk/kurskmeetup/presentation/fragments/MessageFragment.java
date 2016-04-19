package mobi.mpk.kurskmeetup.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mobi.mpk.kurskmeetup.R;

public class MessageFragment extends Fragment {

    public static MessageFragment newInstance() {
        return new MessageFragment();
    }

    public static MessageFragment newInstance(String title) {
        return newInstance(title, "");
    }

    public static MessageFragment newInstance(String title, String msg) {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("msg", msg);
        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        Bundle args = getArguments();
        if (args != null) {
            TextView titleView = (TextView) view.findViewById(R.id.meetups_errortitle);
            titleView.setText(args.getString("title"));
            TextView msgView = (TextView) view.findViewById(R.id.meetups_errortxt);
            msgView.setText(args.getString("msg"));
        }
        return view;
    }
}
