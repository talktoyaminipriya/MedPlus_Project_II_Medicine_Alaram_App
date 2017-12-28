package com.example.priya.medplus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Priya on 12/27/2017.
 */

public class ShopActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "MESSAGE";
    private ListView obj2;
    DBHelper mydb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);

        mydb2 = new DBHelper(this);
        ArrayList array_list2 = mydb2.getAllCotactsShop();
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array_list2);

        obj2 = (ListView) findViewById(R.id.listView2);
        obj2.setAdapter(arrayAdapter2);
        obj2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                int id_To_Search = arg2 + 1;

                Bundle dataBundle2 = new Bundle();
                dataBundle2.putInt("shopid", id_To_Search);

                Intent intent2 = new Intent(getApplicationContext(), DisplayWebsite.class);

                intent2.putExtras(dataBundle2);
                startActivity(intent2);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {

            case R.id.add:
                Bundle dataBundle2 = new Bundle();
                dataBundle2.putInt("shopid", 0);

                Intent intent2 = new Intent(getApplicationContext(), DisplayWebsite.class);
                intent2.putExtras(dataBundle2);

                startActivity(intent2);
                return true;


        }


        return super.onOptionsItemSelected(item);
    }


    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keycode, event);
    }
}
