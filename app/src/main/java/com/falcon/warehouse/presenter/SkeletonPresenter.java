package com.falcon.warehouse.presenter;

import androidx.lifecycle.LiveData;

import com.falcon.warehouse.contract.IWalkingSkeletonContract;
import com.falcon.warehouse.entity.Skeleton;

public class SkeletonPresenter implements IWalkingSkeletonContract.Presenter {

    private IWalkingSkeletonContract.View view;
    private IWalkingSkeletonContract.Model model;

    public SkeletonPresenter(IWalkingSkeletonContract.Model model) {
        this.model = model;
    }

    @Override
    public void setView(IWalkingSkeletonContract.View view) {
        this.view = view;
    }

    @Override
    public void addNewSkeleton() {
        model.createSkeleton(view.getName(), view.getAge());
    }

    @Override
    public void setSkeletonToTextView() {
        LiveData<Skeleton> skeletonLiveData = model.getSkeletonByName(view.getName());
        view.setSkeletonToTextView(skeletonLiveData);
        view.showMessage();
    }

}
