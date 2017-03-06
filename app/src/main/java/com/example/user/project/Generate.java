package com.example.user.project;

import android.content.ComponentCallbacks2;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import static android.R.attr.id;
import static android.R.id.button1;

public class Generate extends AppCompatActivity implements Animation.AnimationListener {
    Table myDb;
    Button generate;
    Animation wheelani;
    TextView txtMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);
        myDb= new Table(this);
        wheelani= AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.wheel);
        wheelani.setAnimationListener(this);
        generate=(Button)findViewById(R.id.button1);
        ani();
        Intent svc=new Intent(this, MusicService.class);
        startService(svc);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);//Menu Resource, Menu
        return true;
    }
    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Cursor gn=myDb.getname();
        if (gn.getCount() == 0) {
            showMessage("Error","Nothing Found");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (gn.moveToNext()) {
            buffer.append(gn.getString(0) + "\n");
        }
        showMessage("Data",buffer.toString());
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent i = new Intent(Generate.this, MainActivity.class);
                startActivity(i);
                return true;
            case R.id.generate:
                Intent g = new Intent(Generate.this, Generate.class);
                startActivity(g);
                return true;
            case R.id.add:
                Intent a = new Intent(Generate.this, Add.class);
                startActivity(a);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void ani(){
        generate.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(wheelani);
                // start the animation
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
}

