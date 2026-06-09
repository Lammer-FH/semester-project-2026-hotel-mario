<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-title>Booking Details</ion-title>
      </ion-toolbar>
    </ion-header>

    <ion-content class="ion-padding">

      <!-- Loading state -->
      <ion-spinner v-if="loading" name="crescent"></ion-spinner>

      <!-- Error message -->
      <ion-card v-else-if="error">
        <ion-card-header>
          <ion-card-title>Error</ion-card-title>
        </ion-card-header>
        <ion-card-content>
          {{ error }}
        </ion-card-content>
        <ion-button expand="block" @click="goBack">Back</ion-button>
      </ion-card>

      <!-- Success response -->
      <ion-card v-else-if="response">
        <ion-card-header>
          <ion-card-title>Booking Confirmed</ion-card-title>
        </ion-card-header>
        <ion-card-content>
            <p><b>Booking ID:</b> {{ response.id }}</p>
            <p><b>Name:</b> {{ response.firstName }} {{ response.lastName }}</p>
            <p><b>Check-in:</b> {{ response.checkIn }}</p>
            <p><b>Check-out:</b> {{ response.checkOut }}</p>
            <p><b>Breakfast:</b> {{ response.breakfast ? 'Yes' : 'No' }}</p>

            <ion-divider class="ion-margin-vertical" />
            <!-- ROOM -->
             <ion-card-subtitle>Booking Confirmed</ion-card-subtitle>
            <h1>Room</h1>

            <p><b>Title:</b> {{ response.room.title }}</p>
            <p><b>Description:</b> {{ response.room.description }}</p>
            <p><b>Price per night:</b> €{{ response.room.pricePerNight }}</p>

            <div class="extras ion-margin-top">
                <h4>Extras</h4>

                <ion-chip
                v-for="extra in response.room.extras"
                :key="extra.id"
                >
                <ion-icon :name="extra.icon" />
                <ion-label>{{ extra.name }}</ion-label>
                </ion-chip>
            </div>

            <ion-divider class="ion-margin-vertical" />


            <h1>Hotel</h1>

            <p><b>Name:</b> {{ response.hotel.name }}</p>

            <p>
                <b>Address:</b>
                {{ response.hotel.address.street }},
                {{ response.hotel.address.city }},
                {{ response.hotel.address.postalCode }},
                {{ response.hotel.address.country }}
            </p>

            <p>
                <b>Coordinates:</b>
                {{ response.hotel.address.latitude }},
                {{ response.hotel.address.longitude }}
            </p>

            <p><b>Phone:</b> {{ response.hotel.contact.phone }}</p>
            <p><b>Email:</b> {{ response.hotel.contact.email }}</p>

            <p><b>Directions:</b></p>
            <p>{{ response.hotel.directions }}</p>

        </ion-card-content>
        <ion-button expand="block" @click="goHome">Home</ion-button>
      </ion-card>

    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import { IonPage, IonContent, IonHeader, IonToolbar, IonTitle, IonCard, IonCardHeader, IonCardContent, IonButton, IonCardTitle, IonSpinner, IonIcon, IonLabel, IonCardSubtitle} from '@ionic/vue'
import { onMounted, ref } from 'vue';
import { useBookingStore } from '@/stores/useBookingStore';
import { useRouter } from 'vue-router';
import { BookingDto, BookingResponseDto } from '@/services/api';


const bookingStore = useBookingStore();
const router = useRouter();

const loading = ref(true);
const response = ref<BookingResponseDto | null>(null);
const error = ref<string | null>(null);

onMounted(async () => {
  response.value = await bookingStore.sendBooking()
  loading.value = false;
  console.log("Backen retunrned: ", response);
  /*
  if (response.value === null)
  {
    error.value = "An e"
  }
    */
});

const goBack = () => router.back();
const goHome = () => router.push({ name: 'home' });

</script>