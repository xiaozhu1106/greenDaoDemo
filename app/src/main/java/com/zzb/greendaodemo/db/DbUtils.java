package com.zzb.greendaodemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.zzb.greendaodemo.BaseApplication;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by ZZB on 2017/6/28.
 */

public class DbUtils {
    private static DbUtils dbUtils;
    /**数据库名称*/
    private final static String dbName = "worklog.db";
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;

    private DbUtils() {
        context = BaseApplication.mContext;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
    }

    /**
     * 获取单例
     *
     * @return
     */
    public static DbUtils getInstance() {
        if (dbUtils == null) {
            synchronized (DbUtils.class) {
                if (dbUtils == null) {
                    dbUtils = new DbUtils();
                }
            }
        }
        return dbUtils;
    }

    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可写数据库
     */
    private SQLiteDatabase getWritableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getWritableDatabase();
        return db;
    }


    /**
     * 保存或替换
     * @param bean
     */
    public void saveData(WorkLogBean bean) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        WorkLogBeanDao chatListDao = daoSession.getWorkLogBeanDao();
        chatListDao.insertOrReplace(bean);
    }


    /**
     * 通过时间查找所有列表，按时间倒序
     */
    public List<WorkLogBean> queryAllData() {
        try {
            DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
            DaoSession daoSession = daoMaster.newSession();
            WorkLogBeanDao chatListDao = daoSession.getWorkLogBeanDao();
            QueryBuilder<WorkLogBean> qb = chatListDao.queryBuilder();
            //条件： id & （（notice & priid） | friend）
            qb.orderDesc(WorkLogBeanDao.Properties.TimeLong);
            List<WorkLogBean> chatLists = qb.list();
            return chatLists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 分页查询,每页10条数据,按时间降序
     * @param page   查询的页数，从0开始
     * @return
     */
    public List<WorkLogBean> queryPageLists(int page) {
        try {
            DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
            DaoSession daoSession = daoMaster.newSession();
            WorkLogBeanDao chatListDao = daoSession.getWorkLogBeanDao();
            QueryBuilder<WorkLogBean> qb = chatListDao.queryBuilder();
            //条件： id & （（notice & priid） | friend）
            qb.orderDesc(WorkLogBeanDao.Properties.TimeLong).offset(page * 10).limit(10);;
            List<WorkLogBean> chatLists = qb.list();
            return chatLists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取此时间对应的对象
     * @return
     */
    public List<WorkLogBean> queryDataFromTime(String timeStr) {
        try {
            DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
            DaoSession daoSession = daoMaster.newSession();
            WorkLogBeanDao chatListDao = daoSession.getWorkLogBeanDao();
            QueryBuilder<WorkLogBean> qb = chatListDao.queryBuilder();
            qb.where(WorkLogBeanDao.Properties.TimeStr.eq(timeStr));
            List<WorkLogBean> chatLists = qb.list();
            return chatLists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 将此时间的数据删除
     * @param timeStr
     */
    public void deleteDataFromTime(String timeStr) {
        try {
            DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
            DaoSession daoSession = daoMaster.newSession();
            WorkLogBeanDao chatListDao = daoSession.getWorkLogBeanDao();
            QueryBuilder<WorkLogBean> qb = chatListDao.queryBuilder();
            DeleteQuery<WorkLogBean> bd = qb.where(WorkLogBeanDao.Properties.TimeStr.eq(timeStr)).buildDelete();
            bd.executeDeleteWithoutDetachingEntities();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
