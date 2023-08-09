package com.plcoding.testingcourse.part12.data

import android.content.Context
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import androidx.work.Configuration
import androidx.work.testing.SynchronousExecutor
import androidx.work.testing.TestListenableWorkerBuilder
import androidx.work.testing.WorkManagerTestInitHelper
import androidx.work.workDataOf
import assertk.assertThat
import assertk.assertions.isLessThan
import assertk.assertions.isNotNull
import com.plcoding.testingcourse.R
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.File

class PhotoCompressionWorkerTest {

    @get:Rule
    val contentUriRule = TestContentUriRule(R.drawable.kermit)

    @Before
    fun setUp() {
        val config = Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setExecutor(SynchronousExecutor())
            .build()
        WorkManagerTestInitHelper.initializeTestWorkManager(
            ApplicationProvider.getApplicationContext(),
            config
        )
    }

    @Test
    fun testImageCompression_imageGetsSmaller() = runBlocking {
        val initialPhotoSize = contentUriRule.file.length()

        val input = workDataOf(
            PhotoCompressionWorker.KEY_CONTENT_URI to contentUriRule.contentUri.toString(),
            PhotoCompressionWorker.KEY_COMPRESSION_THRESHOLD to 5_000L
        )

        val context = ApplicationProvider.getApplicationContext<Context>()
        val request = TestListenableWorkerBuilder<PhotoCompressionWorker>(context)
            .setInputData(input)
            .build()

        val result = request.doWork()

        val outputFilePath = result.outputData.getString(PhotoCompressionWorker.KEY_RESULT_PATH)
        assertThat(outputFilePath).isNotNull()

        val compressedFile = File(outputFilePath!!)
        assertThat(compressedFile.length()).isLessThan(initialPhotoSize)
    }
}