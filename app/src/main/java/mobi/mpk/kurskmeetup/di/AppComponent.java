package mobi.mpk.kurskmeetup.di;

import javax.inject.Singleton;

import dagger.Component;
import mobi.mpk.kurskmeetup.presentation.adapters.MeetupListAdapter;
import mobi.mpk.kurskmeetup.presentation.fragments.MeetupsTabFragment;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(MeetupsTabFragment fragment);
    void inject(MeetupListAdapter adapter);

}
