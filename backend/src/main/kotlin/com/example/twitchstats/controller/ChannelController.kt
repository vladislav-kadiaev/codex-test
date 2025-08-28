package com.example.twitchstats.controller

import com.example.twitchstats.model.ViewStat
import com.example.twitchstats.service.ChannelStatsService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/channels")
class ChannelController(private val service: ChannelStatsService) {

    @PostMapping("/{name}/refresh")
    fun refresh(@PathVariable name: String): ViewStat = service.refreshStats(name)

    @GetMapping("/{name}/stats")
    fun stats(@PathVariable name: String): List<ViewStat> = service.getStats(name)

    @GetMapping("/{name}/stats/month")
    fun monthlyStats(@PathVariable name: String): List<ViewStat> = service.getMonthlyStats(name)
}
