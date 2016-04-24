package mobi.mpk.kurskmeetup.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mobi.mpk.kurskmeetup.application.ApiMeetupsService;
import mobi.mpk.kurskmeetup.application.presenter.MeetupsPresenter;
import mobi.mpk.kurskmeetup.application.presenter.MyMeetupsPresenter;
import mobi.mpk.kurskmeetup.data.AsyncRepository;
import mobi.mpk.kurskmeetup.data.Urls;
import mobi.mpk.kurskmeetup.data.retrofit.UrlRepository;
import mobi.mpk.kurskmeetup.domain.MeetupsService;
import mobi.mpk.kurskmeetup.domain.PeopleService;
import mobi.mpk.kurskmeetup.domain.dto.People;

@Module
public class AppModule {

    @Provides
    @Singleton
    public static AsyncRepository provideAsyncRepository() {
        return new UrlRepository(Urls.APIARY);
    }

    @Provides
    @Singleton
    public static ApiMeetupsService provideApiMeetupsService(AsyncRepository repository) {
        return new ApiMeetupsService(repository);
    }

    @Provides
    @Singleton
    public static MeetupsService provideMeetupsService(ApiMeetupsService service) {
        return service;
    }

    @Provides
    @Singleton
    public static PeopleService providePeopleService(ApiMeetupsService service) {
        return service;
    }

    @Provides
    @Singleton
    public static MeetupsPresenter provideMeetupsPresenter() {
        return new MyMeetupsPresenter();
    }

}
