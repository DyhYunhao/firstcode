package com.dyh.firistcode.camera

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.FileProvider
import com.dyh.firistcode.R
import com.zxy.tiny.Tiny
import java.io.File

class CameraActivity : AppCompatActivity() {
    val takePhoto = 1
    val fromAlbum = 2
    lateinit var imageUri: Uri
    lateinit var outputImage: File

    val options: Tiny.BitmapCompressOptions = Tiny.BitmapCompressOptions()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        findViewById<Button>(R.id.btn_take_photo).setOnClickListener {
            outputImage = File(externalCacheDir, "output_image.jpg")
            if (outputImage.exists()) {
                outputImage.delete()
            }
            outputImage.createNewFile()
            imageUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                FileProvider.getUriForFile(this, "com.dyh.firistcode.fileprovider", outputImage)
            } else {
                Uri.fromFile(outputImage)
            }

            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            startActivityForResult(intent, takePhoto)
        }

        findViewById<Button>(R.id.btn_from_album).setOnClickListener {
            //打开文件选择器
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            //指定只显示图片
            intent.type = "image/*"
            Log.d("TAG", "startActivityForResult:")
            startActivityForResult(intent, fromAlbum)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("TAG", "requestCode: $resultCode")
        when (requestCode) {
            takePhoto -> {
                Log.d("TAG", "takePhoto:")
                if (resultCode == Activity.RESULT_OK) {
                    val bitmap =
                        BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri))

                    //使用tiny压缩解决图片过大无法显示
                    Tiny.getInstance().source(bitmap).asBitmap().withOptions(options)
                        .compress { _, bitmap1, _ ->
                            findViewById<ImageView>(R.id.iv_photo).setImageBitmap(rotateIfRequired(bitmap1))
                        }

                    //findViewById<ImageView>(R.id.iv_photo).setImageBitmap(rotateIfRequired(bitmap))
                }
            }

            fromAlbum -> {
                Log.d("TAG", "getBitmapFromUri:")
                Log.d("TAG", "getBitmapFromUri: ${data?.data.toString()}")
                if (resultCode == Activity.RESULT_OK && data != null) {
                    data.data?.let { uri ->
                        //选择图片显示
                        var bitmap = getBitmapFromUri(uri)
                        Tiny.getInstance().source(bitmap).asBitmap().withOptions(options)
                            .compress { _, bitmap1, _ ->
                                findViewById<ImageView>(R.id.iv_photo).setImageBitmap(bitmap1)
                            }
                       // findViewById<ImageView>(R.id.iv_photo).setImageBitmap(bitmap)
                    }
                }
            }
        }
    }

    private fun getBitmapFromUri(uri: Uri) = contentResolver.openFileDescriptor(uri, "r")?.use {
        Log.d("TAG", "getBitmapFromUri: ")
        BitmapFactory.decodeFileDescriptor(it.fileDescriptor)
    }

    private fun rotateIfRequired(bitmap: Bitmap): Bitmap {
        val exif = ExifInterface(outputImage.path)
        return when (exif.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_NORMAL
        )) {
            ExifInterface.ORIENTATION_ROTATE_90 -> rotateBitmap(bitmap, 90)
            ExifInterface.ORIENTATION_ROTATE_180 -> rotateBitmap(bitmap, 180)
            ExifInterface.ORIENTATION_ROTATE_270 -> rotateBitmap(bitmap, 270)
            else -> bitmap
        }
    }

    private fun rotateBitmap(bitmap: Bitmap, degree: Int): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(degree.toFloat())
        val rotateBitmap =
            Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        bitmap.recycle()
        return rotateBitmap
    }
}