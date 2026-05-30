<template>
  <ion-list>
    <ion-card v-for="room in rooms" :key="room.id">
      <ion-card-header>
        <img :src="room.image" :alt="room.name" class="room-image">
        <ion-card-title>{{ room.name }}</ion-card-title>
        <ion-card-subtitle>€ {{ room.price }} / night</ion-card-subtitle>
      </ion-card-header>

      <ion-card-content>
        <p class="room-description">{{ room.description }}</p>

        <div class="extras">
          <span v-for="extra in room.extras" :key="extra.name" class="extra-chip">
            <ion-icon :name="extra.icon" class="extra-icon" />
            {{ extra.name }}
          </span>
        </div>

        <ion-badge v-if="room.available !== null" :color="room.available ? 'success' : 'danger'" class="availability-badge">
          {{ room.available ? 'Available' : 'Not Available' }}
        </ion-badge>
      </ion-card-content>
    </ion-card>
  </ion-list>
</template>

<script setup>
import {
  IonList,
  IonCard,
  IonCardHeader,
  IonCardTitle,
  IonCardSubtitle,
  IonCardContent,
  IonBadge,
  IonIcon
} from '@ionic/vue'

defineProps({
  rooms: Array
})
</script>

<style scoped>
.room-image {
  width: 100%;
  height: 200px;
  object-fit: cover;
  border-radius: 8px;
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

.extra-chip {
  display: inline-flex;
  align-items: center;
  gap: 4px;
  background: var(--ion-color-light);
  border-radius: 12px;
  padding: 2px 8px;
  font-size: 0.8em;
}

.extra-icon {
  font-size: 1em;
}

.availability-badge {
  margin-top: 4px;
}
</style>
