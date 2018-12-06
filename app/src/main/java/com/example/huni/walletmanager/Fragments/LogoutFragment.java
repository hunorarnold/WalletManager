package com.example.huni.walletmanager.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huni.walletmanager.R;
import com.example.huni.walletmanager.loginActivity;

public class LogoutFragment extends Fragment {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Intent intent;
    private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View iView = inflater.inflate(R.layout.fragment_logout, container, false);
        context = getContext();
        sharedPreferences = context.getSharedPreferences("innoshop", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        intent = new Intent(getActivity(),loginActivity.class);
        startActivity(intent);
        getActivity().finish();

        return iView;
    }


}
