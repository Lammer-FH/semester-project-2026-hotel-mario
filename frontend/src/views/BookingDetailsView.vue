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
          <ion-card-title>{{ errorTitle }}</ion-card-title>
        </ion-card-header>
        <ion-card-content>
          <ion-item lines="none">
            <ion-label>{{ errorBody }}</ion-label>
          </ion-item>
        </ion-card-content>
        <ion-button v-if="bookingStore.errorStatus === 409" expand="block" @click="goToRoomSelection">Choose another room</ion-button>
        <ion-button v-else-if="isRetryable" expand="block" @click="submit">Try again</ion-button>
        <ion-button v-else expand="block" @click="goBack">Back</ion-button>
      </ion-card>

      <!-- Success response -->
      <ion-card v-else-if="response">
        <ion-card-header>
          <ion-card-title>Booking Confirmed</ion-card-title>
        </ion-card-header>
        <ion-card-content>

  <!-- Booking Basics -->
        <ion-card-subtitle>Booking Details</ion-card-subtitle>

        <ion-item lines="none">
            <ion-label><strong>Booking ID:</strong> {{ response.id }}</ion-label>
        </ion-item>

        <ion-item lines="none">
            <ion-label><strong>Name:</strong> {{ response.firstName }} {{ response.lastName }}</ion-label>
        </ion-item>

        <ion-item lines="none">
            <ion-label><strong>Check-in:</strong> {{ response.checkIn }}</ion-label>
        </ion-item>

        <ion-item lines="none">
            <ion-label><strong>Check-out:</strong> {{ response.checkOut }}</ion-label>
        </ion-item>

        <ion-item lines="none">
            <ion-label><strong>Breakfast:</strong> {{ response.breakfast ? 'Yes' : 'No' }}</ion-label>
        </ion-item>

        <ion-divider class="ion-margin-vertical" />

        <!-- ROOM DETAILS -->
        <ion-card-title>Room</ion-card-title>

        <ion-item lines="none">
            <ion-label><strong>Title:</strong> {{ response.room.title }}</ion-label>
        </ion-item>

        <ion-item lines="none">
            <ion-label><strong>Description:</strong> {{ response.room.description }}</ion-label>
        </ion-item>

        <ion-item lines="none">
            <ion-label><strong>Price per night:</strong> €{{ response.room.pricePerNight }}</ion-label>
        </ion-item>

        <!-- Room Extras -->
        <div class="ion-margin-top">
            <ion-card-subtitle>Extras</ion-card-subtitle>
            <div class="ion-flex ion-flex-wrap">
            <ion-chip
                v-for="extra in response.room.extras"
                :key="extra.id"
                class="ion-margin-end ion-margin-bottom"
            >
                <ion-icon :name="extra.icon" />
                <ion-label>{{ extra.name }}</ion-label>
            </ion-chip>
            </div>
        </div>

        <ion-divider class="ion-margin-vertical" />

        <!-- HOTEL DETAILS -->
        <ion-card-title>Hotel</ion-card-title>

        <ion-item lines="none">
            <ion-label><strong>Name:</strong> {{ response.hotel.name }}</ion-label>
        </ion-item>

        <ion-item lines="none">
            <ion-label>
            <strong>Address:</strong>
            {{ response.hotel.address.street }},
            {{ response.hotel.address.city }},
            {{ response.hotel.address.postalCode }},
            {{ response.hotel.address.country }}
            </ion-label>
        </ion-item>

        <ion-item lines="none">
            <ion-label>
            <strong>Coordinates:</strong>
            {{ response.hotel.address.latitude }}, {{ response.hotel.address.longitude }}
            </ion-label>
        </ion-item>

        <ion-item lines="none">
            <ion-label><strong>Phone:</strong> {{ response.hotel.contact.phone }}</ion-label>
        </ion-item>

        <ion-item lines="none">
            <ion-label><strong>Email:</strong> {{ response.hotel.contact.email }}</ion-label>
        </ion-item>

        <ion-item lines="none">
            <ion-label><strong>Directions:</strong> {{ response.hotel.directions }}</ion-label>
        </ion-item>

        </ion-card-content>
        <ion-button expand="block" @click="goHome">Home</ion-button>
      </ion-card>

    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import { IonPage, IonContent, IonHeader, IonToolbar, IonTitle, IonCard, IonCardHeader, IonCardContent, IonButton, IonCardTitle, IonSpinner, IonIcon, IonLabel, IonCardSubtitle, IonItem, IonChip} from '@ionic/vue'
import { computed, onMounted, ref } from 'vue';
import { useBookingStore } from '@/stores/useBookingStore';
import { useRouter } from 'vue-router';
import { BookingDto, BookingResponseDto } from '@/services/api';


const bookingStore = useBookingStore();
const router = useRouter();

const loading = ref(true);
const response = ref<BookingResponseDto | null>(null);
const error = ref<boolean>(false);

async function submit() {
  loading.value = true;
  error.value = false;
  response.value = await bookingStore.sendBooking();
  error.value = bookingStore.error;
  loading.value = false;
  if (!error.value) bookingStore.reset();
}

onMounted(submit);

const isRetryable = computed(() => bookingStore.errorStatus === 0 || bookingStore.errorStatus >= 500)

const errorTitle = computed(() => {
  if (bookingStore.errorStatus === 409) return 'Room no longer available'
  if (bookingStore.errorStatus === 0) return 'Connection failed'
  if (bookingStore.errorStatus >= 500) return 'Server error'
  return 'Booking Error'
})

const errorBody = computed(() => {
  if (bookingStore.errorStatus === 409) return 'This room was booked by someone else. Please choose another room.'
  if (bookingStore.errorStatus === 0) return 'Could not reach the server. Please check your connection and try again.'
  if (bookingStore.errorStatus >= 500) return 'Something went wrong on our end. Please try again.'
  return bookingStore.errorMessage
})

const goBack = () => router.back();
const goHome = () => router.push({ name: 'home' });
const goToRoomSelection = () => router.push({ name: 'Room' });

</script>