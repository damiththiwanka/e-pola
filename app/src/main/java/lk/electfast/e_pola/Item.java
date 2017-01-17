package lk.electfast.e_pola;

/**
 * Created by MADHAWA on 1/14/2017.
 */

import android.graphics.drawable.Drawable;

public class Item
{
    String title;
    Drawable image;
    String id_cat;

    // Empty Constructor
    public Item()
    {

    }

    // Constructor
    public Item(String title, Drawable image,String id_cat)
    {
        super();
        this.title = title;
        this.image = image;
        this.id_cat = id_cat;
    }

    // Getter and Setter Method
    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public Drawable getImage()
    {
        return image;
    }

    public void setImage(Drawable image)
    {
        this.image = image;
    }

    public String getId_cat()
    {
        return id_cat;
    }

    public void setId_cat(String id_cat)
    {
        this.id_cat = id_cat;
    }



}
