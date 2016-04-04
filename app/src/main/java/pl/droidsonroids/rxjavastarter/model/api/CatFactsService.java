package pl.droidsonroids.rxjavastarter.model.api;

import pl.droidsonroids.rxjavastarter.model.CatFactResponse;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class CatFactsService {

    private static volatile CatFactsService mCatsFactsService;
    private final CatFactsApi mCatsFactsAPI;

    private CatFactsService() {
        mCatsFactsAPI = new Retrofit.Builder()
                .baseUrl("http://catfacts-api.appspot.com/api/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CatFactsApi.class);
    }

    public static CatFactsService getInstance() {
        if(mCatsFactsService == null) {
            synchronized (CatFactsService.class) {
                if(mCatsFactsService == null) {
                    mCatsFactsService = new CatFactsService();
                }
            }
        }
        return mCatsFactsService;
    }

    public Observable<CatFactResponse> getCatFacts(final int number) {
        return mCatsFactsAPI.getCatsFacts(number);
    }
}
