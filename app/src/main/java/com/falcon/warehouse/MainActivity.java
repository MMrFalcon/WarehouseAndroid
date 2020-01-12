package com.falcon.warehouse;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.falcon.warehouse.root.App;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App) getApplication()).getComponent().inject(this);


        final MaterialButton offline = findViewById(R.id.offlineButton);
        final MaterialButton online = findViewById(R.id.onlineButton);

        offline.setOnClickListener(v -> startActivity(new Intent(this, SkeletonActivity.class)));

        online.setOnClickListener(v ->  startActivity(new Intent(this, LocalisationActivity.class)));

    }

}
