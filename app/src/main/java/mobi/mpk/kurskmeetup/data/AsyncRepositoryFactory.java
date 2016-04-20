package mobi.mpk.kurskmeetup.data;

import mobi.mpk.kurskmeetup.data.retrofit.UrlRepository;

public class AsyncRepositoryFactory {

    public AsyncRepository create(String url) {
        return new UrlRepository(url);
    }

}
