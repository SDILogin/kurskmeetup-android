package mobi.mpk.kurskmeetup.presentation.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.crashlytics.android.Crashlytics;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;

import mobi.mpk.kurskmeetup.R;
import mobi.mpk.kurskmeetup.presentation.adapters.TabsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.container)
    ViewPager viewPager;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        viewPager.setAdapter(new TabsPagerAdapter(getSupportFragmentManager()));

        tabLayout.setupWithViewPager(viewPager);
        setIconToTabAtPosition(0, R.drawable.ic_event_white_36dp);
        setIconToTabAtPosition(1, R.drawable.ic_group_white_36dp);
    }

    private void setIconToTabAtPosition(int position, int iconResId) {
        TabLayout.Tab tab = tabLayout.getTabAt(position);
        if (tab != null) {
            tab.setIcon(iconResId);
            tab.setText("");
        }
    }
}
