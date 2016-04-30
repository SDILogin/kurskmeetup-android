package mobi.mpk.kurskmeetup.presentation.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import mobi.mpk.kurskmeetup.presentation.fragments.MeetupsListFragment;
import mobi.mpk.kurskmeetup.presentation.fragments.PeopleFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {
    private static final int TABS_COUNT = 2;
    private String[] titles = {"Meetups", "Peoples"};

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return MeetupsListFragment.newInstance();

            case 1:
                return PeopleFragment.newInstance();

            default:
                throw new IndexOutOfBoundsException("No tab with index " + position);
        }
    }

    @Override
    public int getCount() {
        return TABS_COUNT;
    }

}
