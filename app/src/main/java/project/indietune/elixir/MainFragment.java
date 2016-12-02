package project.indietune.elixir;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private TextView mTitleView, mAuthorView, mDescriptionView;
    private Firebase mTitle, mAuthor, mDescription;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> list_of_rooms = new ArrayList<>();
    private Button add_room;
    private EditText room_name;
    private String name;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference().getRoot();

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        add_room = (Button) getActivity().findViewById(R.id.buttonTopic);
        room_name = (EditText) getActivity().findViewById(R.id.editTextTopic);
        listView = (ListView) getActivity().findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list_of_rooms);
        listView.setAdapter(arrayAdapter);
        request_user_name();
        add_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(room_name.getText().toString(), "");
                root.updateChildren(map);
            }
        });
        mTitleView = (TextView) getActivity().findViewById(R.id.textViewTitle);
        mAuthorView = (TextView) getActivity().findViewById(R.id.textViewAuthor);
        mDescriptionView = (TextView) getActivity().findViewById(R.id.textViewDesc);
        mTitle = new Firebase("https://elixir-95d35.firebaseio.com/title");
        mTitle.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);
                mTitleView.setText(value);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mAuthor = new Firebase("https://elixir-95d35.firebaseio.com/author");
        mAuthor.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                mAuthorView.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mDescription = new Firebase("https://elixir-95d35.firebaseio.com/description");
        mDescription.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                mDescriptionView.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    private void request_user_name() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Enter name: ");
        final EditText input_field = new EditText(getActivity());
        builder.setView(input_field);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                name = input_field.getText().toString();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                request_user_name();
            }
        });
        builder.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

}
