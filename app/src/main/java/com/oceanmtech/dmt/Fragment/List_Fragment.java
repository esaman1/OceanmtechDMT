package com.oceanmtech.dmt.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.oceanmtech.dmt.R;



public class List_Fragment extends Fragment {


    List_Fragment list_fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        list_fragment=new List_Fragment();


        return view;
    }
}