package com.chen.sharebike;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.chen.sharebike.CustomView.OutlineProvider;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumActivity extends AppCompatActivity {

    @BindView(R.id.root)
    public ViewGroup mRoot;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        ButterKnife.bind(this);

//        imageView = new ImageView(this);
//        Drawable drawableColor = new ColorDrawable(Color.rgb(255, 0, 0));
////        imageView.setColorFilter(Color.rgb(255,0,0));
//        imageView.setLayoutParams(new FrameLayout.LayoutParams(2000, 40));
//
//        imageView.setImageDrawable(drawableColor);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            imageView.setElevation(30f);
//        }
//        mRoot.addView(imageView);
//
//        imageView.postDelayed(aniRunnable, 33);
//        imageView.post(new Runnable() {
//            @Override
//            public void run() {
//
//                imageView.setTop(100);
//            }
//        });
//        OutlineProvider.setOutline(imageView, OutlineProvider.SHAPE_RECT);
    }

    Runnable aniRunnable = new Runnable() {
        @Override
        public void run() {
            imageView.setLeft(imageView.getLeft() + 10);
            imageView.postDelayed(this, 33);
        }
    };
}
