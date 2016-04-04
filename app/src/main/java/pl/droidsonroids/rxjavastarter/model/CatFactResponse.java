package pl.droidsonroids.rxjavastarter.model;

import java.util.List;

public class CatFactResponse {

    private boolean success;
    private List<String> facts;

    public CatFactResponse() {}

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(final boolean success) {
        this.success = success;
    }

    public List<String> getFacts() {
        return facts;
    }

    public void setFacts(final List<String> facts) {
        this.facts = facts;
    }
}
