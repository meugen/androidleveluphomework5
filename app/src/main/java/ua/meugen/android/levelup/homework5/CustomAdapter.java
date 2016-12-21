package ua.meugen.android.levelup.homework5;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public final class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.VH>
        implements View.OnClickListener {

    private static final String CHECKED_POSITIONS_KEY = "checkedPositions";
    private static final String ITEMS_KEY = "items";

    private final LayoutInflater inflater;
    private Set<Integer> checkedPositions;
    private List<String> items;

    private OnSizeChangedListener listener;

    private CustomAdapter(@NonNull final Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public CustomAdapter(@NonNull final Context context, @NonNull final Bundle state) {
        this(context);
        this.checkedPositions = new HashSet<>(state.getIntegerArrayList(
                CHECKED_POSITIONS_KEY));
        this.items = state.getStringArrayList(ITEMS_KEY);
    }

    public CustomAdapter(@NonNull final Context context, @NonNull final List<String> items) {
        this(context);
        this.checkedPositions = new HashSet<>();
        this.items = items;
    }

    public void saveState(final Bundle outState) {
        outState.putStringArrayList(ITEMS_KEY, new ArrayList<>(this.items));
        outState.putIntegerArrayList(CHECKED_POSITIONS_KEY, new ArrayList<>(
                this.checkedPositions));
    }

    public OnSizeChangedListener getListener() {
        return listener;
    }

    public void setListener(final OnSizeChangedListener listener) {
        this.listener = listener;
        callListener();
    }

    @Override
    public VH onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new VH(inflater.inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(final VH holder, final int position) {
        holder.setText(items.get(position));
        holder.setChecked(checkedPositions
                .contains(position));
        holder.setListener(this, position);
    }

    @Override
    public void onClick(final View view) {
        final Integer position = (Integer) view.getTag();
        if (this.checkedPositions.contains(position)) {
            this.checkedPositions.remove(position);
        } else {
            this.checkedPositions.add(position);
        }
        notifyItemChanged(position);

        callListener();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public int add(final String newValue) {
        this.items.add(newValue);
        final int position = this.items.size() - 1;
        notifyItemRangeInserted(position, 1);

        callListener();
        return position;
    }

    private void callListener() {
        if (this.listener != null) {
            this.listener.onSizeChanged(this.items.size(), this.checkedPositions.size());
        }
    }

    public static final class VH extends RecyclerView.ViewHolder {

        private final CheckedTextView checkedTextView;

        public VH(final View itemView) {
            super(itemView);
            checkedTextView = (CheckedTextView) itemView.findViewById(R.id.text);
        }

        public void setText(final CharSequence text) {
            checkedTextView.setText(text);
        }

        public void setChecked(final boolean checked) {
            checkedTextView.setChecked(checked);
        }

        public void setListener(final View.OnClickListener listener, final Object tag) {
            checkedTextView.setOnClickListener(listener);
            checkedTextView.setTag(tag);
        }
    }

    public interface OnSizeChangedListener {

        void onSizeChanged(int count, int checked);
    }
}
