package mobi.mpk.kurskmeetup.presentation.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import mobi.mpk.kurskmeetup.presentation.fragments.MeetupsFragment;
import mobi.mpk.kurskmeetup.presentation.fragments.MeetupsTabFragment;
import mobi.mpk.kurskmeetup.presentation.fragments.PeopleFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {
    private static final int TABS_COUNT = 2;
    private Fragment[] tabs;
    private String[] titles = {"Meetups", "Peoples"};

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
        tabs = new Fragment[TABS_COUNT];
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position < 0 || position >= TABS_COUNT) {
            throw new IndexOutOfBoundsException("No tab with index " + position);
        }
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (tabs[0] == null) {
                    tabs[0] = MeetupsTabFragment.newInstance();
                }
                break;
            case 1:
                if (tabs[1] == null) {
                    tabs[1] = PeopleFragment.newInstance();
                }
                break;
            default:
                throw new IndexOutOfBoundsException("No tab with index " + position);
        }
        return tabs[position];
    }

    @Override
    public int getCount() {
        return TABS_COUNT;
    }

}
