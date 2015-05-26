package com.example.izzy.sprinter.data;

/**
 * Created by izzy on 22/05/15.
 */
public class Sport {

    public String name;
    public String pictureName;

    public Sport(String nameOfSport,String pictureText)
    {
        name = nameOfSport;
        pictureName =pictureText;
    }

    @Override
    public String toString() {
        return  name;
    }

    public String getPictureName()
    {
        return pictureName;
    }
}
