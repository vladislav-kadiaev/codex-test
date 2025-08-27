package com.example.twitchstats.service

import com.example.twitchstats.model.Channel
import com.example.twitchstats.model.ViewStat
import com.example.twitchstats.repository.ChannelRepository
import com.example.twitchstats.repository.ViewStatRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
class ChannelStatsService(
    private val channelRepository: ChannelRepository,
    private val viewStatRepository: ViewStatRepository,
    private val twitchClient: TwitchClient
) {
    @Transactional
    fun refreshStats(channelName: String): ViewStat {
        val viewers = twitchClient.fetchViewerCount(channelName)
        val channel = channelRepository.findByName(channelName)
            .orElseGet { channelRepository.save(Channel(name = channelName)) }
        val stat = ViewStat(channel = channel, timestamp = Instant.now(), viewers = viewers)
        return viewStatRepository.save(stat)
    }

    fun getStats(channelName: String): List<ViewStat> {
        val channel = channelRepository.findByName(channelName)
        return channel.map { viewStatRepository.findByChannelOrderByTimestampDesc(it) }.orElse(emptyList())
    }
}
