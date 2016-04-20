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
    private String title;
    private String msg;

    private TextView titleView;
    private TextView msgView;

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
        titleView = (TextView) view.findViewById(R.id.meetups_errortitle);
        msgView = (TextView) view.findViewById(R.id.meetups_errortxt);
        if (args != null) {
            setMsg(args.getString("title"), args.getString("msg"));
            showMsg();
        }
        return view;
    }

    public void setMsg(String title, String msg) {
        this.title = title;
        this.msg = msg;
        if (titleView != null && msgView != null) {
            showMsg();
        }
    }

    private void showMsg() {
        titleView.setText(title);
        msgView.setText(msg);
    }

}
