package com.chen.sharebike;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chen.sharebike.DataModel.MyCode;
import com.chen.sharebike.DataModel.MyCodeResult;
import com.chen.sharebike.Server.MyAction1;
import com.chen.sharebike.Server.Server;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity{


    final static String TAG = "MainActivity";

    final static int SCAN_CODE = 0;

    @BindView(R.id.backImageView)
    public ImageView mBackImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Glide.with(this).load(R.drawable.back).into(mBackImageView);
        mBackImageView.setScaleX(1.2f);
        mBackImageView.setScaleY(1.2f);
    }

    @OnClick(R.id.scanButton)
    public void scanButtonClick(){
//        测试用
//        final MyCode myCode = new MyCode();
//        myCode.id = "123";
//        Server.getApi().borrowCar(myCode)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new MyAction1<MyCodeResult>() {
//                    @Override
//                    public void call() {
//                        if (mVar.result.equals("success")){
//                            Intent i = new Intent(MainActivity.this, UsingActivity.class);
//                            i.putExtra("id", myCode.id);
//                            MainActivity.this.startActivity(i);
//                        }
//                    }
//                });
//
//        Intent i = new Intent(MainActivity.this, UsingActivity.class);
//        i.putExtra("id", "123");
//        MainActivity.this.startActivity(i);

        Intent i = new Intent(this, ScanActivity.class);
        this.startActivityForResult(i, SCAN_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == SCAN_CODE){
            if (resultCode == RESULT_OK){
                String result = data.getStringExtra("code");
                Gson gson = new Gson();
                final MyCode myCode = gson.fromJson(result, MyCode.class);
                if (!myCode.type.equals("car"))
                    return;
                Server.getApi().borrowCar(myCode)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new MyAction1<MyCodeResult>() {
                            @Override
                            public void call() {
                                if (mVar.result.equals("success")){
                                    Intent i = new Intent(MainActivity.this, UsingActivity.class);
                                    i.putExtra("id", myCode.id);
                                    MainActivity.this.startActivity(i);
                                }
                            }
                        });
            }
        }
    }
}
