package com.example.twitchstats.model

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "view_stats")
data class ViewStat(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id")
    val channel: Channel,

    val timestamp: Instant,
    val viewers: Int
)
