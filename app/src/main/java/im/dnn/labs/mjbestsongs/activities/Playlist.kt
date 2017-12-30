package im.dnn.labs.mjbestsongs.activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import im.dnn.labs.mjbestsongs.R
import im.dnn.labs.mjbestsongs.adapters.TrackItemAdapter
import im.dnn.labs.mjbestsongs.iTunes.Playlist
import im.dnn.labs.mjbestsongs.iTunes.iTunes
import im.dnn.labs.mjbestsongs.interfaces.RecyclerTouchListener
import android.app.Activity
import android.content.Intent
import im.dnn.labs.mjbestsongs.helpers.Utils

class Playlist : AppCompatActivity () {
    private var ctx: Context? = null
    private var atx: Activity? = null
    private var utils: Utils? = null

    override fun onCreate (savedInstanceState: Bundle?) {
        super.onCreate (savedInstanceState)
        setContentView (R.layout.activity_playlist)
        ctx = this
        atx = this
        utils = Utils (ctx, atx)

        val itunes = iTunes ()
        val playlist = itunes.searchPlaylist ("Michael+Jackson") as Playlist
        buildPlaylist (playlist)

    }

    private fun buildPlaylist (playlist: Playlist) {
        val tracklist = findViewById<RecyclerView>(R.id.tracklist)
        val tracks = TrackItemAdapter (playlist.tracks)

        val lmanager: RecyclerView.LayoutManager = LinearLayoutManager (this)
        tracklist.layoutManager = lmanager
        tracklist.itemAnimator = DefaultItemAnimator ()
        tracklist.adapter = tracks

        val listener = object : RecyclerTouchListener.ClickListener {
            override fun onClick (view: View, pos: Int) {
                val track = playlist.tracks [pos]

                val intent = Intent (ctx, Track::class.java)
                intent.putExtra ("track", track.serialize ())
                startActivity (intent)
            }
            override fun onLongClick (view: View, pos: Int) { }
        }
        tracklist.addOnItemTouchListener (RecyclerTouchListener (applicationContext, tracklist, listener))
    }

    private var back_presed: Long = 0
    override fun onBackPressed () {
        if (back_presed + 2000 > System.currentTimeMillis ()) {
            utils!!.escape ()
        } else {
            Toast.makeText (ctx, getString (R.string.press_to_exit), Toast.LENGTH_SHORT).show ()
        }
        back_presed = System.currentTimeMillis ()
    }
}
