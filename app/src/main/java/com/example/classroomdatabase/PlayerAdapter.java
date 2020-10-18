package com.example.classroomdatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.MyViewHolder>{
    private List<Player> playerList;


    public void getPlayerList(List<Player> playerList){
        this.playerList= playerList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.singleCode.setText("Code: "+playerList.get(position).getCode());
        holder.singleName.setText("Name: "+playerList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView singleName, singleCode;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            singleName= itemView.findViewById(R.id.singleNameId);
            singleCode= itemView.findViewById(R.id.singleCodeId);
        }
    }
}
