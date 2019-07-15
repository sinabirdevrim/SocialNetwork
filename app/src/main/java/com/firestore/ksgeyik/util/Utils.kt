package com.firestore.ksgeyik.util

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView

object Utils {
    fun openCamera(appCompatActivity: AppCompatActivity) {
        TedPermission.with(appCompatActivity).setPermissionListener(object : PermissionListener {
            override fun onPermissionGranted() {
                CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1, 1)
                    .start(appCompatActivity)
            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
            }
        }).setDeniedMessage("Permission neccessary")
            .setPermissions(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ).check()
    }
}