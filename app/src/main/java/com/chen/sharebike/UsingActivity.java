package com.chen.sharebike;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.chen.sharebike.DataModel.MyCode;
import com.chen.sharebike.DataModel.MyCodeResult;
import com.chen.sharebike.Server.MyAction1;
import com.chen.sharebike.Server.Server;
import com.google.gson.Gson;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UsingActivity extends AppCompatActivity {

    final static int SCAN_CODE = 0;

    @BindView(R.id.timeView)
    public TextView mTimeView;

    private int mTimeCount;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_using);
        ButterKnife.bind(this);
        mTimeView.post(mTimer);
        mTimeCount = 0;
        mTimerEnable = true;
        Intent i = getIntent();
        id = i.getStringExtra("id");
    }

    private boolean mTimerEnable;
    private Runnable mTimer = new Runnable() {
        @Override
        public void run() {
            int t1 = mTimeCount % 60;
            int t2 = (mTimeCount / 60) % 60;
            int t3 = (mTimeCount / 3600) % 60;
            mTimeView.setText(String.format(Locale.CHINA, "%02d:%02d:%02d", t3, t2, t1));
            mTimeCount += 1;
            if (mTimerEnable)
                mTimeView.postDelayed(this, 1000);

            MyCode myCode = new MyCode();
            myCode.id = UsingActivity.this.id;
            myCode.type = "car";
            Server.getApi().is_return(myCode)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new MyAction1<MyCodeResult>() {
                        @Override
                        public void call() {
                            if (mVar.result.equals("return")) {
                                Intent i = new Intent(UsingActivity.this, StatementActivity.class);
                                i.putExtra("TimeCount", mTimeCount);
                                UsingActivity.this.startActivity(i);
                                UsingActivity.this.finish();
                            }
                        }
                    });
        }
    };

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
