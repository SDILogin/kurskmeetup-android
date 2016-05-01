package mobi.mpk.kurskmeetup.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private Context applicationContext;

    public ApplicationModule(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Provides
    public Context provideApplicationContext() {
        return applicationContext;
    }
}
