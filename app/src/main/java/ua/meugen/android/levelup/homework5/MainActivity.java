package ua.meugen.android.levelup.homework5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class MainActivity extends AppCompatActivity {

    private static final Random RANDOM = new Random();
    private static final int COUNT = 100;

    private static final String ITEMS_KEY = "items";

    private CustomAdapter adapter;
    private List<String> items;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            this.items = genDumpData();
        } else {
            this.items = savedInstanceState.getStringArrayList(ITEMS_KEY);
        }

        this.recyclerView = (RecyclerView) findViewById(R.id.recycler);
        this.recyclerView.setLayoutManager(new CustomLayoutManager(this));
        setupAdapter();
    }

    private static List<String> genDumpData() {
        final int count = Math.max(10, RANDOM.nextInt(COUNT));
        final List<String> result = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            result.add(new BigInteger(20, RANDOM).toString(26));
        }
        return result;
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList(ITEMS_KEY, (ArrayList<String>) this.items);
    }

    private void setupAdapter() {
        if (this.adapter == null) {
            this.adapter = new CustomAdapter(this);
            this.recyclerView.setAdapter(this.adapter);
        }
        this.adapter.reload(this.items);
    }
}
