package mobi.mpk.kurskmeetup.data;

import mobi.mpk.kurskmeetup.data.retrofit.UrlRepository;

public class AsyncRepositoryFactory {
    private static AsyncRepositoryFactory instance;

    private AsyncRepositoryFactory() {}

    public static AsyncRepositoryFactory getInstance() {
        if (instance == null) {
            instance = new AsyncRepositoryFactory();
        }
        return instance;
    }

    public AsyncRepository create(String url) {
        return new UrlRepository(url);
    }

}
