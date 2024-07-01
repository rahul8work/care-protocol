package com.onchain.careprotocol.ui.home

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.text.TextPaint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.onchain.careprotocol.R
import com.onchain.careprotocol.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setGifImage()

        setTextViewShader()

        return root
    }

    private fun setGifImage() {
        val imageView: ImageView = binding.homeCareIv


        val gifLoader = ImageLoader.Builder(this.requireActivity())
            .components {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()

        val imageRequest = ImageRequest.Builder(this.requireActivity())
            .data(R.drawable.forest)
            .target(imageView)
            .build()

        gifLoader.enqueue(imageRequest)
    }

    fun setTextViewShader(){
        val textView: TextView = binding.homeCareTv
        val paint: TextPaint = textView.getPaint()
        val width = paint.measureText("Tianjin, China")

        val textShader: Shader =

            LinearGradient(
            0f, 0f, width, textView.getTextSize(),
            intArrayOf(
                Color.parseColor("#070A52"),
                Color.parseColor("#5C8374"),
                Color.parseColor("#1B4242"),
                Color.parseColor("#092635"),
            ), null, Shader.TileMode.CLAMP
        )

        textView.getPaint().setShader(textShader)
        textView.setPaintFlags(textView.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}