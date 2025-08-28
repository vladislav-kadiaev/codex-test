package com.example.twitchstats

import com.example.twitchstats.model.Channel
import com.example.twitchstats.model.ViewStat
import com.example.twitchstats.repository.ChannelRepository
import com.example.twitchstats.repository.ViewStatRepository
import com.example.twitchstats.service.ChannelStatsService
import com.example.twitchstats.service.TwitchClient
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.time.Instant
import java.util.Optional

@SpringBootTest
class ChannelStatsServiceTest {

    @MockBean
    lateinit var channelRepository: ChannelRepository

    @MockBean
    lateinit var viewStatRepository: ViewStatRepository

    @MockBean
    lateinit var twitchClient: TwitchClient

    @Autowired
    lateinit var service: ChannelStatsService

    @Test
    fun `refreshStats stores viewer count`() {
        val channel = Channel(id = 1, name = "somechannel")
        given(channelRepository.findByName("somechannel")).willReturn(Optional.of(channel))
        given(twitchClient.fetchViewerCount("somechannel")).willReturn(123)
        given(viewStatRepository.save(Mockito.any(ViewStat::class.java))).willAnswer { it.arguments[0] }

        val stat = service.refreshStats("somechannel")
        assertEquals(123, stat.viewers)
    }

    @Test
    fun `getMonthlyStats returns stats`() {
        val channel = Channel(id = 1, name = "somechannel")
        val stat = ViewStat(id = 1, channel = channel, timestamp = Instant.now(), viewers = 10)
        given(channelRepository.findByName("somechannel")).willReturn(Optional.of(channel))
        given(
            viewStatRepository.findByChannelAndTimestampAfterOrderByTimestampDesc(
                Mockito.eq(channel),
                Mockito.any(Instant::class.java)
            )
        ).willReturn(listOf(stat))

        val stats = service.getMonthlyStats("somechannel")
        assertEquals(listOf(stat), stats)
    }
}
