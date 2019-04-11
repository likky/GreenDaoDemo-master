package com.meitu.lyz.greendaodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.meitu.lyz.greendaodemo.dao.UserDao;
import com.meitu.lyz.greendaodemo.db.UserDaoHelper;
import com.meitu.lyz.greendaodemo.entity.User;

import java.util.List;

import static com.meitu.lyz.greendaodemo.db.DaoHelper.getUserDao;

public class MainActivity extends AppCompatActivity {

    private Button mRefreshBtn;
    private Button mAddBtn;
    private Button mDeleteBtn;
    private RecyclerView mDataRv;

    private UserAdapter mUserAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mRefreshBtn = findViewById(R.id.refresh_btn);
        mAddBtn = findViewById(R.id.add_btn);
        mDeleteBtn = findViewById(R.id.delete_btn);
        mDataRv = findViewById(R.id.data_rv);

        mDataRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mUserAdapter = new UserAdapter();
        mDataRv.setAdapter(mUserAdapter);
        mUserAdapter.setData(UserDaoHelper.queryAll());


        mRefreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUserAdapter.setData(UserDaoHelper.queryAll());
            }
        });

        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                user.setName(System.currentTimeMillis() + "");
                user.setSex(System.currentTimeMillis() % 2 == 0);
                UserDaoHelper.insert(user);
                mRefreshBtn.performClick();
            }
        });

        mDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //id是1,2,3,4，当删除4后添加新的id是5，因此下面删除代码有问题。
                //下面代码删除所有女性数据
                List<User> userList= getUserDao().queryBuilder().where(UserDao.Properties.Sex.eq("false")).build().list();
                for (User user : userList) {
                    getUserDao().delete(user);
                }
                //只有一条数据，使用下面代码删除。
                //User user= getUserDao().queryBuilder().where(UserDao.Properties.Sex.eq("false")).build().unique();
                //if(user!=null) {
                    //getUserDao().delete(user);
                    //或者这样删除
                    //UserDaoHelper.delete(user.getId());
                //}
                mRefreshBtn.performClick();
            }
        });
    }
}
