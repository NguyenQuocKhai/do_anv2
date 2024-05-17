package com.example.navigationdrawertesting.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.navigationdrawertesting.R;
import com.example.navigationdrawertesting.databinding.FragmentHomeBinding;

public class Home extends Fragment {


    //View Binding is a feature that allows you to more easily write code that interacts with views by providing
    // compile-time safety and removing boilerplate code.
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }


}