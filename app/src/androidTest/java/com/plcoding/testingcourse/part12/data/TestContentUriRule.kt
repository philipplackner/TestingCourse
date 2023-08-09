package com.plcoding.testingcourse.part12.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.core.content.FileProvider
import androidx.test.core.app.ApplicationProvider
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import java.io.File

class TestContentUriRule(
    private val resId: Int
): TestWatcher() {

    lateinit var file: File
    lateinit var contentUri: Uri

    override fun starting(description: Description?) {
        super.starting(description)
        val context = ApplicationProvider.getApplicationContext<Context>()
        val bitmap = BitmapFactory.decodeResource(context.resources, resId)

        file = File(context.cacheDir, "test_image.jpg")
        file.outputStream().use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
        }

        contentUri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )
    }

    override fun finished(description: Description?) {
        super.finished(description)
        val context = ApplicationProvider.getApplicationContext<Context>()
        File(context.cacheDir, "test_image.jpg").delete()
    }
}