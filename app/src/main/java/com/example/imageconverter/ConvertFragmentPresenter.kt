package com.example.imageconverter

import moxy.MvpPresenter
import android.graphics.Bitmap
import android.net.Uri
import io.reactivex.rxjava3.disposables.CompositeDisposable

class ConvertFragmentPresenter : MvpPresenter<FragmentView>() {
    val disposable = CompositeDisposable()
    fun setPickedImage(selectedFile: Uri?) {
        viewState.setOriginalImage(selectedFile)
    }
    fun convertAndSaveNewImage(image: Bitmap, path: String?){
        disposable.addAll(Converter.convertImage(image, path)
            .subscribe ({ convertedImage ->
                viewState.setConvertedImage(Uri.parse(convertedImage.absolutePath))
            }, { error ->
                println(error.message)
            })
        )
    }
    override fun onDestroy() {
        disposable.dispose()
    }
}