package com.example.agenda.api

import android.util.Log
import androidx.compose.ui.platform.LocalGraphicsContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class HttpClient {

    private val  client = OkHttpClient.Builder()
    .followRedirects(false)
    .build()
    private   val  baseUrl = "http://192.168.194.72:8080/api"

    // GET Request using Coroutines
    suspend fun get(endpoint: String): String? {

        val request = Request.Builder()
            .url("$baseUrl$endpoint")
            .get()
            .build()

        return executeRequest(request)
    }

    // POST Request using Coroutines
    suspend fun post(endpoint: String, jsonBody: String): String? {
        val body = jsonBody.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        val request = Request.Builder()
            .url("$baseUrl$endpoint")
            .post(body)
            .build()

        return executeRequest(request)
    }

    // PUT Request using Coroutines
    suspend fun put(endpoint: String, jsonBody: String): String? {
        val body = jsonBody.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        val request = Request.Builder()
            .url("$baseUrl$endpoint")
            .put(body)
            .build()

        return executeRequest(request)
    }

    // DELETE Request using Coroutines
    suspend fun delete(endpoint: String): String? {
        val request = Request.Builder()
            .url("$baseUrl$endpoint")
            .delete()
            .build()

        return executeRequest(request)
    }

    // Helper function to execute the request and return the response as a String

    private suspend fun executeRequest(request: Request): String? {
        return withContext(Dispatchers.IO) {
            try {
                val response = client.newCall(request).execute()
                response.body?.string()

            } catch (e: IOException) {
                e.printStackTrace()
                null
            }
        }
    }

}
