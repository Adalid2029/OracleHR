package com.developers.oraclehr;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by Nickman on 27/04/2017.
 */

public class MyButton extends android.support.v7.widget.AppCompatButton{
    public MyButton(Context context) {
        super(context);
        init();
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/VeraSe.ttf"),1);
    }
}
