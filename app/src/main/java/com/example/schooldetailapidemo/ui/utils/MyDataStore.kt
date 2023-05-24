package com.example.schooldetailapidemo.ui.utils

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.util.Locale.ENGLISH

class MyDataStore constructor(private val context: Context) {

    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
        private val IS_LOGIN = booleanPreferencesKey("isLogin")
        private val TOKEN = stringPreferencesKey("token")
        private val ID = stringPreferencesKey("id")
        var count = 0
        private const val LANGUAGE_PREFERENCES_NAME = "language_preferences"
        private val LANGUAGE_KEY = stringPreferencesKey("language_key")
        private val ENGLISH = stringPreferencesKey("english")
        private val ARABIC = stringPreferencesKey("arabic")
        private val Context.languagePreferences by preferencesDataStore(name = LANGUAGE_PREFERENCES_NAME)
    }

    suspend fun setUserId(id: String) {
        context.dataStore.edit { preferences ->
            preferences[ID] = id
        }
    }

    val getUserId: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[ID] ?: ""
        }


    //User login or not save
    suspend fun isUserLogin(isLogin: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_LOGIN] = isLogin
        }
    }

    //Check user login or not
    val isUserLogin: Flow<Boolean?> = context.dataStore.data
        .map { preferences ->
            preferences[IS_LOGIN] ?: false
        }

    suspend fun setToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN] = token
        }
    }

    val getToken: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[TOKEN] ?: ""
        }


    suspend fun deleteNamePreferences() {
        context.dataStore.edit { preferences ->
            preferences.remove(TOKEN)
        }
    }

    suspend fun deleteAllPreferences() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }


    suspend fun setLanguage(language: String) {
        context.dataStore.edit { preferences ->

                Log.d("language---------------------------------",language)
                preferences[ENGLISH] = language

                preferences[ARABIC] = language

        }
    }

    val getLanguage: Flow<String> = context.dataStore.data.map { preferences ->

            preferences[ENGLISH] ?: ""

            preferences[ARABIC] ?: ""

    }
}


