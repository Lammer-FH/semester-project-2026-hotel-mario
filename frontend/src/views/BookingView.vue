<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
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
        :check-in="checkIn",
        :check-out="checkOut"
        @submit="submitBooking"
      />
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import { IonPage, IonContent, IonHeader, IonToolbar, IonTitle, IonButton, IonText, IonList, IonItem, IonCheckbox, IonLabel, IonInput } from '@ionic/vue'
import { computed, reactive } from 'vue';
import { useRoute } from 'vue-router';
import { useRoomStore } from '@/stores/useRoomStore'
import RoomCard from '@/components/molecules/RoomCard.vue';
import BookingForm from '@/components/molecules/BookingForm.vue';
import type { LocationQueryValue } from 'vue-router';

const roomStore = useRoomStore()
const route = useRoute();

const checkIn = computed(() => toString(route.query.checkIn));
const checkOut = computed(() => toString(route.query.checkOut));

const booking = reactive({
  roomId: Number(route.params.roomId),
  firstName: '',
  lastName: '',
  email: '',
  emailConfirmation: '',
  checkIn: checkIn,
  checkOut: checkOut,
  breakfast: false,
});

const roomId = Number(route.params.roomId);



const room = computed(() =>
  roomStore.rooms.find(r => r.id === roomId)
);


function toString(value: LocationQueryValue | LocationQueryValue[] | null): string {
  if (Array.isArray(value)) return value[0] ?? '';
  if (typeof value === 'string') return value;
  return '';
}

const submitBooking = () => {
  console.log('Booking submitted:', booking);
  console.log(room);
  console.log(roomStore.rooms);

  // call API or Pinia action here
};
</script>