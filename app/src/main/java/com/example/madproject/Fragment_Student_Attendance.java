package com.example.madproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Fragment_Student_Attendance extends Fragment {
    Spinner subjectList;
    String sectionValue, subjectValue;
    Button attendance, sheet;
    FirebaseDatabase database;
    DatabaseReference myref;
    TextView t;
    String text;
    String fsubject;
    List<String> sec = new ArrayList<>();
    String subjects[] = {"mad", "fln", "cc"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_fragment__student__attendance, container, false);

        t=myView.findViewById(R.id.tv);
        text="";

        subjectList = myView.findViewById(R.id.subjectList);
        attendance = (Button) myView.findViewById(R.id.takeAttendance);
        sheet = (Button) myView.findViewById(R.id.generateSheet);
        database=FirebaseDatabase.getInstance();
        myref=database.getReference();

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, subjects);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        subjectList.setAdapter(adapter2);

        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject=subjectList.getSelectedItem().toString();
                fsubject="6b_"+subject;
                myref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        text=dataSnapshot.child("Students").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("usn").getValue(String.class);
                        String attend=dataSnapshot.child(fsubject).child(text).child("attended").getValue(String.class);
                        t.setText(attend);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Toast.makeText(getActivity(),"clicked",Toast.LENGTH_LONG).show();
            }
        });
        sheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        return myView;
    }
}
