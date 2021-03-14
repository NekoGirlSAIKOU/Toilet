package pers.nekogirlsaikou.toilet.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon

fun Drawable.toBitmap():Bitmap{
    val bitmap = if (this.intrinsicWidth <= 0 || this.intrinsicHeight <= 0){
        Bitmap.createBitmap(
            1,
            1,
            Bitmap.Config.ARGB_8888
        )
    } else {
        Bitmap.createBitmap(
            this.intrinsicWidth,
            this.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
    }
    val canvas = Canvas(bitmap)
    this.setBounds(0, 0, canvas.getWidth(), canvas.getHeight())
    this.draw(canvas)
    return bitmap
}

fun Drawable.toIcon():Icon{
    return Icon.createWithBitmap(toBitmap())
}
