package com.falcon.warehouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final MaterialButton offline = findViewById(R.id.offlineButton);
        final MaterialButton online = findViewById(R.id.onlineButton);

        offline.setOnClickListener(v -> Toast.makeText(this, "OFFLINE", Toast.LENGTH_LONG).show());

        online.setOnClickListener(v -> Toast.makeText(this, "ONLINE", Toast.LENGTH_LONG).show());

    }

}
