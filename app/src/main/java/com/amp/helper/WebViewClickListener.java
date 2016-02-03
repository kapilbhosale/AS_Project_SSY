package com.amp.helper;


import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ListView;

/**
 * Created by amolpatil on 23/07/15.
 */
public class WebViewClickListener implements View.OnTouchListener
{
    private int position;
    private ViewGroup vg;
    private WebView wv;

    public WebViewClickListener(WebView wv, ViewGroup vg, int position) {
        this.vg = vg;
        this.position = position;
        this.wv = wv;
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_CANCEL:
                return true;
            case MotionEvent.ACTION_UP:
                sendClick();
                return true;
        }

        return false;
    }

    public void sendClick() {
        ListView lv = (ListView) vg;
        lv.performItemClick(wv, position, 0);
    }

}
