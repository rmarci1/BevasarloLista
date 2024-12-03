package com.example.bevasarlolista;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetailsActivity extends AppCompatActivity {

    private TextView tvItemName, tvItemQuantity;
    private Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        String name = getIntent().getStringExtra("ITEM_NAME");
        int quantity = getIntent().getIntExtra("ITEM_QUANTITY", 0);

        tvItemName.setText("Termék: " + name);
        tvItemQuantity.setText("Mennyiség: " + quantity);

        btnBack.setOnClickListener(v -> finish());

    }
    public void init(){
        tvItemName = findViewById(R.id.tvItemName);
        tvItemQuantity = findViewById(R.id.tvItemQuantity);
        btnBack = findViewById(R.id.btnBack);
    }
}