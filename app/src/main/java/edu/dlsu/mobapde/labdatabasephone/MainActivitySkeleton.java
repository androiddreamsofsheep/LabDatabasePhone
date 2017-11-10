package edu.dlsu.mobapde.labdatabasephone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class MainActivitySkeleton extends AppCompatActivity {

    RecyclerView rvPhones;
    Button tvAddPhone;
    DatabaseHelper dbHelper;
    PhonesAdapter phonesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize views
        rvPhones = (RecyclerView) findViewById(R.id.rv_phones);
        tvAddPhone = (Button) findViewById(R.id.button_add_phone);

        // initialize items to the database
        dbHelper = new DatabaseHelper(getBaseContext());

        phonesAdapter
                = new PhonesAdapter(getBaseContext(),
                    dbHelper.getAllPhonesCursor());

        rvPhones.setAdapter(phonesAdapter);
        rvPhones.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        tvAddPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO lead the user to the activity to create a new record
                Intent intent = ;
                // Note: make sure to pass an extra that we want to add and not edit


                // END

                startActivity(intent);
            }
        });

        phonesAdapter.setOnItemClickListener(new PhonesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(long id) {
                // TODO lead the user to the activity to view the clicked item
                Intent intent = ;
                // Note: make sure to pass an extra that we want to edit and not add

                //       also pass the id of the selected item

                // END
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
