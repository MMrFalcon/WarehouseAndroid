package com.falcon.warehouse.presenter;

import com.falcon.warehouse.entity.Skeleton;
import com.falcon.warehouse.service.IWalkingSkeleton;

public class SkeletonPresenter implements IWalkingSkeleton.Presenter {

    private IWalkingSkeleton.View view;
    private IWalkingSkeleton.Model model;

    public SkeletonPresenter(IWalkingSkeleton.Model model) {
        this.model = model;
    }

    @Override
    public void setView(IWalkingSkeleton.View view) {
        this.view = view;
    }

    @Override
    public void addNewSkeleton() {
        model.createSkeleton(view.getName(), view.getAge());
    }

    @Override
    public void setSkeletonToTextView() {
        Skeleton skeleton = model.getSkeleton();
        view.setSkeletonToTextView(skeleton);
        view.showMessage();
    }
}
