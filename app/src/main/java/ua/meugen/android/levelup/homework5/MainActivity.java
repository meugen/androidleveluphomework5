package ua.meugen.android.levelup.homework5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class MainActivity extends AppCompatActivity
        implements View.OnClickListener, CustomAdapter.OnSizeChangedListener {

    private static final Random RANDOM = new Random();
    private static final int COUNT = 100;

    private CustomAdapter adapter;

    private RecyclerView recyclerView;
    private EditText newValueView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.newValueView = (EditText) findViewById(R.id.new_value);
        findViewById(R.id.add).setOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        RecyclerSetupHelper.setup(recyclerView);

        if (savedInstanceState == null) {
            this.adapter = new CustomAdapter(this, genDumpData());
        } else {
            this.adapter = new CustomAdapter(this, savedInstanceState);
        }
        this.adapter.setListener(this);
        recyclerView.setAdapter(this.adapter);
    }

    private static List<String> genDumpData() {
        final int count = Math.max(10, RANDOM.nextInt(COUNT));
        final List<String> result = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            result.add(randomString());
        }
        return result;
    }

    private static String randomString() {
        return new BigInteger(20, RANDOM).toString(26);
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.saveState(outState);
    }

    @Override
    public void onClick(final View view) {
        final int viewId = view.getId();
        if (viewId == R.id.add) {
            add();
        }
    }

    private void add() {
        String newValue = newValueView.getText().toString().trim();
        if (newValue.length() == 0) {
            newValue = randomString();
        }
        final int position = this.adapter.add(newValue);
        this.recyclerView.scrollToPosition(position);
    }

    @Override
    public void onSizeChanged(final int count, final int checked) {
        setTitle("" + checked + " items checked of " + count);
    }
}
