package com.example.agenda.api

import com.example.agenda.ui.components.Agenda
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


fun parseActivities(responseBody: String): List<Agenda> {
    val gson = Gson()
    val activityListType = object : TypeToken<List<Agenda>>() {}.type
    return gson.fromJson(responseBody, activityListType)
}

fun parseActivity(responseBody: String): Agenda {
    val gson = Gson()
    val activityListType = object : TypeToken<Agenda>() {}.type
    return gson.fromJson(responseBody, activityListType)
}
