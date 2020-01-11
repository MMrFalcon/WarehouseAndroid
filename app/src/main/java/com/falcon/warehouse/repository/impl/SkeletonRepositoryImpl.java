package com.falcon.warehouse.repository.impl;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.falcon.warehouse.dao.SkeletonDao;
import com.falcon.warehouse.entity.Skeleton;
import com.falcon.warehouse.repository.SkeletonRepository;

import javax.inject.Inject;

public class SkeletonRepositoryImpl implements SkeletonRepository {

    private final SkeletonDao skeletonDao;

    @Inject
    public SkeletonRepositoryImpl(SkeletonDao skeletonDao) {
        this.skeletonDao = skeletonDao;
    }


    @Override
    public void insertSkeleton(Skeleton skeleton) {
        new InsertSkeletonTask(skeletonDao).execute(skeleton);
    }

    @Override
    public LiveData<Skeleton> findSkeletonByName(String skeletonName) {
        return skeletonDao.findSkeletonByName(skeletonName);
    }

    private static class InsertSkeletonTask extends AsyncTask<Skeleton, Void, Void> {
        private SkeletonDao skeletonDao;

        public InsertSkeletonTask(SkeletonDao skeletonDao) {
            this.skeletonDao = skeletonDao;
        }

        @Override
        protected Void doInBackground(Skeleton... skeletons) {
            skeletonDao.insertSkeleton(skeletons[0]);
            return null;
        }
    }
}
