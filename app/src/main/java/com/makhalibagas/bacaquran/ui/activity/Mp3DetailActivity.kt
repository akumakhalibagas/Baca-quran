package com.makhalibagas.bacaquran.ui.activity

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.makhalibagas.bacaquran.R
import com.makhalibagas.bacaquran.adapter.AyatAdapterKt
import com.makhalibagas.bacaquran.model.quran.SuratMp3
import com.makhalibagas.bacaquran.viewmodel.AyatViewModel
import kotlinx.android.synthetic.main.activity_mp3_detail.*

class Mp3DetailActivity : AppCompatActivity(){


    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var seekBar: SeekBar
    private lateinit var ayatViewModel: AyatViewModel
    private var lastProgress = 0
    private var handler : Handler = Handler()
    private var isPlaying = false

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_mp3_detail)


        seekBar = findViewById(R.id.seekbar)
        val suratMp3 = intent.getParcelableExtra<SuratMp3>(EXTRA_DATA)
        if (suratMp3 != null){
            ayatViewModel = ViewModelProviders.of(this).get(AyatViewModel::class.java)
            ayatViewModel.loadDataAyat(suratMp3.nomor)
            ayatViewModel.allAyat.observe(this, Observer {
                vp.adapter = AyatAdapterKt(vp, it)
            })
        }

        vp.clipToPadding = false
        vp.clipChildren = false
        vp.offscreenPageLimit = 3
        vp.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        btPlay.setOnClickListener {
            if (!isPlaying && suratMp3 != null){
                isPlaying = true
                startPlaying(suratMp3)
            }else{
                isPlaying = false
                stopPlaying()
            }
        }
    }


    private fun stopPlaying(){
        try {
            mediaPlayer.release()
        }catch (e:Exception){
            e.message
        }

        mediaPlayer == null
        btPlay.setBackgroundResource(R.drawable.ic_play)
    }
    private fun startPlaying(suratMp3: SuratMp3){
        mediaPlayer = MediaPlayer()
        try {
            mediaPlayer.setDataSource(suratMp3.audio)
            mediaPlayer.prepare()
            mediaPlayer.start()

        }catch (e : Exception){
            e.message
        }


        btPlay.setBackgroundResource(R.drawable.ic_pause)
        seekbar.progress = lastProgress
        mediaPlayer.seekTo(lastProgress)
        seekBar.max = mediaPlayer.duration
        seekUpdate()


        mediaPlayer.setOnCompletionListener {
            btPlay.setBackgroundResource(R.drawable.ic_play)
            isPlaying = false
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (mediaPlayer != null && fromUser){
                    mediaPlayer.seekTo(progress)
                    lastProgress = progress
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

    }
    private fun runnable() : Runnable = Runnable {
        seekUpdate()
    }
    private fun seekUpdate(){
        if (mediaPlayer != null){
            seekBar.progress = mediaPlayer.currentPosition
            lastProgress = mediaPlayer.currentPosition
        }

        handler.postDelayed(runnable(), 100)
    }


}
