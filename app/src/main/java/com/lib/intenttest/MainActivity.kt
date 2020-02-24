package com.lib.intenttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.createBalloon
import com.skydoves.balloon.showAlignBottom
import com.skydoves.balloon.showAlignTop
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {

    private lateinit var str : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        str = ""

        text.setOnClickListener {

            //calling api with Coroutines
            //Dispatcher.IO indicates it is being called in background and Io indicated input/output
            //operation either from REST api or from a local database like RoomDB or Realm DB
            CoroutineScope(Dispatchers.IO).launch {
                var res = RetrofitFactory.makeRetrofitService().getREsp()

                //Dispatchers.Main indicates it is being transferred to Main thread
                withContext(Dispatchers.Main) {
                    var r = res.body() as Resp
                    str = r.status.toString()


                    /*
                    * Update your Ui component after parsing the response
                    */

                    // This a library for showing messages using custom tooltips
                    // You can use any other inbuilt methods like toast or snackbars
                    // or use the fetched data to update your UI components
                    val balloon = createBalloon(this@MainActivity) {
                        setArrowSize(10)
                        setWidthRatio(1.0f)
                        setHeight(65)
                        setArrowPosition(0.7f)
                        setCornerRadius(4f)
                        setAutoDismissDuration(2000)
                        setAlpha(0.9f)
                        setArrowVisible(false)
                        setText(str)
                        setTextColorResource(android.R.color.white)
                        setBackgroundDrawable(resources.getDrawable(R.drawable.gradient))
                        setBalloonAnimation(BalloonAnimation.CIRCULAR)
                    }

                    constraintRoot.showAlignTop(balloon)
                }
            }
        }
    }
}

