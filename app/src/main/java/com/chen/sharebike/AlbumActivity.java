package com.chen.sharebike;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chen.sharebike.KotlinLearn.test_class;

import butterknife.BindView;
import butterknife.ButterKnife;



public class AlbumActivity extends AppCompatActivity {

    @BindView(R.id.root)
    public ViewGroup mRoot;

    @BindView(R.id.tv)
    public TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        ButterKnife.bind(this);
        test_class tc = new test_class(mTv);
        tc.setTv();
    }

}
