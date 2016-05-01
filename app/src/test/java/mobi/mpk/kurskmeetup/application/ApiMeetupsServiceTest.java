package mobi.mpk.kurskmeetup.application;

import org.junit.Before;

import mobi.mpk.kurskmeetup.data.AsyncRepository;
import mobi.mpk.kurskmeetup.domain.MeetupsService;

import static org.mockito.Mockito.mock;

public class ApiMeetupsServiceTest {
    private AsyncRepository repository;
    private MeetupsService service;

    @Before
    public void init() {
        repository = mock(AsyncRepository.class);
        service = new ApiMeetupsService(repository);
    }
}
