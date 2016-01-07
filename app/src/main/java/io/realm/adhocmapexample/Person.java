package io.realm.adhocmapexample;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Required;

public class Person extends RealmObject {
    private String name;
    private RealmList<Tuple> tuples;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Tuple> getTuples() {
        return tuples;
    }

    public void setTuples(RealmList<Tuple> tuples) {
        this.tuples = tuples;
    }
}
