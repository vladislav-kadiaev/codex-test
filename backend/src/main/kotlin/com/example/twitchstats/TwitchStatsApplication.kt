package com.example.twitchstats

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TwitchStatsApplication

fun main(args: Array<String>) {
    runApplication<TwitchStatsApplication>(*args)
}
