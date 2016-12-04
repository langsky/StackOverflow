package io.ican.hgl.stackoverflow.convert;

import android.databinding.BindingAdapter;
import android.view.View;

import io.ican.hgl.stackoverflow.R;
import io.ican.hgl.stackoverflow.entity.question.Summary;

/**
 * Created by hgl on 16-12-2.
 *
 */

public class DataBindingConvert {

    @BindingAdapter({"bind:state"})
    public static void stateToAnswer(View view, int state) {
        switch (state) {
            case Summary.ANSWERED:
                view.setBackgroundResource(R.drawable.answered);
                break;
            default:
            case Summary.UNANSWERED:
                view.setBackgroundResource(R.drawable.noanswered);
                break;
            case Summary.ANSWERED_ACCEPTED:
                view.setBackgroundResource(R.drawable.answered_accepted);
                break;
        }
    }
}
