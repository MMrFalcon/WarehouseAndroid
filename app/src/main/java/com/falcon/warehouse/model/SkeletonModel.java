package com.falcon.warehouse.model;

import com.falcon.warehouse.entity.Skeleton;
import com.falcon.warehouse.repository.SkeletonRepository;
import com.falcon.warehouse.service.IWalkingSkeleton;

public class SkeletonModel implements IWalkingSkeleton.Model {

    private SkeletonRepository skeletonRepository;

    public SkeletonModel(SkeletonRepository skeletonRepository) {
        this.skeletonRepository = skeletonRepository;
    }

    @Override
    public Skeleton createSkeleton(String name, int age) {
        return skeletonRepository.createSkeleton(name, age);
    }

    @Override
    public Skeleton getSkeleton() {
        return skeletonRepository.getSkeleton();
    }
}
