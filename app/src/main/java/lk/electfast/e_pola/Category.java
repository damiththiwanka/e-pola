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
        data.add(new Item("Facebook", getResources().getDrawable(R.mipmap.ic_launcher)));
        data.add(new Item("Twitter", getResources().getDrawable(R.mipmap.ic_launcher)));
        data.add(new Item("Linked In", getResources().getDrawable(R.mipmap.ic_launcher)));
        data.add(new Item("Google", getResources().getDrawable(R.mipmap.ic_launcher)));
        data.add(new Item("Yahoo", getResources().getDrawable(R.mipmap.ic_launcher)));
        data.add(new Item("YouTube", getResources().getDrawable(R.mipmap.ic_launcher)));
        data.add(new Item("Flickr", getResources().getDrawable(R.mipmap.ic_launcher)));
        data.add(new Item("Whatsapp", getResources().getDrawable(R.mipmap.ic_launcher)));
        data.add(new Item("Blogger", getResources().getDrawable(R.mipmap.ic_launcher)));
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
        Toast.makeText(getApplicationContext(), message , Toast.LENGTH_SHORT).show();

if (message.equals("Facebook")){
        Intent i = new Intent(Category.this, MapsActivity.class);
        // passing array index
        i.putExtra("type", "atm");

        startActivity(i);



}

    }

}