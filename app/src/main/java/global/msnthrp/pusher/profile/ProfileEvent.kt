package global.msnthrp.pusher.profile

import android.graphics.Bitmap

sealed class ProfileEvent

data class CodeBitmapReady(val bitmap: Bitmap) : ProfileEvent()
