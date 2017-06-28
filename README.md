# greenDaoDemo
这是一个GreenDao3.2.0使用Demo，并附有详细教程

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


如果想深入的学习，可以看我的另一篇文章，在真实项目中的实际应用：
http://www.jianshu.com/p/b1b2d3333fca



