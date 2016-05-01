package mobi.mpk.kurskmeetup.data.retrofit;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import mobi.mpk.kurskmeetup.data.AsyncRepository;
import mobi.mpk.kurskmeetup.data.BadResponse;
import mobi.mpk.kurskmeetup.data.OnDataLoadListener;
import mobi.mpk.kurskmeetup.domain.dto.Meetup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UrlRepository implements AsyncRepository {

    private KurskMeetupApi api;

    @Inject
    public UrlRepository(KurskMeetupApi api) {
        this.api = api;
    }

    @Override
    public Observable<List<Meetup>> getMeetups() {
        return api.listMeetups()
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Meetup> getMeetup(int id) {
        return Observable.just(new Meetup());
    }

}
