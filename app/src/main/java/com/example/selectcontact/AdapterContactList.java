package com.example.selectcontact;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.example.selectcontact.SelectContactActivityNew.allSelected;

public class AdapterContactList extends RecyclerView.Adapter<AdapterContactList.MyHandler> implements Filterable {

    public List<Contact> listModelCityLocality;
    public List<Contact> filteristModelCityLocality;
    public List<String> addNewContact = new ArrayList<>();
    List<MStoreCheckBoxState> storeCheckBoxState = new ArrayList<>();
    Context context;
    public String newCharString = "";
    public GetContectListInterface getContectListInterface;


    public AdapterContactList(List<Contact> listModelCityLocality, Context contex) {

        this.listModelCityLocality = listModelCityLocality;
        this.filteristModelCityLocality = listModelCityLocality;
        this.context = contex;
    }

    @NonNull
    @Override
    public MyHandler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.contact_row, viewGroup, false);
        MyHandler myHandler = new MyHandler(view);

        return myHandler;
    }


    @Override
    public void onBindViewHolder(@NonNull MyHandler viewHolder, final int i) {
        viewHolder.setIsRecyclable(false);
        Log.d("sxjsncj", String.valueOf(allSelected));
        viewHolder.tv_contactname.setText(filteristModelCityLocality.get(i).name);
        viewHolder.tv_contactno.setText(filteristModelCityLocality.get(i).number);

        if (allSelected == -1) {

//            filteristModelCityLocality.get(i).setChecked(false);
//            viewHolder.checkbox.setChecked(false);
            if (filteristModelCityLocality.get(i).isChecked()) {
                viewHolder.checkbox.setChecked(true);
            }
            else {
                viewHolder.checkbox.setChecked(false);
            }

        }
        // Executed when all Selected is clicked
        else if (allSelected == 1) {
            filteristModelCityLocality.get(i).setChecked(true);
            viewHolder.checkbox.setChecked(true);
        }
        // Executed when  UnSelect all is clicked
        else if (allSelected == 0) {
            filteristModelCityLocality.get(i).setChecked(false);
            viewHolder.checkbox.setChecked(false);
        }

//        else if (allSelected == 2) {
//            if (filteristModelCityLocality.get(i).isChecked()) {
//                viewHolder.checkbox.setChecked(true);
//            }
//            else {
//                viewHolder.checkbox.setChecked(false);
//            }
//        }

        viewHolder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                allSelected = -1;
                if (isChecked) {
                    Contact contact=filteristModelCityLocality.get(i);
                    contact.setChecked(true);
                    getContectListInterface = (SelectContactActivityNew) context;
                    getContectListInterface.getContectList(filteristModelCityLocality.get(i), true);
                } else {
                    filteristModelCityLocality.get(i).setChecked(false);
                    getContectListInterface = (SelectContactActivityNew) context;
                    getContectListInterface.getContectList(filteristModelCityLocality.get(i), false);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteristModelCityLocality.size();
    }

    public static class MyHandler extends RecyclerView.ViewHolder {

        TextView tv_contactno;
        CheckBox checkbox;
        TextView tv_contactname;

        public MyHandler(@NonNull View itemView) {
            super(itemView);
            tv_contactno = itemView.findViewById(R.id.tv_contactno);
            tv_contactname = itemView.findViewById(R.id.tv_contactname);
            checkbox = itemView.findViewById(R.id.checkbox);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
             //   newCharString = charString;
                if (charString.isEmpty()) {
                    filteristModelCityLocality = listModelCityLocality;
                } else {
                    List<Contact> filteredList = new ArrayList<>();
//                    for (Contact row : listModelCityLocality) {
//                        if (row.name.toLowerCase().contains(charString.toLowerCase())) {
//                            filteredList.add(row);
//                        }
                    for (int i=0;i<listModelCityLocality.size();i++) {
                        if (listModelCityLocality.get(i).name.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(listModelCityLocality.get(i));
                        }
                    }

                    filteristModelCityLocality = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteristModelCityLocality;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteristModelCityLocality = (ArrayList<Contact>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface GetContectListInterface {
        public void getContectList(Contact addNewContact, boolean visibility);

    }

}
