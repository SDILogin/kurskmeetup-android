package mobi.mpk.kurskmeetup.application;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

import mobi.mpk.kurskmeetup.data.AsyncRepository;
import mobi.mpk.kurskmeetup.data.OnDataLoadListener;
import mobi.mpk.kurskmeetup.domain.MeetupsService;
import mobi.mpk.kurskmeetup.domain.dto.Meetup;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ApiMeetupsServiceTest {
    private AsyncRepository repository;
    private MeetupsService service;

    @Before
    public void init() {
        repository = mock(AsyncRepository.class);
        service = new ApiMeetupsService(repository);
    }

    @Test
    public void testGetMeetups() throws Exception {

        service.getMeetups();

        verify(repository).getMeetups(any(OnDataLoadListener.class));
    }

    @Test
    public void testRegisterObserver() throws Exception {
        OnDataLoadListener listener = new OnDataLoadListener<List<Meetup>>() {
            @Override
            public void onSuccess(List<Meetup> data) {

            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        };

        service.registerMeetupsObserver(listener);

        Field observersList = service.getClass().getDeclaredField("observers");
        observersList.setAccessible(true);
        List<OnDataLoadListener<List<Meetup>>> observers =
                (List<OnDataLoadListener<List<Meetup>>>) observersList.get(service);
        assertTrue(observers.contains(listener));
    }

    @Test
    public void testUnregisterObserver() throws Exception {
        OnDataLoadListener listener = new OnDataLoadListener() {
            @Override
            public void onSuccess(Object data) {

            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        };

        service.registerMeetupsObserver(listener);
        service.unregisterMeetupsObserver(listener);

        Field observersList = service.getClass().getDeclaredField("observers");
        observersList.setAccessible(true);
        List<OnDataLoadListener<List<Meetup>>> observers =
                (List<OnDataLoadListener<List<Meetup>>>) observersList.get(service);
        assertFalse(observers.contains(listener));
    }
}
