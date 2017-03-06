package com.example.user.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by User on 4/3/2017.
 */

public class itemview extends Activity {
    // Declare Variables
    TextView TextView;
    TextView TextView2;
    String name;
    String address;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemview);
        // Retrieve data from MainActivity on item click event
        Intent i = getIntent();
        // Get the results of rank
        name = i.getStringExtra("name");
        // Get the results of country
        address = i.getStringExtra("address");

        // Locate the TextViews in singleitemview.xml
        TextView = (TextView) findViewById(R.id.textView);
        TextView2 = (TextView) findViewById(R.id.textView2);

        // Load the results into the TextViews
        TextView.setText(name);
        TextView2.setText(address);
    }
}
