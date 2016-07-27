package pl.droidsonroids.rxjavastarter.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import butterknife.Bind;
import butterknife.ButterKnife;

import java.util.List;
import java.util.Random;
import pl.droidsonroids.rxjavastarter.R;
import pl.droidsonroids.rxjavastarter.model.CatFactResponse;
import pl.droidsonroids.rxjavastarter.model.CatFactWithImage;
import pl.droidsonroids.rxjavastarter.model.api.CatFactsService;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.recycler_view) RecyclerView mRecyclerView;

    private final int[] catImagesId = {R.drawable.cat1, R.drawable.cat2, R.drawable.cat3};
    private CatFactsAdapter mCatFactsAdapter;
    private Subscription mSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initCatFactsList();
    }

    private void initCatFactsList() {
        mCatFactsAdapter = new CatFactsAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mCatFactsAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadCatFacts();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    private void loadCatFacts() {
        mSubscription = CatFactsService.getInstance().getCatFacts(100)
                .map(catFactResponse -> catFactResponse.getFacts())
                .flatMap(catFacts -> Observable.from(catFacts))
                .distinct()
                .filter(catFact -> catFact.length() <= 300)
                .take(3)
                .map(catFact -> new CatFactWithImage(catFact, MainActivity.this.getRandomCatImageId()))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mCatFactsAdapter::setCatFactWithImages, Throwable::printStackTrace);
    }

    private int getRandomCatImageId() {
        return catImagesId[new Random().nextInt(3)];
    }
}
