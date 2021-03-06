package com.programacion3.androidlatte.cafeteria_androidlatte.UI.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.programacion3.androidlatte.cafeteria_androidlatte.Controller.DBController;
import com.programacion3.androidlatte.cafeteria_androidlatte.Models.Item;
import com.programacion3.androidlatte.cafeteria_androidlatte.R;
import com.programacion3.androidlatte.cafeteria_androidlatte.UI.Adapters.ItemAdapter;

import java.util.LinkedList;
import java.util.List;

public class DisplayDesayunoActivity extends AppCompatActivity {

    private ListView listView;
    private List<Item> itemList;
    DBController dbController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_desayuno);

        List<Item> allItemList;
        listView = findViewById(R.id.lista1);
        itemList = new LinkedList<>();
        dbController = new DBController(this, "DBCafeteria.db", null, 1);

        allItemList = dbController.selectAllItems();
        for (Item item: allItemList) {
            if ((item.getCodeFood()/100) == 1) {
                itemList.add(item);
            }
        }

        ItemAdapter itemAdapter = new ItemAdapter(this, itemList);
        listView.setAdapter(itemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DisplayDesayunoActivity.this, SeleccionDeProductosActivity.class);
                Item item = (Item) adapterView.getItemAtPosition(i);
                intent.putExtra("itemSelected", item);
                startActivity(intent);
            }
        });

    }

}
