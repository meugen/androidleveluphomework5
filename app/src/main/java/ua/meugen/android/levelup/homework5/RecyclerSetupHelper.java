package ua.meugen.android.levelup.homework5;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;


public final class RecyclerSetupHelper {

    private RecyclerSetupHelper() {}

    public static void setup(final RecyclerView view) {
        final GridLayoutManager manager = new GridLayoutManager(
                view.getContext(), 2);
        manager.setSpanSizeLookup(new SpanSizeLookupImpl());
        view.setLayoutManager(manager);
        view.addItemDecoration(new SeparatorsDecoration());
    }
}

final class SpanSizeLookupImpl extends GridLayoutManager.SpanSizeLookup {

    @Override
    public int getSpanSize(final int position) {
        return position % 3 == 2 ? 2 : 1;
    }
}

final class SeparatorsDecoration extends RecyclerView.ItemDecoration {

    private final Paint paint;

    public SeparatorsDecoration() {
        this(Color.GRAY);
    }

    public SeparatorsDecoration(final int color) {
        this.paint = new Paint();
        this.paint.setColor(color);
    }

    @Override
    public void onDrawOver(final Canvas canvas, final RecyclerView parent,
                           final RecyclerView.State state) {
        super.onDrawOver(canvas, parent, state);

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);

            final int positionMod = parent.getChildAdapterPosition(child) % 3;
            if (positionMod == 0) {
                drawRight(canvas, child);
            }
            drawBottom(canvas, child);
        }
    }

    private void drawRight(final Canvas canvas, final View child) {
        final int leftAndRight = child.getRight();
        canvas.drawLine(leftAndRight, child.getTop(), leftAndRight,
                child.getBottom(), this.paint);
    }

    private void drawBottom(final Canvas canvas, final View child) {
        final int topAndBottom = child.getBottom();
        canvas.drawLine(child.getLeft(), topAndBottom, child.getRight(),
                topAndBottom, this.paint);
    }
}