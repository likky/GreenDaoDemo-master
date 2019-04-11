package com.meitu.lyz.greendaodemo.db;

import com.meitu.lyz.greendaodemo.entity.User;

import java.util.List;

/**
 * @author LYZ 2018.08.06
 */
public class UserDaoHelper {

    public static List<User> queryAll() {
        return DaoHelper.getUserDao().queryBuilder().list();
    }

    public synchronized static void insert(User user) {
        DaoHelper.getUserDao().insert(user);
    }

    public synchronized static void update(User user) {
        DaoHelper.getUserDao().update(user);
    }

    public synchronized static void delete(long id) {
        DaoHelper.getUserDao().deleteByKey(id);
    }

}
