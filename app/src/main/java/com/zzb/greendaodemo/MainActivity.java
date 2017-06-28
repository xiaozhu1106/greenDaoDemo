package com.zzb.greendaodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zzb.greendaodemo.db.DbUtils;
import com.zzb.greendaodemo.db.WorkLogBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.et_time)
    EditText etTime;
    @BindView(R.id.et_description)
    EditText etDescription;
    @BindView(R.id.et_query_time)
    EditText etQueryTime;
    @BindView(R.id.tv_show_content)
    TextView tvShowContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_save, R.id.btn_get, R.id.btn_get_all})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save: //保存数据
                String time = etTime.getText().toString().trim();
                long timeLong = parseStrToLong(time);
                String description = etDescription.getText().toString().trim();
                WorkLogBean workLogBean = new WorkLogBean();
                workLogBean.setTimeStr(time);
                workLogBean.setTimeLong(timeLong);
                workLogBean.setDescription(description);
                DbUtils.getInstance().saveData(workLogBean);
                break;
            case R.id.btn_get: //查询指定时间数据
                String quertTime = etQueryTime.getText().toString().toString();
                List<WorkLogBean> datas = DbUtils.getInstance().queryDataFromTime(quertTime);
                tvShowContent.setText(datas == null ? "查询数据为空" : datas.toString());
                break;
            case R.id.btn_get_all: //查询所有数据
                List<WorkLogBean> list = DbUtils.getInstance().queryAllData();
                tvShowContent.setText(list == null ? "查询数据为空" : list.toString() );
                break;
        }
    }


    /**
     * @param timeStr
     * @return
     */
    private long parseStrToLong(String timeStr) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return simpleDateFormat.parse(timeStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }


    private String parseLongToStr(Long timeLong) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return simpleDateFormat.format(new Date(timeLong));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
