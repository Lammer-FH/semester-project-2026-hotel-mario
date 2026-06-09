<template>
  <ion-page>
    <ion-header class="no-print">
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
      <ion-card v-else-if="response" class="confirmation-card">
        <ion-card-header>
          <ion-card-title>Booking Confirmed</ion-card-title>
        </ion-card-header>
        <ion-card-content>

          <ion-card-subtitle>Booking Details</ion-card-subtitle>
          <DetailRow label="Booking ID" :value="response.id" />
          <DetailRow label="Name" :value="`${response.firstName} ${response.lastName}`" />
          <DetailRow label="Check-in" :value="response.checkIn" />
          <DetailRow label="Check-out" :value="response.checkOut" />
          <DetailRow label="Breakfast" :value="response.breakfast ? 'Yes' : 'No'" />

          <hr class="section-divider" />

          <ion-card-title>Room</ion-card-title>

          <img
            :src="`/images/rooms/${response.room.id}.jpg`"
            :alt="response.room.title"
            class="room-image"
          />

          <DetailRow label="Title" :value="response.room.title" />
          <DetailRow label="Description" :value="response.room.description" />
          <DetailRow label="Price per night" :value="`€${response.room.pricePerNight}`" />

          <div class="ion-margin-top">
            <ion-card-subtitle>Extras</ion-card-subtitle>
            <div class="extras-row">
              <ExtraChip
                v-for="extra in response.room.extras"
                :key="extra.id"
                :name="extra.name"
                :icon="extra.icon"
              />
            </div>
          </div>

          <hr class="section-divider" />

          <ion-card-title>Hotel</ion-card-title>
          <DetailRow label="Name" :value="response.hotel.name" />
          <DetailRow label="Address" :value="hotelAddress" />
          <DetailRow label="Phone" :value="response.hotel.contact.phone" />
          <DetailRow label="Email" :value="response.hotel.contact.email" />
          <DetailRow label="Directions" :value="response.hotel.directions" />

        </ion-card-content>

        <div class="action-buttons no-print">
          <ion-button expand="block" @click="goHome">Home</ion-button>
          <ion-button expand="block" fill="outline" @click="print()">Print</ion-button>
        </div>
      </ion-card>

    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import { IonPage, IonContent, IonHeader, IonToolbar, IonTitle, IonCard, IonCardHeader,
  IonCardContent, IonButton, IonCardTitle, IonSpinner, IonLabel,
  IonCardSubtitle, IonItem } from '@ionic/vue'
import { computed, onMounted, ref } from 'vue'
import { useBookingStore } from '@/stores/useBookingStore'
import { useRouter } from 'vue-router'
import { BookingResponseDto } from '@/services/api'
import DetailRow from '@/components/atoms/DetailRow.vue'
import ExtraChip from '@/components/atoms/ExtraChip.vue'

const bookingStore = useBookingStore()
const router = useRouter()

const loading = ref(true)
const response = ref<BookingResponseDto | null>(null)
const error = ref<boolean>(false)

async function submit() {
  loading.value = true
  error.value = false
  response.value = await bookingStore.sendBooking()
  error.value = bookingStore.error
  loading.value = false
  if (!error.value) bookingStore.reset()
}

onMounted(submit)

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

const hotelAddress = computed(() => {
  if (!response.value) return ''
  const a = response.value.hotel.address
  return `${a.street}, ${a.city}, ${a.postalCode}, ${a.country}`
})

const goBack = () => router.back()
const goHome = () => router.push({ name: 'home' })
const goToRoomSelection = () => router.push({ name: 'Room' })

function print() { window.print() }
</script>

<style scoped>
.room-image {
  width: 100%;
  max-height: 220px;
  object-fit: cover;
  border-radius: 8px;
  margin: 12px 0 8px;
  display: block;
}

.extras-row {
  display: flex;
  flex-wrap: wrap;
  gap: 6px;
  margin-top: 4px;
}

.action-buttons {
  display: flex;
  flex-direction: column;
  padding: 0 16px 16px;
  gap: 8px;
}

.section-divider {
  border: none;
  border-top: 1px solid var(--ion-color-light-shade);
  margin: 12px 0;
}
</style>

<!-- Global print styles — not scoped so they reach Ionic shadow DOM -->
<style>
@media print {
  @page {
    size: A4 portrait;
    margin: 15mm 20mm;
  }

  .no-print,
  ion-spinner {
    display: none !important;
  }

  ion-app,
  ion-page,
  ion-router-outlet {
    position: static !important;
    contain: none !important;
    overflow: visible !important;
  }

  ion-content {
    --background: #ffffff;
    --color: #000000;
    position: static !important;
    contain: none !important;
    overflow: visible !important;
  }

  ion-content::part(scroll) {
    position: static !important;
    overflow: visible !important;
  }

  .confirmation-card {
    box-shadow: none !important;
    border: none !important;
  }

  ion-card-title,
  ion-card-subtitle {
    color: #000000 !important;
  }

  ion-item::part(native) {
    background: transparent !important;
    color: #000000 !important;
    padding-inline-start: 0 !important;
    min-height: 28px !important;
  }

  ion-label {
    color: #000000 !important;
  }

  .extra-chip {
    background: #f4f4f4 !important;
    color: #000000 !important;
    border: 1px solid #cccccc !important;
  }

  .room-image {
    max-height: 160px;
    page-break-inside: avoid;
  }

  .section-divider {
    border: none;
    border-top: 1px solid #cccccc;
    margin: 8px 0;
  }
}
</style>
