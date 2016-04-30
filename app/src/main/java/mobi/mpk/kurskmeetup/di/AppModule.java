package mobi.mpk.kurskmeetup.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mobi.mpk.kurskmeetup.application.ApiMeetupsService;
import mobi.mpk.kurskmeetup.application.presenter.MeetupsPresenter;
import mobi.mpk.kurskmeetup.application.presenter.MeetupsListPresenter;
import mobi.mpk.kurskmeetup.data.AsyncRepository;
import mobi.mpk.kurskmeetup.data.Urls;
import mobi.mpk.kurskmeetup.data.retrofit.UrlRepository;
import mobi.mpk.kurskmeetup.domain.MeetupsService;

@Module
public class AppModule {

    @Provides
    @Singleton
    public static AsyncRepository provideAsyncRepository() {
        return new UrlRepository(Urls.APIARY);
    }

    @Provides
    @Singleton
    public static MeetupsService provideMeetupsService(AsyncRepository repository) {
        return new ApiMeetupsService(repository);
    }

    @Provides
    @Singleton
    public static MeetupsPresenter provideMeetupsPresenter(MeetupsService service) {
        return new MeetupsListPresenter(service);
    }

}
