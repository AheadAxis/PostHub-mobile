package com.posthub.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var supabase: SupabaseClient = createSupabaseClient(
        supabaseUrl = "https://uqgggijqjezqffbgytie.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InVxZ2dnaWpxamV6cWZmYmd5dGllIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDA0Nzk4MTcsImV4cCI6MjAxNjA1NTgxN30.48KSE1m_uk68kecgbDQM2F9BjJ9FVczSWJYTfPz-OAs"
    ) {
        install(Postgrest)
    }
}