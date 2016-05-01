package mobi.mpk.kurskmeetup.di;

import javax.inject.Singleton;

import dagger.Component;
import mobi.mpk.kurskmeetup.di.network.NetworkModule;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface AppComponent {
    MeetupsComponent plus();
}
