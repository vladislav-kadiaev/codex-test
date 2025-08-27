package com.example.twitchstats.repository

import com.example.twitchstats.model.Channel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface ChannelRepository : JpaRepository<Channel, Long> {
    fun findByName(name: String): Optional<Channel>
}
