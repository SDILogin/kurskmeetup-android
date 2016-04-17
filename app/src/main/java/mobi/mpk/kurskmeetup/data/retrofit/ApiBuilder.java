package mobi.mpk.kurskmeetup.data.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ApiBuilder {
    private String url;

    public ApiBuilder(String url) {
        this.url = url;
    }

    public KurskMeetupApi build() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        return retrofit.create(KurskMeetupApi.class);
    }

}
