package project.indietune.elixir;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private TextView mTitleView, mAuthorView, mDescriptionView;
    private Firebase mTitle, mAuthor, mDescription;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

}
