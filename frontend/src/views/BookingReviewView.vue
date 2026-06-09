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

      <ion-list class="ion-margin-top">
        <DetailRow label="Name" :value="`${bookingStore.firstName} ${bookingStore.lastName}`" />
        <DetailRow label="Email" :value="bookingStore.email" />
        <DetailRow label="Check-in" :value="bookingStore.checkIn" />
        <DetailRow label="Check-out" :value="bookingStore.checkOut" />
        <DetailRow label="Breakfast" :value="bookingStore.breakfast ? 'Yes' : 'No'" />
      </ion-list>

      <ion-button expand="block" @click="confirmBooking">Confirm Booking</ion-button>
      <ion-button expand="block" fill="outline" @click="goBack">Edit</ion-button>

    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import { IonPage, IonContent, IonHeader, IonToolbar, IonTitle, IonButton, IonList, IonButtons, IonBackButton } from '@ionic/vue'
import { useRouter } from 'vue-router'
import { useBookingStore } from '@/stores/useBookingStore'
import DetailRow from '@/components/atoms/DetailRow.vue'

const router = useRouter()
const bookingStore = useBookingStore()

const goBack = () => router.back()
const confirmBooking = () => router.push({ name: 'BookingDetails' })
</script>
