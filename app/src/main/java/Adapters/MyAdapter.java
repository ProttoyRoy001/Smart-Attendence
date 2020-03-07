package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartattendence.R;

import java.util.List;

import Model.ListItem;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyAdapterViewHolder>
{

    List<ListItem> listItemsArrayList;
    Context context;
    public MyAdapter (List<ListItem> listItemsArrayList, Context context)
    {
        this.listItemsArrayList = listItemsArrayList;
        this.context = context;
    }
    @NonNull
    @Override
    public MyAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout, parent, false);
        return new MyAdapterViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listItemsArrayList.size();
    }

    public class MyAdapterViewHolder extends RecyclerView.ViewHolder
    {
        public MyAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
        }

    }
}
