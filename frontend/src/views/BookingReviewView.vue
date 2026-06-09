<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-buttons slot="start">
          <ion-back-button default-href="/room" />
        </ion-buttons>
        <ion-title>Review Booking</ion-title>
      </ion-toolbar>
    </ion-header>

    <ion-content class="ion-padding">

      <!-- Booking summary -->
      <ion-list class="ion-margin-top">

        <ion-item>
          <ion-label>
            <b>Name:</b> {{ bookingStore.firstName }} {{ bookingStore.lastName }}
          </ion-label>
        </ion-item>

        <ion-item>
          <ion-label>
            <b>Email:</b> {{ bookingStore.email }}
          </ion-label>
        </ion-item>

        <ion-item>
          <ion-label>
            <b>Check-in:</b> {{ bookingStore.checkIn }}
          </ion-label>
        </ion-item>

        <ion-item>
          <ion-label>
            <b>Check-out:</b> {{ bookingStore.checkOut }}
          </ion-label>
        </ion-item>

        <ion-item>
          <ion-label>
            <b>Breakfast:</b> {{ bookingStore.breakfast ? 'Yes' : 'No' }}
          </ion-label>
        </ion-item>

      </ion-list>

      <!-- Actions -->
      <ion-button expand="block" @click="confirmBooking">
        Confirm Booking
      </ion-button>

      <ion-button expand="block" fill="outline" @click="goBack">
        Edit
      </ion-button>

    </ion-content>
  </ion-page>
</template>


<script setup lang="ts">
import { IonPage, IonContent, IonHeader, IonToolbar, IonTitle, IonLabel, IonButton, IonList, IonItem, IonButtons, IonBackButton } from '@ionic/vue'
import { useRouter, useRoute } from 'vue-router';
import { useBookingStore } from '@/stores/useBookingStore';
const router = useRouter();
const route = useRoute();

const bookingStore = useBookingStore();

const goBack = () => {
  router.back(); // returns to form, state is preserved
};

const confirmBooking = () => {
  console.log('FINAL BOOKING:', {
    ...bookingStore.$state,
  });

  router.push({ name: 'BookingDetails' }); // or success page
};
</script>