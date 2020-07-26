package com.example.selectcontact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterContactListing extends RecyclerView.Adapter<AdapterContactListing.MyHandler> {
    Context context;
    List<Contact> contactList;
    RemoveContactInterface removeContactInterface;
    public AdapterContactListing(Context context,List<Contact> contactList) {
        this.context=context;
        this.contactList=contactList;
    }

    @NonNull
    @Override
    public MyHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.selected_contact_row, parent, false);
        MyHandler myHandler = new MyHandler(view);
        return myHandler;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHandler holder, final int position) {
        holder.tvcontactlisting.setText(contactList.get(position).name);
        holder.tv_contactno.setText(contactList.get(position).number);
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeContactInterface=(SummaryActivity)context;
                removeContactInterface.removeContact(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }
    public class MyHandler extends RecyclerView.ViewHolder {
        TextView tvcontactlisting;
        TextView tv_contactno;
        ImageView cancel;
        public MyHandler(@NonNull View itemView) {
            super(itemView);
            tvcontactlisting=itemView.findViewById(R.id.tv_contactname);
            tv_contactno=itemView.findViewById(R.id.tv_contactno);
            cancel=itemView.findViewById(R.id.cancel);
        }
    }

    public interface RemoveContactInterface
    {
      public void removeContact(int position);
    }
}
