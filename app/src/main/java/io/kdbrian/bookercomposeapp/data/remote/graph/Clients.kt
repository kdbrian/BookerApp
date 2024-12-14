package io.kdbrian.bookercomposeapp.data.remote.graph

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.ApolloRequest
import com.apollographql.apollo.api.ApolloResponse
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.interceptor.ApolloInterceptor
import com.apollographql.apollo.interceptor.ApolloInterceptorChain
import com.apollographql.apollo.network.okHttpClient
import io.kdbrian.bookercomposeapp.data.util.Urls
import kotlinx.coroutines.flow.Flow
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import timber.log.Timber

object Clients {

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    Timber.d("Okhttp -> [${chain.request().method}]: ${chain.request().url}")
                    return chain.proceed(chain.request())
                }

            }
        )
        .build()

    val defaultClient = ApolloClient.Builder()
        .serverUrl(Urls.localGraphAppUrl)
        .addInterceptor(
            object : ApolloInterceptor{
                override fun <D : Operation.Data> intercept(
                    request: ApolloRequest<D>,
                    chain: ApolloInterceptorChain
                ): Flow<ApolloResponse<D>> {
                    Timber.d("Okhttp -> [${request.httpMethod?.name}]: ${request.operation}")
                    return chain.proceed(request)
                }
            }
        )
        .okHttpClient(okHttpClient)
        .build()

}