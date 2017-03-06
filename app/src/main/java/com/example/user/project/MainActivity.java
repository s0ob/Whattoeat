package com.example.user.project;

import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import static com.example.user.project.R.layout.itemview;
import static com.example.user.project.R.string.foodname;

public class MainActivity extends AppCompatActivity {
    EditText item;
    ListView listview;
    ListViewAdapter adapter;
    String[] name;
    String[] address;
    ArrayList<World> arrayList= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        item = (EditText) findViewById(R.id.editText4);
        listview = (ListView) findViewById(R.id.list_item);
        name=new String[]{"Mee Goreng","Asam Laksa","Curry Mee","Char Kway Teow","Seafood Popiah","Hokkien Mee","Loh Mee"};
        address=new String[]{"270 Jalan Burma,Lorong Bangkok","11500 Air Itam,Jalan Pasar","23,Lebuh Kimerley","108,Lorong Selamat",
        "Stall 17,Gerai Gerai Padang Brown","Jalan Penaga,Jelutong","336-G1 Lintang Slim,off Jalan Perak"};
        for(int i=0;i<name.length;i++){
            World wp=new World(name[i],address[i]);
                    arrayList.add(wp);
        }
        adapter=new ListViewAdapter(this,arrayList);
        listview.setAdapter(adapter);
        Intent svc = new Intent(this, MusicService.class);
        startService(svc);
        item.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = item.getText().toString().toLowerCase(Locale.getDefault());
                 adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.menu, menu);//Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                startActivity(i);
                return true;
            case R.id.generate:
                Intent g = new Intent(MainActivity.this, Generate.class);
                startActivity(g);
                return true;
            case R.id.add:
                Intent a = new Intent(MainActivity.this, Add.class);
                startActivity(a);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}





