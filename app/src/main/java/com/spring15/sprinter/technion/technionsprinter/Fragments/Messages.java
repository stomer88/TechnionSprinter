package com.spring15.sprinter.technion.technionsprinter.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.spring15.sprinter.technion.technionsprinter.Activities.MainActivity;
import com.spring15.sprinter.technion.technionsprinter.Adapters.GroupsAdapter;
import com.spring15.sprinter.technion.technionsprinter.Adapters.MessagesAdapter;
import com.spring15.sprinter.technion.technionsprinter.Models.Category;
import com.spring15.sprinter.technion.technionsprinter.Models.Group;
import com.spring15.sprinter.technion.technionsprinter.Models.Message;
import com.spring15.sprinter.technion.technionsprinter.R;
import com.spring15.sprinter.technion.technionsprinter.Repositories.GroupRepository;
import com.spring15.sprinter.technion.technionsprinter.Repositories.MessageRepository;

public class Messages extends Fragment {

    public Messages() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Fragment Preparation
        final View rootView = inflater.inflate(R.layout.fragment_messages, container,
                false);
        int i = getArguments().getInt(MainActivity.ARG_MENU_NUMBER);
        final TextView time = (TextView) rootView.findViewById(R.id.groupTime);
        final TextView location = (TextView) rootView.findViewById(R.id.groupLocation);
        final TextView level = (TextView) rootView.findViewById(R.id.groupLevel);
        final TextView maxSize = (TextView) rootView.findViewById(R.id.groupSize);
        final Button sendButton = (Button) rootView.findViewById(R.id.sendButton);
        final EditText sendMessage = (EditText) rootView.findViewById(R.id.sendMessage);

        String groupObjectId = getArguments().getString("groupObjectId");
        if(groupObjectId == null) {
            String menuTitle = getResources().getStringArray(R.array.menu_list)[i];
            getActivity().setTitle(menuTitle);
        }
        else {
            ParseQuery<Group> query = ParseQuery.getQuery(Group.class);
            query.getInBackground(groupObjectId, new GetCallback<Group>() {
                public void done(final Group item, ParseException e) {
                    if (e == null) {
                        time.setText(item.getTime().toString().substring(0,19));
                        location.setText(item.getLocation());
                        level.setText(item.getLevel());
                        maxSize.setText(item.getSize() + "/" + item.getMaxSize());

                        // item was found
                        ParseQueryAdapter.QueryFactory<Message> factory = MessageRepository.getGroupMessages(item);
                        MessagesAdapter adapter = new MessagesAdapter(
                                getActivity().getApplicationContext(), factory, R.layout.messages_list_item);

                        // Attach it to your ListView, as in the example above
                        ListView listView = (ListView) rootView.findViewById(R.id.listview);
                        listView.setAdapter(adapter);

                        // onclick listeners
                        sendMessage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                MessageRepository.addMessage(new Message(sendMessage.getText().toString(), item));
                                sendMessage.setText("");
                            }
                        });
                    }
                }
            });
        }


        return rootView;
    }
}