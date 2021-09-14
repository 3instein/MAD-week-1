package com.uc.user;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserRVAdapter extends RecyclerView.Adapter<UserRVAdapter.UserViewHolder>{

    private ArrayList<User> userList;

    public UserRVAdapter(ArrayList<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.card_user_id.setText(String.valueOf(position));
        holder.card_user_name.setText(userList.get(position).getFullName());
        holder.card_user_age.setText(userList.get(position).getAge());
        holder.card_user_address.setText(String.valueOf(userList.get(position).getAddress()));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView card_user_id, card_user_name, card_user_age, card_user_address;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            card_user_id = itemView.findViewById(R.id.card_user_id);
            card_user_name = itemView.findViewById(R.id.card_user_name);
            card_user_age = itemView.findViewById(R.id.card_user_age);
            card_user_address = itemView.findViewById(R.id.card_user_address);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetailUser.class);

                    intent.putExtra("id", card_user_id.getText());
                    intent.putExtra("name", card_user_name.getText());
                    intent.putExtra("age", card_user_age.getText());
                    intent.putExtra("address", card_user_address.getText());

                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
