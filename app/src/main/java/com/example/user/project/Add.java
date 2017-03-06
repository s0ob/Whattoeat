package com.example.user.project;

import android.content.ComponentCallbacks2;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class Add extends AppCompatActivity {
    public static final String EDIT_FOOD_1 = "editFood1";
    public static final String EDIT_PLACE_1 = "editPlace1";
    public static final String EDITID_1 = "editid1";
    Table myDb;
    EditText editFood,editPlace,editid;
    Button Add,Del,Selecta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        myDb= new Table(this);
        editFood=(EditText)findViewById(R.id.editText);
        editPlace=(EditText)findViewById(R.id.editText2);
        editid=(EditText)findViewById(R.id.editText3);
        Add=(Button)findViewById(R.id.button);
        Del=(Button)findViewById(R.id.button3);
        Selecta=(Button)findViewById(R.id.button4);
        if(savedInstanceState!=null){
           editFood.setText(savedInstanceState.getString("EDIT_FOOD_1"));
            editPlace.setText(savedInstanceState.getString("EDIT_PLACE_1"));
            editid.setText(savedInstanceState.getString("EDIT_ID_1"));
        }
        editFood.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (editFood.getText().length()==0) {
                    editFood.setError("Cannot be blank");
                    Add.setEnabled(false);
                }
                else{
                    Add.setEnabled(true);
                }
            }
        });
        editPlace.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (editPlace.getText().length()==0) {
                    editPlace.setError("Cannot be blank");
                }
            }
        });
        Adddata();
        Deldata();
        Alldata();
        Intent svc=new Intent(this, MusicService.class);
        startService(svc);
    }
    public void Adddata(){
        Add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            boolean isInserted = myDb.insertData(editFood.getText().toString(),
                                    editPlace.getText().toString());
                            if (isInserted == true)
                                Toast.makeText(Add.this, "Food Data inserted", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(Add.this, "Food Data not inserted", Toast.LENGTH_LONG).show();
                        }
                });
    }
    public void Alldata(){
        Selecta.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor sd = myDb.getalldata();
                        if (sd.getCount() == 0) {
                            showMessage("Error","Nothing Found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (sd.moveToNext()) {
                            buffer.append("Number:" + sd.getString(0) + "\n");
                            buffer.append("Name:" + sd.getString(1) + "\n");
                            buffer.append("Address:" + sd.getString(2) + "\n");
                        }
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage(Message);
        builder.show();
    }
    public void Deldata(){
        Del.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public  void onClick(View v){
                        Integer deletedRows= myDb.delData(editid.getText().toString());

                        if (deletedRows>0)
                            Toast.makeText(Add.this,"Food Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Add.this,"Food Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);//Menu Resource, Menu
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent i = new Intent(Add.this, MainActivity.class);
                startActivity(i);
                return true;
            case R.id.generate:
                Intent g = new Intent(Add.this, Generate.class);
                startActivity(g);
                return true;
            case R.id.add:
                Intent a = new Intent(Add.this, Add.class);
                startActivity(a);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(EDIT_FOOD_1,editFood.getText().toString());
        savedInstanceState.putString(EDIT_PLACE_1,editPlace.getText().toString());
        savedInstanceState.putString(EDITID_1,editid.getText().toString());
    }

}

