package com.zzb.greendaodemo.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ZZB on 2017/6/28.
 */

@Entity
public class WorkLogBean {
    @Id(autoincrement = true)
    @Unique
    private Long id;       //主键自增长，不可重复,作为不同记录对象的标识，传入参数对象时不要传入

    //时间戳
    @Property(nameInDb = "TIMELONG")
    private Long timeLong;

    //时间对应格式字符串
    @Property(nameInDb = "TIMESTR")
    private String timeStr;

    //内容描述
    @Property(nameInDb = "DESCRIPTION")
    private String description;

    @Generated(hash = 5215746)
    public WorkLogBean(Long id, Long timeLong, String timeStr, String description) {
        this.id = id;
        this.timeLong = timeLong;
        this.timeStr = timeStr;
        this.description = description;
    }

    @Generated(hash = 1301884914)
    public WorkLogBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTimeLong() {
        return this.timeLong;
    }

    public void setTimeLong(Long timeLong) {
        this.timeLong = timeLong;
    }

    public String getTimeStr() {
        return this.timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "WorkLogBean{" +
                "id=" + id +
                ", timeLong=" + timeLong +
                ", timeStr='" + timeStr + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
