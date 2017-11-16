package com.chen.sharebike.KotlinLearn

import android.widget.TextView

/**
 * Created by chen on 2017/11/8.
 */

class test_class(val tv:TextView){

    var name: String = "dotomato"
        get() = field.toUpperCase()



    fun setTv() {
        tv.text = "Hello!"
    }
}