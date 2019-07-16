package com.example.madproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Fragment_Attendance_Teacher extends Fragment  {
    Spinner sectionList;
    Spinner subjectList;
    String sectionValue,subjectValue;
    Button attendance,sheet;

    List<String> sec=new ArrayList<>();
    String subjects[]={"cc","fln","mad"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView=inflater.inflate(R.layout.fragment__attendance_teacher, container, false);

        sec.add("6a");
        sec.add("6b");
        sec.add("6c");
        sectionList=myView.findViewById(R.id.sectionList);
        subjectList=myView.findViewById(R.id.subjectList);
        attendance=(Button)myView.findViewById(R.id.takeAttendance);
        sheet=(Button)myView.findViewById(R.id.generateSheet);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,sec);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapter2=new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,subjects);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sectionList.setAdapter(adapter);
        subjectList.setAdapter(adapter2);

        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sectionValue=sectionList.getSelectedItem().toString();
                subjectValue=subjectList.getSelectedItem().toString();
                Bundle b=new Bundle();
                b.putString("section",sectionValue);
                b.putString("subject",subjectValue);
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                Fragment_Teacher_Take_Attendance f1=new Fragment_Teacher_Take_Attendance();
                    f1.setArguments(b);
                fragmentTransaction.replace(R.id.fragment_container,f1,"recycler");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                //Toast.makeText(getActivity(),"hello",Toast.LENGTH_LONG).show();
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
