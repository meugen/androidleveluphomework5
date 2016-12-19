package ua.meugen.android.levelup.homework5;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;


public final class CustomLayoutManager extends GridLayoutManager {

    public CustomLayoutManager(final Context context) {
        super(context, 2);
        setSpanSizeLookup(new SpanSizeLookupImpl());
    }
}

final class SpanSizeLookupImpl extends GridLayoutManager.SpanSizeLookup {

    @Override
    public int getSpanSize(final int position) {
        return position % 3 == 2 ? 2 : 1;
    }
}