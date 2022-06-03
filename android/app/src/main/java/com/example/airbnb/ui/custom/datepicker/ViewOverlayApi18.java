package com.example.airbnb.ui.custom.datepicker;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOverlay;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.android.material.internal.ViewOverlayImpl;

@SuppressLint("RestrictedApi")
@RequiresApi(18)
class ViewOverlayApi18 implements ViewOverlayImpl {

    private final ViewOverlay viewOverlay;

    ViewOverlayApi18(@NonNull View view) {
        viewOverlay = view.getOverlay();
    }

    @Override
    public void add(@NonNull Drawable drawable) {
        viewOverlay.add(drawable);
    }

    @Override
    public void remove(@NonNull Drawable drawable) {
        viewOverlay.remove(drawable);
    }
}
