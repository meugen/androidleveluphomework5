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
    private static final int COUNT = 20_000;

    private CustomAdapter adapter;

    private RecyclerView recyclerView;
    private EditText newValueView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.newValueView = (EditText) findViewById(R.id.new_value);
        findViewById(R.id.add).setOnClickListener(this);
        findViewById(R.id.remove_checked).setOnClickListener(this);

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
        final List<String> result = new ArrayList<>(COUNT);
        for (int i = 0; i < COUNT; i++) {
            result.add("" + i + ". " + randomString());
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
        } else if (viewId == R.id.remove_checked) {
            removeChecked();
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

    private void removeChecked() {
        this.adapter.removeChecked();
    }

    @Override
    public void onSizeChanged(final int count, final int checked) {
        setTitle("" + checked + " items checked of " + count);
    }
}
