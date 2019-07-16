package com.example.madproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;


public class Fragment_Student_Home extends Fragment implements View.OnClickListener {
    CardView analysisCard,attendanceCard,signoutCard;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView=inflater.inflate(R.layout.fragment__student__home, container, false);
        analysisCard=(CardView)myView.findViewById(R.id.analysis);
        attendanceCard=(CardView)myView.findViewById(R.id.sattend);
        signoutCard=(CardView)myView.findViewById(R.id.ssignout);

        analysisCard.setOnClickListener(this);
        attendanceCard.setOnClickListener(this);
        signoutCard.setOnClickListener(this);
        return myView;
    }



    public void onClick(View view){

        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        Fragment_Student_Attendance fat=new Fragment_Student_Attendance();
        Fragment_SpreadSheet_Teacher fst=new Fragment_SpreadSheet_Teacher();


        switch(view.getId()){
            case R.id.uploadCard:   fragmentTransaction.replace(R.id.fragment_container,fst,"upload card");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;

            case R.id.sattend:   fragmentTransaction.replace(R.id.fragment_container,fat,"attendance");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;

            case  R.id.ssignout:
                FirebaseAuth.getInstance().signOut();
                getActivity().finish();
                startActivity(new Intent(getActivity(),LoginActivity.class));
        }
    }


}

