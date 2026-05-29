<template>
  <ion-list>
    <ion-card v-for="room in rooms" :key="room.id">
    <ion-card-header>

      <!-- Image -->
      <img :src="room.image" alt="Room image" class="room-image">
        <ion-card-title>{{ room.name }}</ion-card-title>
        <ion-card-subtitle>€ {{ room.price }}</ion-card-subtitle>
      </ion-card-header>

      <ion-card-content>
        <p>{{ room.description }}</p>

        <!-- Availability -->
        <ion-badge :color="room.available ? 'success' : 'danger'">
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
  IonBadge
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
</style>