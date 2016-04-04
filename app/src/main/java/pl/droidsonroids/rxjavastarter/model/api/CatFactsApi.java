package pl.droidsonroids.rxjavastarter.model.api;

import pl.droidsonroids.rxjavastarter.model.CatFactResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface CatFactsApi {
    @GET("facts")
    Observable<CatFactResponse> getCatsFacts(@Query("number") int number);
}
