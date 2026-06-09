<template>
  <ion-card class="room-card">
    <div class="card-inner">
      <img :src="room.image" :alt="room.name" class="room-image">
      <div class="card-body">
        <ion-card-header>
          <ion-card-title>{{ room.name }}</ion-card-title>
          <ion-card-subtitle>€ {{ room.price }} / night</ion-card-subtitle>
        </ion-card-header>

        <ion-card-content>
          <p class="room-description">{{ room.description }}</p>

          <div class="extras">
            <ExtraChip
              v-for="extra in room.extras"
              :key="extra.name"
              :name="extra.name"
              :icon="extra.icon"
            />
          </div>

          <AvailabilityBadge :available="room.available" />
        </ion-card-content>
      </div>
    </div>
  </ion-card>
</template>

<script setup lang="ts">
import { IonCard, IonCardHeader, IonCardTitle, IonCardSubtitle, IonCardContent } from '@ionic/vue'
import ExtraChip from '@/components/atoms/ExtraChip.vue'
import AvailabilityBadge from '@/components/atoms/AvailabilityBadge.vue'
import type { Room } from '@/stores/useRoomStore'

defineProps<{ room: Room }>()
</script>

<style scoped>
.room-card {
  margin: 0 0 12px;
}

.card-inner {
  display: flex;
  flex-direction: column;
}

.room-image {
  width: 100%;
  height: 200px;
  object-fit: cover;
  border-radius: 8px 8px 0 0;
  display: block;
}

.card-body {
  flex: 1;
}

.room-description {
  font-size: 0.9em;
  color: var(--ion-color-medium);
  margin-bottom: 8px;
}

.extras {
  display: flex;
  flex-wrap: wrap;
  gap: 6px;
  margin-bottom: 10px;
}

@media (min-width: 768px) {
  .card-inner {
    flex-direction: row;
    min-height: 180px;
  }

  .room-image {
    width: 280px;
    height: auto;
    min-height: 180px;
    flex-shrink: 0;
    border-radius: 8px 0 0 8px;
  }
}
</style>
