package com.meitu.lyz.greendaodemo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meitu.lyz.greendaodemo.db.UserDaoHelper;
import com.meitu.lyz.greendaodemo.entity.User;

import java.util.List;

/**
 * @author LYZ 2018.08.06
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private List<User> data;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(View.inflate(viewGroup.getContext(), R.layout.item_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder viewHolder, int i) {
        User user = data.get(i);
        viewHolder.mNameTv.setText(user.getName());
        viewHolder.mSexTv.setText(user.getSex() ? "男" : "女");
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDaoHelper.delete(data.get(viewHolder.getAdapterPosition()).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void setData(List<User> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mNameTv;
        TextView mSexTv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mNameTv = itemView.findViewById(R.id.name_tv);
            mSexTv = itemView.findViewById(R.id.sex_tv);
        }
    }
}
