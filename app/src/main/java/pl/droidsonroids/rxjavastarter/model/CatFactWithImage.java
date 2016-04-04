package pl.droidsonroids.rxjavastarter.model;

public class CatFactWithImage {

    private String mCatFact;
    private int mCatImageId;

    public CatFactWithImage(final String catFact, final int catImageId) {
        mCatFact = catFact;
        mCatImageId = catImageId;
    }

    public String getCatFact() {
        return mCatFact;
    }

    public int getCatImageId() {
        return mCatImageId;
    }
}
