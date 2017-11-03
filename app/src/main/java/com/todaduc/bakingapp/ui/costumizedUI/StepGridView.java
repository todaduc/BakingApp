package com.todaduc.bakingapp.ui.costumizedUI;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.GridView;

import static java.lang.Integer.MAX_VALUE;

/**
 * Created by ddjankou on 9/21/2017.
 */

public class StepGridView extends GridView {
    public StepGridView(Context context) {
        super(context);
    }

    public StepGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StepGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean isExpanded()
    {
        return expanded;
    }

    boolean expanded = false;
    @SuppressLint("Range")
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        // HACK! TAKE THAT ANDROID!
       /* if (isExpanded())
        {
            // Calculate entire height by providing a very large height hint.
            // View.MEASURED_SIZE_MASK represents the largest height possible.
            int expandSpec = MeasureSpec.makeMeasureSpec(MEASURED_SIZE_MASK,
                    MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);

            ViewGroup.LayoutParams params = getLayoutParams();
            params.height = getMeasuredHeight();
        }
        else
        {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }*/
        int heightSpec;

        if (getLayoutParams().height == LayoutParams.WRAP_CONTENT) {

            heightSpec = MeasureSpec.makeMeasureSpec(
                    Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        }
        else {
            // Any other height should be respected as is.
            heightSpec = heightMeasureSpec;
        }

        super.onMeasure(widthMeasureSpec, heightSpec);
    }

    public void setExpanded(boolean expanded)
    {
        this.expanded = expanded;
    }
}
