package com.falcon.warehouse.repository.impl;

import com.falcon.warehouse.repository.BaseRepository;
import com.falcon.warehouse.root.Constants;

import java.util.Calendar;
import java.util.Date;

public abstract class BaseRepositoryImpl implements BaseRepository {

    public Date getMaxRefreshTime(Date currentDate){
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.MINUTE, -Constants.REFRESH_TIME_IN_MINUTES);
        return cal.getTime();
    }
}
