package com.falcon.warehouse.contract;

import androidx.lifecycle.LiveData;

import com.falcon.warehouse.entity.Skeleton;

public interface IWalkingSkeletonContract {

    interface Model {
        LiveData<Skeleton> createSkeleton(String name, int age);
        LiveData<Skeleton> getSkeletonByName(String skeletonName);
    }

    interface View {
        String getName();
        int getAge();
        void setSkeletonToTextView(LiveData<Skeleton> skeleton);
        void showMessage();
    }

    interface Presenter {
        void setView(View view);
        void addNewSkeleton();
        void setSkeletonToTextView();
    }
}
