<template>
  <div class="room-list">
    <RoomCard v-for="room in rooms" :key="room.id" :room="room" />
  </div>
</template>

<script setup lang="ts">
import RoomCard from '@/components/molecules/RoomCard.vue'
import type { Room } from '@/stores/useRoomStore'

defineProps<{ rooms: Room[] }>()
</script>

<style scoped>
.room-list {
  padding: 8px;
}

@media (min-width: 768px) {
  .room-list {
    padding: 0;
  }
}
</style>
