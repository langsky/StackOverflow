package io.ican.hgl.stackoverflow.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import io.ican.hgl.stackoverflow.entity.question.Summary;

/**
 * Created by hgl on 16-12-3.
 */

public class SummaryNarrow extends RelativeLayout{

    private Summary summary;

    public SummaryNarrow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SummaryNarrow(Context context) {
        super(context);
    }


}
