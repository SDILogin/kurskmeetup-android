package mobi.mpk.kurskmeetup.di;

import dagger.Subcomponent;
import mobi.mpk.kurskmeetup.presentation.fragments.MeetupsListFragment;

@Subcomponent(modules = {MeetupsModule.class})
public interface MeetupsComponent {
    void inject(MeetupsListFragment fragment);
}
