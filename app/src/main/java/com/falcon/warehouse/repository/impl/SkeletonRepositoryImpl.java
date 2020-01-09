package com.falcon.warehouse.repository.impl;

import com.falcon.warehouse.entity.Skeleton;
import com.falcon.warehouse.repository.SkeletonRepository;

public class SkeletonRepositoryImpl implements SkeletonRepository {

    private Skeleton skeleton;

    @Override
    public Skeleton createSkeleton(String name, int age) {
        skeleton = new Skeleton(name, age);
        return skeleton;
    }

    @Override
    public Skeleton getSkeleton() {
        if (skeleton == null) {
            skeleton = new Skeleton("Sample Skeleton", 23);
        }

        return skeleton;
    }
}
