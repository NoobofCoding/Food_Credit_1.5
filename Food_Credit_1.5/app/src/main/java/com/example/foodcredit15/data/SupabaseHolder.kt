package com.example.foodcredit15.data

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseHolder {

    private const val SUPABASE_URL = "https://pqcevmuppumzdooscxwf.supabase.co"
    private const val SUPABASE_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBxY2V2bXVwcHVtemRvb3NjeHdmIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NTYzMTAyNTQsImV4cCI6MjA3MTg4NjI1NH0.6OEe5QP9r7ByusMpLKMybaK2ebviF8d4FTFm-i_pLHo"

    val client: SupabaseClient by lazy {
        createSupabaseClient(
            supabaseUrl = SUPABASE_URL,
            supabaseKey = SUPABASE_KEY
        ) {
            install(Auth)
            install(Postgrest)
        }
    }
}


