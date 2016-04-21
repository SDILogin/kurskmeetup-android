package mobi.mpk.kurskmeetup.application;

import mobi.mpk.kurskmeetup.data.AsyncRepository;
import mobi.mpk.kurskmeetup.data.AsyncRepositoryFactory;
import mobi.mpk.kurskmeetup.data.Urls;
import mobi.mpk.kurskmeetup.domain.MeetupsService;

public class MeetupServiceFactory {
    private static MeetupServiceFactory instance;

    private MeetupServiceFactory() {}

    public static MeetupServiceFactory getInstance() {
        if (instance == null) {
            instance = new MeetupServiceFactory();
        }
        return instance;
    }

    public MeetupsService create() {
        return new ApiMeetupsService(AsyncRepositoryFactory.getInstance().create(Urls.APIARY));
    }

    public MeetupsService create(AsyncRepository repository) {
        return new ApiMeetupsService(repository);
    }

}
