package edu.dlsu.mobapde.labdatabasephone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewPhoneActivitySkeleton extends AppCompatActivity {

    Button buttonEdit, buttonDelete;
    TextView tvSize, tvRes, tvManu;
    DatabaseHelper dbHelper;
    long id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_phone);

        tvSize = (TextView) findViewById(R.id.tv_size);
        tvRes = (TextView) findViewById(R.id.tv_resolution);
        tvManu = (TextView) findViewById(R.id.tv_manufacturer);
        buttonDelete = (Button) findViewById(R.id.button_delete);
        buttonEdit = (Button) findViewById(R.id.button_edit);

        // TODO initialize dbHelper
        dbHelper = ;

        // TODO get the id from MainAcitivity
        id = ;

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.removePhone(id);
            }
        });

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Lead the user to the create activity
                // Note: we don't want to add, we just want to edit; set the extra to add:false
                //       we also want to pass the id of this current object
                Intent intent = ;




                // END

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // TODO load the details from the database
        Phone phone = ;

        if(phone!=null){
            // TODO load object details to the TextViews
        }else{
            finish();
        }
    }
}
