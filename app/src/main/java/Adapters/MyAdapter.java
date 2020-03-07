package Adapters;

import android.content.Context;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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

        ListItem listItem  = listItemsArrayList.get(position);
        holder.textViewtype.setText(listItem.getType());
        holder.textViewCode.setText(listItem.getCode());
        Linkify.addLinks(holder.textViewCode,Linkify.ALL);

    }

    @Override
    public int getItemCount() {
        return listItemsArrayList.size();
    }

    public class MyAdapterViewHolder extends RecyclerView.ViewHolder
    {
        TextView textViewCode,textViewtype;
        CardView cardView;
        public MyAdapterViewHolder(@NonNull View itemView) {

            super(itemView);
            textViewCode=(TextView)itemView.findViewById(R.id.textViewCode);
            textViewtype=(TextView)itemView.findViewById(R.id.textViewType);
            cardView = (CardView)itemView.findViewById(R.id.cardView);


        }

    }
}
