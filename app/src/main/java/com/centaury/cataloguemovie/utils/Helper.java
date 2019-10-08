package com.centaury.cataloguemovie.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Centaury on 10/7/2019.
 */
public class Helper {

    public static class TopItemDecoration extends RecyclerView.ItemDecoration {
        private int spacing;

        public TopItemDecoration(int spacing) {
            this.spacing = spacing;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            if (position == 0) {
                outRect.top = spacing;
            }
        }
    }
}
