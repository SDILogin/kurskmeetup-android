package mobi.mpk.kurskmeetup;

import android.app.Application;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Injector.INSTANCE.setup();
    }

}
