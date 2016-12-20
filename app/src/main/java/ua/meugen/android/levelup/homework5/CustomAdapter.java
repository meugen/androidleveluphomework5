package ua.meugen.android.levelup.homework5;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import java.util.List;


public final class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.VH> {

    private final LayoutInflater inflater;
    private List<String> items;

    public CustomAdapter(final Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public void reload(final List<String> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public VH onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new VH(inflater.inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(final VH holder, final int position) {
        holder.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
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
    }
}
