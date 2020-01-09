package com.falcon.warehouse.repository;

import com.falcon.warehouse.entity.Skeleton;

public interface SkeletonRepository {

    Skeleton createSkeleton(String name, int age);

    Skeleton getSkeleton();

}
