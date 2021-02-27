package com.makhalibagas.bacaquran.ui.activity

import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.makhalibagas.bacaquran.R
import com.makhalibagas.bacaquran.adapter.RecommendPengajianAdapter
import com.makhalibagas.bacaquran.model.pengajian.Pengajian
import com.makhalibagas.bacaquran.utils.DataPengajian
import kotlinx.android.synthetic.main.activity_video.*

class VideoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_video)

        val pengajian = intent.getParcelableExtra<Pengajian>("EXTRA")
        if (pengajian != null){
            val mediaController = MediaController(this);
            videoView.setVideoURI(Uri.parse(pengajian.url))
            videoView.setMediaController(mediaController)
            videoView.requestFocus()
            videoView.start()
            mediaController.show()
            tv_name.text = pengajian.name

        }


        rvRekomend.layoutManager = LinearLayoutManager(this)
        rvRekomend.adapter = RecommendPengajianAdapter(DataPengajian.dataPengajian())
    }
}
