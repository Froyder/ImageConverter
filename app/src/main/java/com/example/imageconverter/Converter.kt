package com.example.imageconverter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleOnSubscribe
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.lang.Exception

object Converter {
    fun convertImage(image: Bitmap, path: String?) : Single <File>{
        return Single.create(SingleOnSubscribe {
            if (it.isDisposed) return@SingleOnSubscribe
            val newImage = File(path, "NewImage.png")
            val stream: OutputStream = FileOutputStream(newImage)
            if (image.compress(Bitmap.CompressFormat.JPEG, 100, stream))
                it.onSuccess(newImage)
            else
                it.onError(Exception("Conversion problem"))
            stream.flush()
            stream.close()
        })
    }
}