package mobi.mpk.kurskmeetup;

import mobi.mpk.kurskmeetup.di.AppComponent;
import mobi.mpk.kurskmeetup.di.DaggerAppComponent;

public enum Injector {
    INSTANCE;
    private AppComponent component;

    public AppComponent getComponent() {
        return component;
    }

    void setup() {
        component = DaggerAppComponent.create();
    }
}
