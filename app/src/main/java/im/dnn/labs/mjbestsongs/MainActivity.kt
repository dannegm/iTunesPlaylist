package im.dnn.labs.mjbestsongs

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import im.dnn.labs.mjbestsongs.helpers.Utils
import android.widget.TextView
import im.dnn.labs.mjbestsongs.activities.Splash


class MainActivity : AppCompatActivity () {
    private var ctx: Context? = null
    private var atx: Activity? = null
    private var actx: Class<*>? = null

    @SuppressLint ("SetTextI18n")
    override fun onCreate (savedInstanceState: Bundle?) {
        super.onCreate (savedInstanceState)
        setContentView (R.layout.activity_main)

        ctx = this
        atx = this
        actx = Splash::class.java

        val versionName = findViewById<TextView> (R.id.versionName)
        versionName.text = "${getString(R.string.version)} ${BuildConfig.VERSION_NAME}"
        start ()

    }

    fun start () {
        val utils = Utils (ctx, atx)
        utils.to (actx)
    }
}
