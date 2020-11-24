package com.example.kotlindemo.http

import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*

class SSLSocketClient {
    companion object {
        fun getSSLSocketFactory(): SSLSocketFactory {
            val sslContext: SSLContext = SSLContext.getInstance("SSL")
            sslContext.init(null, getTrustManager(), SecureRandom())
            return sslContext.socketFactory
        }

        fun getTrustManager(): Array<TrustManager> {
            return arrayOf(object : X509TrustManager {
                override fun checkServerTrusted(
                    chain: Array<out X509Certificate>?,
                    authType: String?
                ) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }

                override fun checkClientTrusted(
                    chain: Array<out X509Certificate>?,
                    authType: String?
                ) {
                }

            })
        }

        fun getHostnameVerifier(): HostnameVerifier {
            return HostnameVerifier { _, _ -> true }
        }
    }
}