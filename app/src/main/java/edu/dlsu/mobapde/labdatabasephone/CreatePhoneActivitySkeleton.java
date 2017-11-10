package edu.dlsu.mobapde.labdatabasephone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreatePhoneActivitySkeleton extends AppCompatActivity {

    EditText etRes, etSize, etManu;
    Button buttonDone;
    long id = -1;
    boolean addPhone = false;
    DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_phone);

        etRes = (EditText) findViewById(R.id.et_resolution);
        etSize = (EditText) findViewById(R.id.et_size);
        etManu = (EditText) findViewById(R.id.et_manufacturer);
        buttonDone = (Button) findViewById(R.id.button_done);

        // TODO initialize dbHelper
        dbHelper = new DatabaseHelper(getBaseContext());

        // This activity should be able to handle both creating and editing a phone
        // We will know based on which activity called this instance

        // In MainActivity (buttonAdd) -> extra should be add: true
        // In ViewPhoneActivity (buttonEdit) -> extra should be add:false
        // TODO check previous activity's extra to know whether to add or edit

        addPhone = ;

        // if user wants to edit, we should load the current values of the phone
        if(!addPhone){
            // get phone id from extras
            id = ;
            // TODO call databasehelper and find phone object given id
            Phone phone = ;

            if(phone!=null){
                // TODO load phone details to editTexts
            }
        }

        // Question : Do we really need the addPhone boolean, or can we do with fewer variables?


        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Phone phone = new Phone();
                // TODO get the user's input and set them as phone's properties
                

                // insert/edit the phone,
                if(addPhone){
                    // TODO call databasehelper to addPhone

                }else{
                    // TODO call databasehelper to editPhone, user phoneId

                }
                finish();
            }
        });
    }
}
