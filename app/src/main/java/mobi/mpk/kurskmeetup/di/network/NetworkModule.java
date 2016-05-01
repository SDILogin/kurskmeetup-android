package mobi.mpk.kurskmeetup.di.network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mobi.mpk.kurskmeetup.data.retrofit.ApiBuilder;
import mobi.mpk.kurskmeetup.data.retrofit.KurskMeetupApi;

@Module
public class NetworkModule {

    private String baseUrl;

    public NetworkModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    public KurskMeetupApi provideKurskMeetupApi() {
        return new ApiBuilder(baseUrl).build();
    }
}
