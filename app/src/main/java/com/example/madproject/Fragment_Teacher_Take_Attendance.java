package com.example.madproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Fragment_Teacher_Take_Attendance extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<attendance_card_item> cardList;
    ArrayList<attendance_card_item> retrive;
    DatabaseReference myRef1, myref2;
    Button b;
    ArrayList<String> a;
    String subject,fsubject;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment__teacher__take__attendance, container, false);
        b = myView.findViewById(R.id.button);
        mRecyclerView = myView.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        retrive = new ArrayList<attendance_card_item>();
        cardList = new ArrayList<>();
        a = new ArrayList<String>();
        subject=getArguments().getString("subject");
        fsubject="6b_"+subject;

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef1 = database.getReference("Teachers");
        myref2 = database.getReference(fsubject);
        myRef1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).hasChild(fsubject)) {
                    Log.d("Inside uid", "above uid");
                    myref2.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot d : dataSnapshot.getChildren()) {
                                Log.d("Inside uid", "reached uid");
                                attendance_card_item c = d.getValue(attendance_card_item.class);
                                cardList.add(c);


                            }
                            mAdapter = new exampleAdapter(cardList, Fragment_Teacher_Take_Attendance.this);

                            mRecyclerView.setAdapter(mAdapter);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                } else {
                    Toast.makeText(getActivity(),"class not assigned",Toast.LENGTH_LONG).show();
                    FragmentManager fragmentManager=getFragmentManager();
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    Fragment_Attendance_Teacher fat=new Fragment_Attendance_Teacher();
                    fragmentTransaction.replace(R.id.fragment_container,fat,"attendance");

                    fragmentTransaction.commit();
                    Log.d("outside uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (attendance_card_item e : retrive) {

                    Integer count=Integer.parseInt(e.getAttended());
                    count++;
                    myref2.child(e.getUsn()).child("attended").setValue(count.toString());

                }

            }
        });
        return myView;
    }

    public void set(attendance_card_item e) {
        retrive.add(e);
        Toast.makeText(getActivity(), e.getUsn(), Toast.LENGTH_LONG).show();
    }
    public void unset(attendance_card_item e) {
        retrive.remove(e);
        Toast.makeText(getActivity(), e.getUsn(), Toast.LENGTH_LONG).show();
    }


}
