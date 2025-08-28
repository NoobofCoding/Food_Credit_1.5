package com.example.foodcredit15.data

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Gotrue
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage
import io.github.jan.supabase.realtime.Realtime

object SupabaseHolder {

    val client: SupabaseClient by lazy {
        createSupabaseClient(
            supabaseUrl = "https://pqcevmuppumzdooscxwf.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBxY2V2bXVwcHVtemRvb3NjeHdmIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NTYzMTAyNTQsImV4cCI6MjA3MTg4NjI1NH0.6OEe5QP9r7ByusMpLKMybaK2ebviF8d4FTFm-i_pLHo"
        ) {
            install(Gotrue)
            install(Postgrest)
            install(Storage)
            install(Realtime)
        }
    }
}

