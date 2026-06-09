<template>
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

    <!-- Check-in / Check-out (read-only) -->
    <ion-item lines="none">
      <ion-label>Check-In: {{ bookingStore.checkIn }}</ion-label>
    </ion-item>

    <ion-item lines="none">
      <ion-label>Check-Out: {{ bookingStore.checkOut }}</ion-label>
    </ion-item>

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
</template>

<script setup lang="ts">
import { reactive } from 'vue';
import { IonButton, IonList, IonItem, IonCheckbox, IonLabel, IonInput, IonNote} from '@ionic/vue'
import { useBookingStore } from '@/stores/useBookingStore';

const bookingStore = useBookingStore();

const emit = defineEmits<{
  (e: 'submit'): void;
}>();

const props = defineProps<{
  roomId: number;
}>();

const errors = reactive({
  firstName: '',
  lastName: '',
  email: '',
  emailConfirmation: '',
});

const validateFirstName = () => {
  errors.firstName = bookingStore.firstName.trim()
    ? ''
    : 'First name is required';
};

const validateLastName = () => {
  errors.lastName = bookingStore.lastName.trim()
    ? ''
    : 'Last name is required';
};

const validateEmail = () => {
  if (!bookingStore.email.includes('@')) {
    errors.email = 'Email must contain @';
  } else {
    errors.email = '';
  }
};

const validateEmailConfirmation = () => {
  errors.emailConfirmation =
    bookingStore.email === bookingStore.emailConfirmation
      ? ''
      : 'Emails do not match';
};

const isFormValid = () => {
  return (
    bookingStore.firstName.trim() &&
    bookingStore.lastName.trim() &&
    bookingStore.email.includes('@') &&
    bookingStore.email === bookingStore.emailConfirmation
  );
};

const submit = () => {
  if (!isFormValid()) return;

  emit('submit');
};
</script>