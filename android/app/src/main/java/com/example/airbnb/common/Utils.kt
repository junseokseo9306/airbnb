package com.example.airbnb.common

import android.content.Context
import android.content.pm.PackageManager
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Context.hasPermission(permission: String): Boolean =
    ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

fun Fragment.requestPermissionsResult(
    permissionArray: Array<String>,
    permissionListener: OnRequestPermissionListener
) {

    val launcher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->

            val deniedPermissions = permissions.entries.filter { !it.value }
                .map { it.key }

            if (deniedPermissions.isNotEmpty()) {
                permissionListener.onDenied(deniedPermissions)
            } else {
                permissionListener.onGranted()
            }
        }

    launcher.launch(permissionArray)
}

fun View.showSnackbar(msgId: Int, showLength: Int, actionMessageId: Int, action: (View) -> Unit) {
    val snackbar = Snackbar.make(this, context.getString(msgId), showLength)
    snackbar.setAction(context.getString(actionMessageId)) {
        action(this)
    }.show()
}