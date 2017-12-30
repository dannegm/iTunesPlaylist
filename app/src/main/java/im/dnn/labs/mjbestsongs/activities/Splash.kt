package im.dnn.labs.mjbestsongs.activities

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import im.dnn.labs.mjbestsongs.R
import android.app.Activity
import android.content.Context
import im.dnn.labs.mjbestsongs.helpers.Utils
import android.widget.TextView
import im.dnn.labs.mjbestsongs.BuildConfig


class Splash : AppCompatActivity() {
    private var ctx: Context? = null
    private var atx: Activity? = null
    private var utils: Utils? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        ctx = this
        atx = this

        utils = Utils (ctx, atx)

        val versionName = findViewById<TextView> (R.id.versionName)
        versionName.text = "${getString (R.string.version)} ${BuildConfig.VERSION_NAME}"

        chectConectionStatus ()
    }

    private fun chectConectionStatus () {
        if (utils!!.IsConnected ()) {
            utils!!.to (Playlist::class.java)
        } else {
            utils!!.alertToEscape (getString (R.string.no_connection_alert))
        }
    }

    override fun onResume () {
        super.onResume ()
        chectConectionStatus ()
    }

    override fun onRestart () {
        super.onRestart ()
        chectConectionStatus ()
    }

    override fun onBackPressed () {
        utils!!.escape ()
    }
}
