package com.falcon.warehouse;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.falcon.warehouse.contract.IWalkingSkeletonContract;
import com.falcon.warehouse.entity.Skeleton;
import com.falcon.warehouse.root.App;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import javax.inject.Inject;

public class SkeletonActivity extends AppCompatActivity implements IWalkingSkeletonContract.View {

    @Inject
    IWalkingSkeletonContract.Presenter presenter;

    private MaterialTextView skeletonName;
    private MaterialTextView skeletonAge;
    private MaterialButton loadSkeleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skeleton);

        ((App) getApplication()).getComponent().inject(this);

        skeletonName = findViewById(R.id.skeletonName);
        skeletonAge = findViewById(R.id.skeletonAge);
        loadSkeleton = findViewById(R.id.loadSkeleton);

        loadSkeleton.setOnClickListener(v -> {
            presenter.addNewSkeleton();
            presenter.setSkeletonToTextView();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
    }

    @Override
    public String getName() {
        return "Walking Skeleton";
    }

    @Override
    public int getAge() {
        return 23;
    }

    @Override
    public void setSkeletonToTextView(LiveData<Skeleton> skeleton) {
        skeleton.observe(this, skeleton1 -> {
            skeletonName.setText(skeleton1.getName());
            skeletonAge.setText(String.valueOf(skeleton1.getAge()));
        });
    }

    @Override
    public void showMessage() {
        Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();
    }
}
