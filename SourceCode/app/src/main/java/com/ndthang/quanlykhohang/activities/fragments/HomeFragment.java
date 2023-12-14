package com.ndthang.quanlykhohang.activities.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ndthang.quanlykhohang.R;
import com.ndthang.quanlykhohang.activities.UpdateProductActivity;

public class HomeFragment extends Fragment {
    Button testbtn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        testbtn = view.findViewById(R.id.testbtn);

        testbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UpdateProductActivity.class);
                startActivity(intent);
            }
        });
        return view;

    }
}
