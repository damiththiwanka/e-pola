package lk.electfast.e_pola;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class Category extends Activity implements OnItemClickListener
{
    GridView gridview;
    GridViewAdapter gridviewAdapter;
    ArrayList<Item> data = new ArrayList<Item>();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_category);

        initView(); // Initialize the GUI Components
        fillData(); // Insert The Data
        setDataAdapter(); // Set the Data Adapter
    }

    // Initialize the GUI Components
    private void initView()
    {
        gridview = (GridView) findViewById(R.id.gridView);
        gridview.setOnItemClickListener(this);
    }

    // Insert The Data
    private void fillData()
    {
        data.add(new Item("Carpenter", getResources().getDrawable(R.drawable.carpenter),"587e764181d86cad71f32a8d"));
        data.add(new Item("Cleaning Service", getResources().getDrawable(R.drawable.cleaner),"587e77a1324ad1b12d19e91d"));
        data.add(new Item("Delivery", getResources().getDrawable(R.drawable.delivery),"587e79884ccfa55c5c4b6731"));
        data.add(new Item("Medical Services", getResources().getDrawable(R.drawable.doctor),"587e79fc4ccfa55c5c4b6732"));
        data.add(new Item("Electrician", getResources().getDrawable(R.drawable.electrician),"587e7a0b4ccfa55c5c4b6733"));
        data.add(new Item("legal advice Services", getResources().getDrawable(R.drawable.lawyer),"587e7a244ccfa55c5c4b6734"));
        data.add(new Item("Catering Services", getResources().getDrawable(R.drawable.meals),"587e7a3c4ccfa55c5c4b6735"));
        data.add(new Item("Painting Services", getResources().getDrawable(R.drawable.paintroll),"587e7a534ccfa55c5c4b6736"));
        data.add(new Item("Plumbing Services", getResources().getDrawable(R.drawable.pipe),"587e7a764ccfa55c5c4b6737"));
        data.add(new Item("Taylor Services", getResources().getDrawable(R.drawable.sewingmachine),"587e7a864ccfa55c5c4b6738"));
        data.add(new Item("Taxi Services", getResources().getDrawable(R.drawable.taxi),"587e7a984ccfa55c5c4b6739"));
        data.add(new Item("Mechanical Services", getResources().getDrawable(R.drawable.wrench),"587e7aa84ccfa55c5c4b673a"));
    }

    // Set the Data Adapter
    private void setDataAdapter()
    {
        gridviewAdapter = new GridViewAdapter(getApplicationContext(), R.layout.category, data);
        gridview.setAdapter(gridviewAdapter);
    }

    @Override
    public void onItemClick(final AdapterView<?> arg0, final View view, final int position, final long id)
    {
        String message = data.get(position).getTitle();
        String ID_cat = data.get(position).getId_cat();

        Toast.makeText(getApplicationContext(), message +" "+ ID_cat, Toast.LENGTH_SHORT).show();

//if (message.equals("Carpenter")){
        Intent i = new Intent(Category.this, MapsActivity.class);
        // passing array index


        i.putExtra("id", ID_cat);

        startActivity(i);






//}

    }

}