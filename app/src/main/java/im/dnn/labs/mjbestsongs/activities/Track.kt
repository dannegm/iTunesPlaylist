package im.dnn.labs.mjbestsongs.activities

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import im.dnn.labs.mjbestsongs.R
import im.dnn.labs.mjbestsongs.helpers.Picloader
import im.dnn.labs.mjbestsongs.helpers.Utils
import im.dnn.labs.mjbestsongs.iTunes.Track
import org.json.JSONObject
import android.widget.Toast
import im.dnn.labs.mjbestsongs.R.id.playPause
import android.media.AudioManager
import android.media.MediaPlayer
import android.opengl.Visibility
import java.io.IOException


class Track : AppCompatActivity () {
    private var ctx: Context? = null
    private var utils: Utils? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate (savedInstanceState)
        setContentView (R.layout.activity_track)
        ctx = this
        utils = Utils (ctx)

        val bundle = intent.extras
        val trackJSON = JSONObject (bundle.getString ("track"))
        val track = Track (trackJSON)


        //! Artwork
        val trackName = findViewById<TextView> (R.id.trackName)
        val collectionName = findViewById<TextView> (R.id.collectionName)
        val artwork = findViewById<ImageView> (R.id.artwork)

        trackName.text = track.trackName
        collectionName.text = track.collectionName

        val picture = Picloader (artwork)
        picture
            .placeholder (utils!!.getDrawable (R.drawable.artwork_placeholder))
            .error (utils!!.getDrawable (R.drawable.artwork_error))
            .load (track.artworkUrl100)
            .render ()

        //! Metadata
        val trackPrice = findViewById<TextView> (R.id.trackPrice)
        val currency = findViewById<TextView> (R.id.currency)
        val purchase = findViewById<View> (R.id.purchase)

        trackPrice.text = track.trackPrice.toString()
        currency.text = track.currency
        purchase.setOnClickListener ({
            val intent = Intent (Intent.ACTION_VIEW)
            intent.data = Uri.parse (track.trackViewUrl)
            startActivity (intent)
        })

        //! Player
        //buildPlayer (Uri.parse (track.previewUrl))

        val playPause = findViewById<View> (R.id.playPause)
        playPause.setOnClickListener ({
            Toast.makeText (ctx, "Don't work, sorry :(", Toast.LENGTH_SHORT).show ()
        })

        //! Tools
        val toPlaylist = findViewById<View>(R.id.toPlaylist)
        toPlaylist.setOnClickListener ({ this.finish () })
    }

    val player: MediaPlayer? = null
    fun buildPlayer (stream: Uri) {
        val player = MediaPlayer ()
        try {
            player.setDataSource (ctx, stream)
            player.setAudioStreamType (AudioManager.STREAM_MUSIC);
            player.prepare ()

            val playPause = findViewById<View> (R.id.playPause)
            playPause.setOnClickListener ({
                onPlayPause ()
            })

            player.setOnCompletionListener {
                onPlayerStatusChange ()
            }
        } catch (e: IOException) {
            Toast.makeText (ctx, e.message, Toast.LENGTH_SHORT).show ()
        }
    }

    fun onPlayPause () {
        onPlayerStatusChange ()
        if (!player!!.isPlaying) {
            player!!.start ()
        } else {
            player!!.pause ()
        }
    }

    fun onPlayerStatusChange () {
        val ic_play = findViewById<View> (R.id.playIcon)
        val ic_pause = findViewById<View> (R.id.pauseIcon)

        if (!player!!.isPlaying) {
            ic_play.visibility = View.GONE
            ic_pause.visibility = View.VISIBLE
        } else {
            ic_play.visibility = View.VISIBLE
            ic_pause.visibility = View.GONE
        }
    }

    override fun onBackPressed () {
        this.finish ()
    }
}
