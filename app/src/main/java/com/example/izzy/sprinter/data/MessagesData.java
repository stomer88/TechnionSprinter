package com.example.izzy.sprinter.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by izzy on 22/05/15.
 */
public class MessagesData {

    private List<MessageContent> myMessagesList = new ArrayList<>();

    public  List<MessageContent> getMyMessagesList()
    {
        return myMessagesList;
    }

    public void addMessage(MessageContent message1)
    {
        myMessagesList.add(message1);
    }

    public MessagesData()
    {
        addMessage(new MessageContent("hi, who wants to run?","15:14","haifa",3,"easy","profile1"));
        addMessage(new MessageContent("hi, who wants to run?","15:19","haifa",2,"hard","profile2"));
        addMessage(new MessageContent("hi, who wants to run?","14:14","haifa",4,"hard","profile3"));
        addMessage(new MessageContent("hi, who wants to run?","18:14","haifa",8,"easy","profile4"));
//        addMessage(new MessageContent("hi, who wants to run?","13:14","haifa",9,"medium"));
    }

    public MessagesData(int i)
    {

        String actionString = Constants.STRING_ACTIONS_ARRAY[i];
        addMessage(new MessageContent("hi, who wants  " + actionString + "?","15:14","haifa",3,"easy","profile1"));
        addMessage(new MessageContent("hi, who wants  " + actionString + "?","15:19","haifa",2,"hard","profile2"));
        addMessage(new MessageContent("hi, who wants  " + actionString + "?","14:14","haifa",4,"hard","profile3"));
        addMessage(new MessageContent("hi, who wants  " + actionString + "?","18:14","haifa",8,"easy","profile4"));

    }

}
