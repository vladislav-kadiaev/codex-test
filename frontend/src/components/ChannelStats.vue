<template>
  <div>
    <input v-model="channel" placeholder="Channel name" />
    <button @click="refresh">Refresh</button>
    <ul>
      <li v-for="s in stats" :key="s.id">{{ new Date(s.timestamp).toLocaleString() }} - {{ s.viewers }} viewers</li>
    </ul>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'

const channel = ref('')
const stats = ref([])

async function refresh() {
  if (!channel.value) return
  await axios.post(`/api/channels/${channel.value}/refresh`)
  const res = await axios.get(`/api/channels/${channel.value}/stats`)
  stats.value = res.data
}
</script>
