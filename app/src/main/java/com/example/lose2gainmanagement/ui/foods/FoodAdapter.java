package com.example.lose2gainmanagement.ui.foods;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lose2gainmanagement.R;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> implements Filterable {

    private List<FoodItems> itemList;
    private List<FoodItems> itemListFiltered;
    private Context context;

    public FoodAdapter(List<FoodItems> itemList, Context context) {
        this.itemList = itemList;
        this.itemListFiltered = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fooditem,parent,false);
        return new FoodAdapter.FoodViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        final FoodItems item = itemListFiltered.get(position);

        holder.fName.setText(String.valueOf(item.getfName()));
        holder.fAmountQuantity.setText(item.getfAmount()+" "+item.getfQuantity());
        holder.fProteen.setText(String.valueOf(item.getfProten()));
        holder.fFat.setText(String.valueOf(item.getfFat()));
        holder.fCarb.setText(String.valueOf(item.getfCarb()));
        holder.fCallories.setText(String.valueOf(item.getfCallories()));
    }

    @Override
    public int getItemCount() {
        return itemListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    itemListFiltered = itemList;
                } else {
                    List<FoodItems> filteredList = new ArrayList<>();
                    for (FoodItems row : itemList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getfName().toLowerCase().contains(charString.toLowerCase())
                                || String.valueOf(row.getfId()).toLowerCase().contains(charString.toLowerCase()))
                        {
                            filteredList.add(row);
                        }
                    }

                    itemListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = itemListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                itemListFiltered = (ArrayList<FoodItems>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {
        private TextView fName,fAmountQuantity,fCallories,fFat,fCarb,fProteen;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            fName = itemView.findViewById(R.id.fName);
            fAmountQuantity = itemView.findViewById(R.id.fAmountQuantity);
            fCallories = itemView.findViewById(R.id.fCallory);
            fFat = itemView.findViewById(R.id.fFat);
            fCarb = itemView.findViewById(R.id.fCarb);
            fProteen = itemView.findViewById(R.id.fProten);

        }
    }

    public List<FoodItems> getItemList() {
        return itemList;
    }

    public void setItemList(List<FoodItems> itemList) {
        this.itemList = itemList;
        this.itemListFiltered = itemList;
        for(FoodItems i:itemList){
            Toast.makeText(context,""+i.getfId()+i.getfName(),Toast.LENGTH_SHORT).show();
        }
        notifyDataSetChanged();
    }
}
