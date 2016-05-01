package mobi.mpk.kurskmeetup;

import android.content.Context;

import mobi.mpk.kurskmeetup.data.Urls;
import mobi.mpk.kurskmeetup.di.ApplicationModule;
import mobi.mpk.kurskmeetup.di.DaggerAppComponent;
import mobi.mpk.kurskmeetup.di.MeetupsComponent;
import mobi.mpk.kurskmeetup.di.network.NetworkModule;

public enum Injector {
    INSTANCE;
    private MeetupsComponent meetupsComponent;

    public MeetupsComponent getMeetupsComponent() {
        return meetupsComponent;
    }

    void setup(Context context) {
        meetupsComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(context))
                .networkModule(new NetworkModule(Urls.APIARY))
                .build()
                .plus();
    }
}
