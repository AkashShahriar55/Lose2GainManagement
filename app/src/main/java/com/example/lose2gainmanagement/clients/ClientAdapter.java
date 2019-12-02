package com.example.lose2gainmanagement.clients;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lose2gainmanagement.R;
import com.example.lose2gainmanagement.ui.form.clientDatabase.ClientEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ClientViewHolder> implements Filterable {

    private List<ClientEntity> itemList;
    private List<ClientEntity> itemListFiltered;
    private Context context;

    public ClientAdapter(List<ClientEntity> itemList, Context context) {
        this.itemList = itemList;
        this.itemListFiltered = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.client_list,parent,false);
        return new ClientAdapter.ClientViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientViewHolder holder, int position) {

        final ClientEntity item = itemListFiltered.get(position);
        Bitmap bitmap = loadImageFromStorage(item.getClient_image_directory(),item.getClient_image());
        holder.client_img.setImageBitmap(bitmap);
        holder.client_name.setText(String.valueOf(item.getName()));
        holder.client_sex.setText(String.valueOf(item.getSex()));
        holder.client_phone.setText(String.valueOf(item.getPhone_no()));
        holder.client_age.setText(String.valueOf(item.getAge()));
        holder.client_next_followup.setText(String.valueOf(item.getNext_followup()));
        holder.client_last_followup.setText(String.valueOf(item.getLast_followup()));

        String p = String.valueOf(item.getPriority());
        if (p.equals("1")){
            holder.priority_card.setCardBackgroundColor(context.getColor(R.color.priority_yellow));
        }
        else if (p.equals("2")){
            holder.priority_card.setCardBackgroundColor(context.getColor(R.color.priority_green));
        }
        else {
            if (p.equals("1")){
                holder.priority_card.setCardBackgroundColor(context.getColor(R.color.priority_red));
            }
        }

        holder.itemView.setOnClickListener(view -> {
            //Toast.makeText(context,Integer.toString(position),Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context,ClientDetailsActivity.class);
            Bundle extras=new Bundle();
            extras.putSerializable("client", (Serializable) item);
            intent.putExtras(extras);
            context.startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return itemListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public class ClientViewHolder extends RecyclerView.ViewHolder{
        private ImageView client_img;
        private TextView client_name, client_sex,client_phone,client_age, client_next_followup, client_last_followup;
        CardView priority_card;

        public ClientViewHolder(@NonNull View itemView) {
            super(itemView);
            client_img = itemView.findViewById(R.id.client_img);
            client_name = itemView.findViewById(R.id.client_name);
            client_sex = itemView.findViewById(R.id.client_sex);
            client_phone = itemView.findViewById(R.id.client_phone);
            client_age = itemView.findViewById(R.id.client_age);
            client_next_followup = itemView.findViewById(R.id.client_next_followup);
            client_last_followup = itemView.findViewById(R.id.client_last_followup);
            priority_card =itemView.findViewById(R.id.priority_card);
        }
    }


    private Bitmap loadImageFromStorage(String path, String img_name)
    {
        Bitmap b = null;
        try {
            File f=new File(path, img_name);
            b= BitmapFactory.decodeStream(new FileInputStream(f));
            //ImageView img=(ImageView)findViewById(R.id.imgPicker);
            //img.setImageBitmap(b);

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return b;

    }


    public void setItemList(List<ClientEntity> itemList) {
        this.itemList = itemList;
        this.itemListFiltered = itemList;
       /* for(FoodItems i:itemList){
            Toast.makeText(context,""+i.getfId()+i.getfName(),Toast.LENGTH_SHORT).show();
        }*/
        notifyDataSetChanged();
    }
}
