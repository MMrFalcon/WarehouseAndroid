package com.falcon.warehouse.model;

import androidx.lifecycle.LiveData;

import com.falcon.warehouse.contract.IWalkingSkeletonContract;
import com.falcon.warehouse.entity.Skeleton;
import com.falcon.warehouse.repository.SkeletonRepository;

public class SkeletonModel implements IWalkingSkeletonContract.Model {

    private final SkeletonRepository skeletonRepository;

    public SkeletonModel(SkeletonRepository skeletonRepository) {
        this.skeletonRepository = skeletonRepository;
    }

    @Override
    public LiveData<Skeleton> createSkeleton(String name, int age) {
        Skeleton skeleton = new Skeleton(name, age);
        skeletonRepository.insertSkeleton(skeleton);
        return skeletonRepository.findSkeletonByName(name);
    }

    @Override
    public LiveData<Skeleton> getSkeletonByName(String skeletonName) {
        return skeletonRepository.findSkeletonByName(skeletonName);
    }
}
