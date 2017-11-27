package com.chen.sharebike.Server;

import com.chen.sharebike.DataModel.GetRestResult;
import com.chen.sharebike.DataModel.MyCode;
import com.chen.sharebike.DataModel.MyCodeResult;
import com.chen.sharebike.DataModel.GetRest;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by chen on 17-11-2.
 * Copyright *
 */

public class Server {


    final static private String TAG = "Server";

    //    final static private String BASEURL = "www.dotomato.win";
    final static private String BASEURL = "http://dotomato.top:5001"; //这里要填上树莓派的地址
    final static private String VERSION = "/api/v0.01";

    public interface ServerInterface {

        @Headers({"Content-Type: application/json", "Accept: application/json"})
        @POST(VERSION + "/borrow_car")
        Observable<MyCodeResult> borrowCar(@Body MyCode var);

        @Headers({"Content-Type: application/json", "Accept: application/json"})
        @POST(VERSION + "/return_car")
        Observable<MyCodeResult> returnCar(@Body MyCode var);

        @Headers({"Content-Type: application/json", "Accept: application/json"})
        @POST(VERSION + "/is_return")
        Observable<MyCodeResult> is_return(@Body MyCode var);

    }


    private static ServerInterface mServer=null;

    public static ServerInterface getApi(){
        if (mServer==null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();

            mServer = retrofit.create(ServerInterface.class);
        }
        return mServer;
    }
}
