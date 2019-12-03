package com.example.lose2gainmanagement.clients;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lose2gainmanagement.R;
import com.example.lose2gainmanagement.ui.form.clientDatabase.ClientEntity;
import com.example.lose2gainmanagement.ui.form.clientDatabase.ClientViewModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ClientViewHolder> implements Filterable {

    private List<ClientEntity> itemList;
    private List<ClientEntity> itemListFiltered;
    private Context context;
    private SwipeController swipeController;
    private ClientViewModel clientViewModel;
    private ClientActivity activity;

    public ClientAdapter(List<ClientEntity> itemList, Context context, SwipeController swipeController,ClientViewModel clientViewModel) {
        this.itemList = itemList;
        this.itemListFiltered = itemList;
        this.context = context;
        this.swipeController = swipeController;
        this.clientViewModel = clientViewModel;
        activity = (ClientActivity) context;

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

        holder.item_call.setOnClickListener(view -> {

            swipeController.clear();
            String s ="tel:"+ item.getPhone_no();
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse(s));
            context.startActivity(intent);

        });
        holder.item_delete.setOnClickListener(view -> {

            delete_alert(item);

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
        private CardView priority_card;
        private ImageButton item_call,item_delete;

        public ClientViewHolder(@NonNull View itemView) {
            super(itemView);
            client_img = itemView.findViewById(R.id.client_img);
            client_name = itemView.findViewById(R.id.client_name);
            client_sex = itemView.findViewById(R.id.client_sex);
            client_phone = itemView.findViewById(R.id.client_phone);
            client_age = itemView.findViewById(R.id.client_age);
            client_next_followup = itemView.findViewById(R.id.client_next_followup);
            client_last_followup = itemView.findViewById(R.id.client_last_followup);
            priority_card = itemView.findViewById(R.id.priority_card);
            LinearLayout item_main_layout = itemView.findViewById(R.id.item_main_layout);
            LinearLayout item_secondary_linear = itemView.findViewById(R.id.item_secondary_linear);
            item_call =  itemView.findViewById(R.id.item_call);
            item_delete = itemView.findViewById(R.id.item_delete);


            int width = Resources.getSystem().getDisplayMetrics().widthPixels;
            int extension = (int) (210 * Resources.getSystem().getDisplayMetrics().density);
            int sub = (int) (12* Resources.getSystem().getDisplayMetrics().density);
            //item_main_layout.setMinimumWidth(width + 50);

            ViewGroup.LayoutParams layoutParams1 = item_main_layout.getLayoutParams();
            layoutParams1.width = width+extension;
            item_main_layout.setLayoutParams(layoutParams1);


            ViewGroup.LayoutParams layoutParams2 = item_secondary_linear.getLayoutParams();
            layoutParams2.width = width-sub;
            item_secondary_linear.setLayoutParams(layoutParams2);
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

        notifyDataSetChanged();
    }

    @SuppressLint("RestrictedApi")
    private void delete_alert(ClientEntity client){
        new AlertDialog.Builder(context)
                .setTitle("Are you sure to Delete?")
                .setMessage("Deleting This Client Will Remove Every Information of This Client")
                .setPositiveButton("Delete", (dialogInterface, i) -> {
                    clientViewModel.delete_client(client);
                    notifyDataSetChanged();
                    activity.attach();
                })
                //set negative button
                .setNegativeButton("No", (dialogInterface, i) -> {


                })
                .show();
    }
}
