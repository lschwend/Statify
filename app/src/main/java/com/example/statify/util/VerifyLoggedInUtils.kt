package com.example.statify.util

//fun <T> Activity.guardValidSpotifyApi(
//    classBackTo: Class<out Activity>,
//    alreadyTriedToReauthenticate: Boolean = false,
//    block: suspend (api: SpotifyClientApi) -> T
//): T? {
//    return runBlocking {
//        try {
//            val token = Model.credentialStore.spotifyToken
//                ?: throw SpotifyException.ReAuthenticationNeededException()
//            val usesPkceAuth = token.refreshToken != null
//            val api = (if (usesPkceAuth) Model.credentialStore.getSpotifyClientPkceApi()
//            else Model.credentialStore.getSpotifyImplicitGrantApi())
//                ?: throw SpotifyException.ReAuthenticationNeededException()
//
//            block(api)
//        } catch (e: SpotifyException) {
//            e.printStackTrace()
//            val usesPkceAuth = Model.credentialStore.spotifyToken?.refreshToken != null
//            val api = Model.credentialStore.getSpotifyClientPkceApi()!!
//            if (!alreadyTriedToReauthenticate) {
//                try {
//                    api.refreshToken()
//                    Model.credentialStore.spotifyToken = api.token
//                    block(api)
//                } catch (e: SpotifyException.ReAuthenticationNeededException) {
//                    e.printStackTrace()
//                    return@runBlocking guardValidSpotifyApi(
//                        classBackTo = classBackTo,
//                        alreadyTriedToReauthenticate = true,
//                        block = block
//                    )
//                } catch (e: IllegalArgumentException) {
//                    e.printStackTrace()
//                    return@runBlocking guardValidSpotifyApi(
//                        classBackTo = classBackTo,
//                        alreadyTriedToReauthenticate = true,
//                        block = block
//                    )
//                }
//            } else {
//                pkceClassBackTo = classBackTo
//                startSpotifyClientPkceLoginActivity(SpotifyPkceLoginActivityImpl::class.java)
//                null
//            }
//        }
//    }
//}
