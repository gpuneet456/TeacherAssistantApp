package com.example.madproject;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.madproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef1 = database.getReference();

        if(savedInstanceState==null){
            FragmentManager fragmentManager=getSupportFragmentManager();
            final FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            myRef1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.child("Teachers").hasChild(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                        Fragment_Teacher_Home fragment_teacher_home=new Fragment_Teacher_Home();

                        fragmentTransaction.add(R.id.fragment_container,fragment_teacher_home);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                    else if(dataSnapshot.child("Students").hasChild(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                        Fragment_Student_Home fragment_student_home=new Fragment_Student_Home();
                        fragmentTransaction.add(R.id.fragment_container,fragment_student_home);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



        }
    }
}
