package com.falcon.warehouse.service;

import com.falcon.warehouse.entity.Skeleton;

public interface IWalkingSkeleton {

    interface Model {
        Skeleton createSkeleton(String name, int age);
        Skeleton getSkeleton();
    }

    interface View {
        String getName();
        int getAge();
        void setSkeletonToTextView(Skeleton skeleton);
        void showMessage();
    }

    interface Presenter {
        void setView(View view);
        void addNewSkeleton();
        void setSkeletonToTextView();
    }
}
