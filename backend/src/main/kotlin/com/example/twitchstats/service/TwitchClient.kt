package com.example.twitchstats.service

interface TwitchClient {
    fun fetchViewerCount(channelName: String): Int
}
