package com.example.izzy.sprinter.data;

/**
 * Created by izzy on 22/05/15.
 */
public class MessageContent {

    public String title;
    public String time;
    public String location;
    public int groupSize;
    public String level;
    public String myPicture;

    public MessageContent(String title1,String time1,String location1,int groupSize1,
                          String level1,String myPicture1)

    {
        title = title1;
        time = time1;
        location = location1;
        groupSize =groupSize1;
        level = level1;
        myPicture = myPicture1;
    }



}
