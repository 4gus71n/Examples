package com.kimboo.core.activities

import android.content.Context
import android.content.Intent

class ActivityBuilder {
    companion object {
        fun getExample1Intent(context: Context) : Intent {
            return Intent(context, Class.forName("com.kimboo.example1.ui.examplezip1.ExampleZip1Activity"))
        }
        fun getExample2Intent(context: Context) : Intent {
            return Intent(context, Class.forName("com.kimboo.example1.ui.examplezip2.ExampleZip2Activity"))
        }
        fun getExampleCache1Intent(context: Context) : Intent {
            return Intent(context, Class.forName("com.kimboo.example2.ui.examplecache1.ExampleCache1Activity"))
        }
    }
}