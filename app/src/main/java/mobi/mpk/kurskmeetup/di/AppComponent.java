package mobi.mpk.kurskmeetup.di;

import javax.inject.Singleton;

import dagger.Component;
import mobi.mpk.kurskmeetup.presentation.adapters.MeetupListAdapter;
import mobi.mpk.kurskmeetup.presentation.fragments.MeetupsListFragment;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(MeetupsListFragment fragment);
    void inject(MeetupListAdapter adapter);

}
