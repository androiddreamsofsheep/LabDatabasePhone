package edu.dlsu.mobapde.labdatabasephone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvPhones;
    Button tvAddPhone;
    DatabaseHelper dbHelper;
    PhonesAdapter phonesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvPhones = (RecyclerView) findViewById(R.id.rv_phones);
        tvAddPhone = (Button) findViewById(R.id.button_add_phone);

        dbHelper = new DatabaseHelper(getBaseContext());
        dbHelper.addPhone(new Phone("200x300", 300, "Cherry"));
        dbHelper.addPhone(new Phone("300x400", 330, "Huawei"));

        phonesAdapter
                = new PhonesAdapter(getBaseContext(),
                    dbHelper.getAllPhonesCursor());

        rvPhones.setAdapter(phonesAdapter);
        rvPhones.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        tvAddPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), CreatePhoneActivity.class);
                i.putExtra("add", true);
                startActivity(i);
            }
        });

        phonesAdapter.setOnItemClickListener(new PhonesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(long id) {
                Intent intent = new Intent(getBaseContext(), ViewPhoneActivity.class);
                intent.putExtra(Phone.EXTRA_ID, id);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        phonesAdapter.changeCursor(dbHelper.getAllPhonesCursor());
    }
}
