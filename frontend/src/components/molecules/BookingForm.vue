<template>
  <div>

    <!-- Date interval summary -->
    <div class="date-banner">
      <div class="date-col">
        <span class="date-label">Check-in</span>
        <span class="date-value">{{ formatDate(bookingStore.checkIn) }}</span>
      </div>
      <div class="date-sep">
        <span class="nights-count">{{ nights }} night{{ nights !== 1 ? 's' : '' }}</span>
        <span class="arrow">→</span>
      </div>
      <div class="date-col">
        <span class="date-label">Check-out</span>
        <span class="date-value">{{ formatDate(bookingStore.checkOut) }}</span>
      </div>
    </div>

    <ion-list>

      <!-- First Name -->
      <ion-item>
        <ion-input
          v-model="bookingStore.firstName"
          label="First Name"
          label-placement="stacked"
          @ionBlur="validateFirstName"
        />
      </ion-item>
      <ion-note color="danger" v-if="errors.firstName">
        {{ errors.firstName }}
      </ion-note>

      <!-- Last Name -->
      <ion-item>
        <ion-input
          v-model="bookingStore.lastName"
          label="Last Name"
          label-placement="stacked"
          @ionBlur="validateLastName"
        />
      </ion-item>
      <ion-note color="danger" v-if="errors.lastName">
        {{ errors.lastName }}
      </ion-note>

      <!-- Email -->
      <ion-item>
        <ion-input
          v-model="bookingStore.email"
          type="email"
          label="Email"
          label-placement="stacked"
          @ionBlur="validateEmail"
        />
      </ion-item>
      <ion-note color="danger" v-if="errors.email">
        {{ errors.email }}
      </ion-note>

      <!-- Email Confirmation -->
      <ion-item>
        <ion-input
          v-model="bookingStore.emailConfirmation"
          type="email"
          label="Confirm Email"
          label-placement="stacked"
          @ionBlur="validateEmailConfirmation"
        />
      </ion-item>
      <ion-note color="danger" v-if="errors.emailConfirmation">
        {{ errors.emailConfirmation }}
      </ion-note>

      <!-- Breakfast -->
      <ion-item>
        <ion-checkbox v-model="bookingStore.breakfast">
          Breakfast included
        </ion-checkbox>
      </ion-item>

      <!-- Submit -->
      <ion-button
        expand="block"
        class="ion-margin-top"
        :disabled="!isFormValid()"
        @click="submit"
      >
        Submit Booking
      </ion-button>

    </ion-list>
  </div>
</template>

<script setup lang="ts">
import { computed, reactive } from 'vue';
import { IonButton, IonList, IonItem, IonCheckbox, IonInput, IonNote } from '@ionic/vue'
import { useBookingStore } from '@/stores/useBookingStore';

const bookingStore = useBookingStore();

const emit = defineEmits<{
  (e: 'submit'): void;
}>();

defineProps<{
  roomId: number;
}>();

const errors = reactive({
  firstName: '',
  lastName: '',
  email: '',
  emailConfirmation: '',
});

const nights = computed(() => {
  if (!bookingStore.checkIn || !bookingStore.checkOut) return 0
  const d1 = new Date(bookingStore.checkIn + 'T00:00:00')
  const d2 = new Date(bookingStore.checkOut + 'T00:00:00')
  return Math.max(0, Math.round((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24)))
})

function formatDate(dateStr: string): string {
  if (!dateStr) return '—'
  const d = new Date(dateStr + 'T00:00:00')
  return d.toLocaleDateString('en-GB', { weekday: 'short', day: 'numeric', month: 'short' })
}

const validateFirstName = () => {
  errors.firstName = bookingStore.firstName.trim() ? '' : 'First name is required'
}

const validateLastName = () => {
  errors.lastName = bookingStore.lastName.trim() ? '' : 'Last name is required'
}

const validateEmail = () => {
  errors.email = bookingStore.email.includes('@') ? '' : 'Email must contain @'
}

const validateEmailConfirmation = () => {
  errors.emailConfirmation =
    bookingStore.email === bookingStore.emailConfirmation ? '' : 'Emails do not match'
}

const isFormValid = () =>
  bookingStore.firstName.trim() &&
  bookingStore.lastName.trim() &&
  bookingStore.email.includes('@') &&
  bookingStore.email === bookingStore.emailConfirmation

const submit = () => {
  if (!isFormValid()) return
  emit('submit')
}
</script>

<style scoped>
.date-banner {
  display: flex;
  align-items: center;
  justify-content: space-between;
  background: var(--ion-color-primary);
  color: #ffffff;
  border-radius: 12px;
  padding: 16px 20px;
  margin: 12px 0 16px;
}

.date-col {
  display: flex;
  flex-direction: column;
  align-items: center;
  flex: 1;
}

.date-label {
  font-size: 0.7em;
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 0.08em;
  opacity: 0.8;
  margin-bottom: 4px;
}

.date-value {
  font-size: 1em;
  font-weight: 700;
}

.date-sep {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 0 12px;
  gap: 2px;
}

.nights-count {
  font-size: 0.75em;
  font-weight: 600;
  opacity: 0.9;
  white-space: nowrap;
}

.arrow {
  font-size: 1.2em;
  opacity: 0.7;
}
</style>
