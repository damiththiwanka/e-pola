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
    private geo_list_Adapter mGeo_list_adapter=null;

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
        data.add(new Item("Carpenter", getResources().getDrawable(R.drawable.carpenter),"1"));
        data.add(new Item("Cleaning Service", getResources().getDrawable(R.drawable.cleaner),"2"));
        data.add(new Item("Delivery", getResources().getDrawable(R.drawable.delivery),"3"));
        data.add(new Item("Medical Services", getResources().getDrawable(R.drawable.doctor),"4"));
        data.add(new Item("Electrician", getResources().getDrawable(R.drawable.electrician),"5"));
        data.add(new Item("legal advice Services", getResources().getDrawable(R.drawable.lawyer),"6"));
        data.add(new Item("Catering Services", getResources().getDrawable(R.drawable.meals),"7"));
        data.add(new Item("Painting Services", getResources().getDrawable(R.drawable.paintroll),"8"));
        data.add(new Item("Plumbing Services", getResources().getDrawable(R.drawable.pipe),"9"));
        data.add(new Item("Taylor Services", getResources().getDrawable(R.drawable.sewingmachine),"10"));
        data.add(new Item("Taxi Services", getResources().getDrawable(R.drawable.taxi),"11"));
        data.add(new Item(" mechanical Services", getResources().getDrawable(R.drawable.wrench),"12"));
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

        mGeo_list_adapter = new geo_list_Adapter();
        mGeo_list_adapter.execute((Void) null);
       String date2= mGeo_list_adapter.JsonResponse;
        i.putExtra("id", date2);

        startActivity(i);






//}

    }

}