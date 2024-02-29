package com.posthub.di.modules
import com.posthub.di.repositoryes.SupabaseRepository
import com.posthub.di.repositoryes.SupabaseRepositoryImpl
import org.koin.dsl.module

val supabaseModule = module {
    single<SupabaseRepository> { SupabaseRepositoryImpl() }
}