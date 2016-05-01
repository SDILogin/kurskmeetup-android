package mobi.mpk.kurskmeetup.di;

import dagger.Module;
import dagger.Provides;
import mobi.mpk.kurskmeetup.application.ApiMeetupsService;
import mobi.mpk.kurskmeetup.application.presenter.MeetupsListPresenter;
import mobi.mpk.kurskmeetup.application.presenter.MeetupsPresenter;
import mobi.mpk.kurskmeetup.data.AsyncRepository;
import mobi.mpk.kurskmeetup.data.retrofit.KurskMeetupApi;
import mobi.mpk.kurskmeetup.data.retrofit.UrlRepository;
import mobi.mpk.kurskmeetup.domain.MeetupsService;

@Module
public class MeetupsModule {

    @Provides
    public AsyncRepository provideUrlRepository(KurskMeetupApi api) {
        return new UrlRepository(api);
    }

    @Provides
    public MeetupsService provideMeetupsService(AsyncRepository repository) {
        return new ApiMeetupsService(repository);
    }

    @Provides
    public MeetupsPresenter provideMeetupsPresenter(MeetupsService interactor) {
        return new MeetupsListPresenter(interactor);
    }
}
