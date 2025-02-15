package com.logbook.snackstats.ui.screens.addfoodlog

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import com.logbook.snackstats.BuildConfig
import java.util.Locale


class AddFoodLogViewModel: ViewModel() {

    private var _imageFileUri: Uri? = null
    val imageFileUri: Uri? get() = _imageFileUri

    private val _selectedImageUri = MutableStateFlow<Uri?>(null)
    val selectedImageUri: StateFlow<Uri?> = _selectedImageUri


    fun createCameraImageFile(context: Context): Uri? {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        val imageFile = File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        )

        _imageFileUri = FileProvider.getUriForFile(
            context,
            "${BuildConfig.APPLICATION_ID}.provider", // Use the correct file provider authority
            imageFile
        )

        return _imageFileUri
    }

    fun onImageSelected(uri: Uri?) {
        _selectedImageUri.value = uri
    }


}