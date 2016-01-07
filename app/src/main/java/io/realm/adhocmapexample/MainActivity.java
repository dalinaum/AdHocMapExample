package io.realm.adhocmapexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Realm realm = Realm.getInstance(this);

        realm.beginTransaction();
        RealmResults<Tuple> tuples = realm.where(Tuple.class).findAll();
        RealmResults<Person> persons = realm.where(Person.class).findAll();
        tuples.clear();
        persons.clear();
        realm.commitTransaction();

        realm.beginTransaction();
        Person brad = realm.createObject(Person.class);
        brad.setName("Brad Pitt");
        Tuple t1 = realm.createObject(Tuple.class);
        t1.setKey("First");
        t1.setValue("1st");
        t1.setPerson(brad);
        brad.getTuples().add(t1);

        Tuple t2 = realm.createObject(Tuple.class);
        t2.setKey("Second");
        t2.setValue("2nd");
        t2.setPerson(brad);
        brad.getTuples().add(t2);

        Tuple t3 = realm.createObject(Tuple.class);
        t3.setKey("Third");
        t3.setValue("3rd");
        t3.setPerson(brad);
        brad.getTuples().add(t3);

        Person angelina = realm.createObject(Person.class);
        angelina.setName("Angelina Jolie");
        Tuple t4 = realm.createObject(Tuple.class);
        t4.setKey("First");
        t4.setValue("1st - 2");
        t4.setPerson(angelina);
        angelina.getTuples().add(t4);

        Tuple t5 = realm.createObject(Tuple.class);
        t5.setKey("Second");
        t5.setValue("2nd - 2");
        t5.setPerson(angelina);
        angelina.getTuples().add(t5);

        Tuple t6 = realm.createObject(Tuple.class);
        t6.setKey("Third");
        t6.setValue("3rd - 2");
        t6.setPerson(angelina);
        angelina.getTuples().add(t6);

        realm.commitTransaction();

        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
        layout.removeAllViews();

        Tuple secondOfBrad = realm.where(Tuple.class).equalTo("person.name", "Brad Pitt").equalTo("key", "Second").findFirst();
        String bradText = "Second of Brad: " + secondOfBrad.getValue();
        TextView bradTextView = new TextView(this);
        bradTextView.setText(bradText);
        layout.addView(bradTextView);

        Tuple secondOfAngelina = realm.where(Tuple.class).equalTo("person.name", "Angelina Jolie").equalTo("key", "Second").findFirst();
        String angelinaText = "Second of Angelina: " + secondOfAngelina.getValue();
        TextView angelinaTextView = new TextView(this);
        angelinaTextView.setText(angelinaText);
        layout.addView(angelinaTextView);
    }
}
