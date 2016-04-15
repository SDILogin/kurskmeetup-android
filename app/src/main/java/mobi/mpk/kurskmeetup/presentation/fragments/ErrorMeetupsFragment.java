package mobi.mpk.kurskmeetup.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mobi.mpk.kurskmeetup.R;

public class ErrorMeetupsFragment extends Fragment {

    public static ErrorMeetupsFragment newInstance() {
        return new ErrorMeetupsFragment();
    }

    public static ErrorMeetupsFragment newInstance(String msg) {
        Bundle bundle = new Bundle();
        bundle.putString("error", msg);
        ErrorMeetupsFragment fragment = new ErrorMeetupsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_error_meetups, container, false);
        Bundle args = getArguments();
        if (args != null) {
            TextView textView = (TextView) view.findViewById(R.id.meetups_errortxt);
            textView.setText(args.getString("error"));
        }
        return view;
    }
}
