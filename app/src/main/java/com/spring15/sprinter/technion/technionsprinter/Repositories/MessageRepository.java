package com.spring15.sprinter.technion.technionsprinter.Repositories;

import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.spring15.sprinter.technion.technionsprinter.Models.Category;
import com.spring15.sprinter.technion.technionsprinter.Models.Group;
import com.spring15.sprinter.technion.technionsprinter.Models.Message;

public class MessageRepository {

    public static void addMessage(Message message) {
        message.saveInBackground();
    }

    public static ParseQueryAdapter.QueryFactory<Message> getGroupMessages(final Group group){
        ParseQueryAdapter.QueryFactory<Message> factory =
                new ParseQueryAdapter.QueryFactory<Message>() {
                    public ParseQuery create() {
                        ParseQuery<Group> query = new ParseQuery<Group>("Messages");
                        query.include("sender");
                        query.whereEqualTo("group", group);
                        return query;
                    }
                };
        return factory;
    }
}
