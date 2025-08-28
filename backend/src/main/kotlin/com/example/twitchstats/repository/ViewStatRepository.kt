package com.example.twitchstats.repository

import com.example.twitchstats.model.Channel
import com.example.twitchstats.model.ViewStat
import org.springframework.data.jpa.repository.JpaRepository
import java.time.Instant

interface ViewStatRepository : JpaRepository<ViewStat, Long> {
    fun findByChannelOrderByTimestampDesc(channel: Channel): List<ViewStat>
    fun findByChannelAndTimestampAfterOrderByTimestampDesc(channel: Channel, timestamp: Instant): List<ViewStat>
}
