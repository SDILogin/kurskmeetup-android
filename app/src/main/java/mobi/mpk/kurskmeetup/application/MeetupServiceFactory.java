package mobi.mpk.kurskmeetup.application;

import mobi.mpk.kurskmeetup.data.AsyncRepository;
import mobi.mpk.kurskmeetup.data.AsyncRepositoryFactory;
import mobi.mpk.kurskmeetup.data.Urls;
import mobi.mpk.kurskmeetup.domain.MeetupsService;

public class MeetupServiceFactory {

    public MeetupsService create() {
        return new ApiMeetupsService(new AsyncRepositoryFactory().create(Urls.APIARY));
    }

    public MeetupsService create(AsyncRepository repository) {
        return new ApiMeetupsService(repository);
    }

}
