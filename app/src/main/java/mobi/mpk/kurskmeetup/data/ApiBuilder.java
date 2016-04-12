package mobi.mpk.kurskmeetup.data;

import mobi.mpk.kurskmeetup.data.apiary.Apiary;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ApiBuilder {
    private static KurskMeetupApi api;

    static {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Apiary.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        api = retrofit.create(KurskMeetupApi.class);
    }

    public static KurskMeetupApi getApi() {
        return api;
    }

}
