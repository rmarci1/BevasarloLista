package com.example.bevasarlolista;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    private EditText etProductName, etQuantity;
    private Button btnAddItem;
    private ListView shoppingListView;
    private ArrayList<ShoppingItem> shoppingList;
    private ShoppingListAdapter shoppingListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        btnAddItem.setOnClickListener(v -> {
            String productName = etProductName.getText().toString().trim();
            String quantityStr = etQuantity.getText().toString().trim();

            if (TextUtils.isEmpty(productName) || TextUtils.isEmpty(quantityStr)) {
                Toast.makeText(this, "Minden mezőt ki kell tölteni!", Toast.LENGTH_SHORT).show();
                return;
            }

            int quantity = Integer.parseInt(quantityStr);
            ShoppingItem item = new ShoppingItem(productName, quantity);

            shoppingList.add(item);
            shoppingListAdapter.notifyDataSetChanged();

            etProductName.setText("");
            etQuantity.setText("");
        });

        shoppingListView.setOnItemClickListener((parent, view, position, id) -> {
            ShoppingItem item = shoppingList.get(position);

            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            intent.putExtra("ITEM_NAME", item.getName());
            intent.putExtra("ITEM_QUANTITY", item.getQuantity());
            startActivity(intent);
        });

        shoppingListView.setOnItemLongClickListener((parent, view, position, id) -> {
            shoppingList.remove(position);
            shoppingListAdapter.notifyDataSetChanged();
            return true;
        });
    }
    public void init(){
        etProductName = findViewById(R.id.etProductName);
        etQuantity = findViewById(R.id.etQuantity);
        btnAddItem = findViewById(R.id.btnAddItem);
        shoppingListView = findViewById(R.id.shoppingListView);

        shoppingList = new ArrayList<>();
        shoppingListAdapter = new ShoppingListAdapter(this, shoppingList);

        shoppingListView.setAdapter(shoppingListAdapter);
    }

}