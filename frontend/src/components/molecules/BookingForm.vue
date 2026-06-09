<template>
  <ion-list>

    <!-- First Name -->
    <ion-item>
      <ion-input
        v-model="booking.firstName"
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
        v-model="booking.lastName"
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
        v-model="booking.email"
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
        v-model="booking.emailConfirmation"
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
      <ion-label>Check-In: {{ booking.checkIn }}</ion-label>
    </ion-item>

    <ion-item lines="none">
      <ion-label>Check-Out: {{ booking.checkOut }}</ion-label>
    </ion-item>

    <!-- Breakfast -->
    <ion-item>
      <ion-checkbox v-model="booking.breakfast">
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

const props = defineProps<{
  roomId: number;
  checkIn: string;
  checkOut: string;
}>();

const booking = reactive({
  firstName: '',
  lastName: '',
  email: '',
  emailConfirmation: '',
  checkIn: props.checkIn,
  checkOut: props.checkOut,
  breakfast: false,
});

const errors = reactive({
  firstName: '',
  lastName: '',
  email: '',
  emailConfirmation: '',
});

const validateFirstName = () => {
  errors.firstName = booking.firstName.trim()
    ? ''
    : 'First name is required';
};

const validateLastName = () => {
  errors.lastName = booking.lastName.trim()
    ? ''
    : 'Last name is required';
};

const validateEmail = () => {
  if (!booking.email.includes('@')) {
    errors.email = 'Email must contain @';
  } else {
    errors.email = '';
  }
};

const validateEmailConfirmation = () => {
  errors.emailConfirmation =
    booking.email === booking.emailConfirmation
      ? ''
      : 'Emails do not match';
};

const isFormValid = () => {
  return (
    booking.firstName.trim() &&
    booking.lastName.trim() &&
    booking.email.includes('@') &&
    booking.email === booking.emailConfirmation
  );
};

const submit = () => {
  if (!isFormValid()) return;
  console.log('submit booking', booking);
};
</script>