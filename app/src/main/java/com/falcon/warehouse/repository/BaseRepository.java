package com.falcon.warehouse.repository;

import java.util.Date;

public interface BaseRepository {
    Date getMaxRefreshTime(Date currentDate);
}
