package com.developers.oraclehr;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by Nickman on 27/04/2017.
 */

public class MyEditText extends android.support.v7.widget.AppCompatEditText{
    public MyEditText(Context context) {
        super(context);
        init();
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    public void init(){
        setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/VeraSe.ttf"),1);
    }
}
