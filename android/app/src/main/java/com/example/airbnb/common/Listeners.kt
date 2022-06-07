package com.example.airbnb.common

interface OnRequestPermissionListener {

    fun onGranted()

    fun onDenied(deniedPermissions: List<String>)
}