<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-buttons slot="start">
          <ion-back-button default-href="/room" />
        </ion-buttons>
        <ion-title>Book Your Room</ion-title>
      </ion-toolbar>
    </ion-header>

    <ion-content class="ion-padding">
      <RoomCard
        v-if="room"
        :room="room"
      />
      <BookingForm
        :room-id="roomId"
        @submit="submitBooking"
      />
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import { IonPage, IonContent, IonHeader, IonToolbar, IonTitle, IonButtons, IonBackButton } from '@ionic/vue'
import { computed, reactive } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { useRoomStore } from '@/stores/useRoomStore'
import RoomCard from '@/components/molecules/RoomCard.vue';
import BookingForm from '@/components/molecules/BookingForm.vue';
import type { LocationQueryValue } from 'vue-router';
import { useBookingStore } from '@/stores/useBookingStore';
import { useFilterStore } from '@/stores/useFilterStore';

const roomStore = useRoomStore()
const route = useRoute();

const router = useRouter();

const bookingStore = useBookingStore();
const filterStore = useFilterStore();

bookingStore.roomId = Number(route.params.roomId);
bookingStore.checkIn = toString(filterStore.checkIn);
bookingStore.checkOut = toString(filterStore.checkOut);

const roomId = Number(route.params.roomId);



const room = computed(() =>
  roomStore.rooms.find(r => r.id === roomId)
);


function toString(value: string |  null): string {
  if (typeof value === 'string') return value;
  return '';
}

const submitBooking = () => {
  router.push({
    name: 'BookingReview',
    params: {
      roomId: bookingStore.roomId,
    },
  });
};
</script>