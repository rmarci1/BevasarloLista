package com.example.bevasarlolista;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ShoppingListAdapter extends ArrayAdapter<ShoppingItem> {
    private Context context;
    private List<ShoppingItem> shoppingList;

    public ShoppingListAdapter(Context context, List<ShoppingItem> shoppingList) {
        super(context, R.layout.shopping_item, shoppingList);
        this.context = context;
        this.shoppingList = shoppingList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.shopping_item, parent, false);
        }

        ShoppingItem item = shoppingList.get(position);

        TextView tvProductName = convertView.findViewById(R.id.tvProductName);
        TextView tvProductQuantity = convertView.findViewById(R.id.tvProductQuantity);

        tvProductName.setText(item.getName());
        tvProductQuantity.setText(String.valueOf(item.getQuantity()));

        return convertView;
    }
}

