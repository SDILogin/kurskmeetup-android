package mobi.mpk.kurskmeetup.ioc;

import org.junit.Test;

import mobi.mpk.kurskmeetup.application.ApiMeetupsService;
import mobi.mpk.kurskmeetup.application.MeetupServiceFactory;
import mobi.mpk.kurskmeetup.application.presenter.MeetupsPresenter;
import mobi.mpk.kurskmeetup.application.presenter.MeetupsPresenterFactory;
import mobi.mpk.kurskmeetup.application.presenter.MyMeetupsPresenter;
import mobi.mpk.kurskmeetup.data.AsyncRepository;
import mobi.mpk.kurskmeetup.data.AsyncRepositoryFactory;
import mobi.mpk.kurskmeetup.data.retrofit.UrlRepository;
import mobi.mpk.kurskmeetup.domain.MeetupsService;

import static org.junit.Assert.*;

public class ComponentFactoryTest {

    @Test
    public void testGetInstance() throws Exception {

        ComponentFactory factory = ComponentFactory.getInstance();
        ComponentFactory factory1 = ComponentFactory.getInstance();

        assertEquals(factory, factory1);
    }

    @Test
    public void testCreateComponent() throws Exception {
        // Test case 1 UrlRepository

        AsyncRepository repository = AsyncRepositoryFactory.getInstance().create("http://ya.ru");
        AsyncRepository repository1 = (AsyncRepository) ComponentFactory.getInstance().createComponent(UrlRepository.class);

        assertEquals(repository.getClass(), repository1.getClass());
        // Test case 2 ApiMeetupsService

        MeetupsService service = MeetupServiceFactory.getInstance().create();
        MeetupsService service1 = (MeetupsService) ComponentFactory.getInstance().createComponent(ApiMeetupsService.class);

        assertEquals(service.getClass(), service1.getClass());
        // Test case 3 MyMeetupsPresenter

        MeetupsPresenter presenter = MeetupsPresenterFactory.getInstance().create();
        MeetupsPresenter presenter1 = (MeetupsPresenter) ComponentFactory.getInstance().createComponent(MyMeetupsPresenter.class);

        assertEquals(presenter.getClass(), presenter1.getClass());
        // Test case 4 MeetupsPresenter interface

        MeetupsPresenter presenter2 = (MeetupsPresenter) ComponentFactory.getInstance().createComponent(MeetupsPresenter.class);

        assertEquals(presenter.getClass(), presenter2.getClass());
    }
}