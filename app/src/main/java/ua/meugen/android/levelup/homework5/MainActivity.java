package ua.meugen.android.levelup.homework5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class MainActivity extends AppCompatActivity {

    private static final Random RANDOM = new Random();
    private static final int COUNT = 100;

    private CustomAdapter adapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        RecyclerSetupHelper.setup(recyclerView);

        if (savedInstanceState == null) {
            this.adapter = new CustomAdapter(this, genDumpData());
        } else {
            this.adapter = new CustomAdapter(this, savedInstanceState);
        }
        recyclerView.setAdapter(this.adapter);
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
        this.adapter.saveState(outState);
    }
}
