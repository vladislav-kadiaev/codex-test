package com.example.twitchstats.repository

import com.example.twitchstats.model.Channel
import com.example.twitchstats.model.ViewStat
import org.springframework.data.jpa.repository.JpaRepository

interface ViewStatRepository : JpaRepository<ViewStat, Long> {
    fun findByChannelOrderByTimestampDesc(channel: Channel): List<ViewStat>
}
