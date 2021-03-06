package com.askonthego.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.askonthego.response.Response;

public class SpacerComponent extends LinearLayout implements ISurveyComponent {

    public SpacerComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean acceptsResponse() {
        return false;
    }

    @Override
    public Response getResponse() {
        return new Response();
    }

    @Override
    public View getView() {
        return this;
    }
}
