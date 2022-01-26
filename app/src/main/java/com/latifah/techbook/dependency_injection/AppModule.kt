package com.latifah.techbook.dependency_injection

import android.content.Context
import androidx.room.Room
import com.latifah.techbook.constants.Constants.BASE_URL
import com.latifah.techbook.constants.Constants.TECHBOOK_DATABASE_NAME
import com.latifah.techbook.database.TechbookDB
import com.latifah.techbook.network.TechEventApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDogDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        TechbookDB::class.java,
        TECHBOOK_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideDogDao(db: TechbookDB) = db.getTechbookDao()

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideOkhttpInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideDogImageApi(
        moshi: Moshi,
        networkLoggingInterceptor: HttpLoggingInterceptor
    ): TechEventApiService {

        return Retrofit.Builder()
            .client(OkHttpClient.Builder().addInterceptor(networkLoggingInterceptor).build())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()
            .create(TechEventApiService::class.java)

    }

}