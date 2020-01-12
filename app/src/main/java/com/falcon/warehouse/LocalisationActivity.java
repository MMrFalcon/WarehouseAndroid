package com.falcon.warehouse;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.falcon.warehouse.contract.ILocalisationContract;
import com.falcon.warehouse.entity.Localisation;
import com.falcon.warehouse.root.App;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import javax.inject.Inject;

public class LocalisationActivity extends AppCompatActivity implements ILocalisationContract.View {

    @Inject
    ILocalisationContract.Presenter presenter;

    private MaterialTextView localisationId;
    private MaterialTextView localisationName;
    private MaterialTextView localisationIndex;
    private MaterialButton showLocalisationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localisation);

        ((App) getApplication()).getComponent().inject(this);

        localisationId = findViewById(R.id.localisationId);
        localisationName = findViewById(R.id.localisationName);
        localisationIndex = findViewById(R.id.localisationIndex);
        showLocalisationButton = findViewById(R.id.localisationButton);

        showLocalisationButton.setOnClickListener(v -> presenter.setLocalisationToTextView());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView(this);
    }

    @Override
    public void setLocalisation(LiveData<Localisation> localisation) {
        Log.i("VIEW_SET_LOC", "Setting live data in localisaton");
        localisation.observe(this, localisation1 -> {
            if (localisation1 != null) {
                setLocalisationId(String.valueOf(localisation1.getId()));
                setLocalisationIndex(localisation1.getLocalisationIndex());
                setLocalisationName(localisation1.getLocalisationName());
            }
        });
    }

    @Override
    public void setLocalisationId(String localisationId) {
        this.localisationId.setText(localisationId);
    }

    @Override
    public void setLocalisationIndex(String localisationIndex) {
        this.localisationIndex.setText(localisationIndex);
    }

    @Override
    public void setLocalisationName(String localisationName) {
        this.localisationName.setText(localisationName);
    }
}
