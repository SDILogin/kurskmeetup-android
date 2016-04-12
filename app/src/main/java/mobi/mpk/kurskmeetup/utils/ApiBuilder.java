package mobi.mpk.kurskmeetup.utils;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ApiBuilder {
    private static final String BASE_URL = "http://private-anon-5d9ca48f1-kurskmeetupapi.apiary-mock.com/";

    private static KurskMeetupApi api;

    static {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        api = retrofit.create(KurskMeetupApi.class);
    }

    public static KurskMeetupApi getApi() {
        return api;
    }

}
