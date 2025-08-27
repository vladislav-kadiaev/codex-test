package com.example.twitchstats.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class TwitchApiClient(
    @Value("\${twitch.client-id}") private val clientId: String,
    @Value("\${twitch.access-token}") private val accessToken: String,
    private val webClient: WebClient = WebClient.create()
) : TwitchClient {
    override fun fetchViewerCount(channelName: String): Int {
        // This method makes a call to Twitch API to fetch live viewer count.
        // For offline environments this will return 0.
        val uri = "https://api.twitch.tv/helix/streams?user_login=$channelName"
        val response = webClient.get()
            .uri(uri)
            .header("Client-Id", clientId)
            .header("Authorization", "Bearer $accessToken")
            .retrieve()
            .bodyToMono(Map::class.java)
            .block() ?: return 0
        val data = response["data"] as? List<*>
        val first = data?.firstOrNull() as? Map<*, *>
        return (first?.get("viewer_count") as? Int) ?: 0
    }
}
