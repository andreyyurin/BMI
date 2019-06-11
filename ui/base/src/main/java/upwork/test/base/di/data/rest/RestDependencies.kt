package upwork.test.base.di.data.rest

import retrofit2.Retrofit

interface RestDependencies{
    fun retrofit(): Retrofit
}