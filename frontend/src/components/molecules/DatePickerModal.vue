<template>
  <ion-modal :is-open="isOpen" @didDismiss="$emit('close')">
    <ion-content>
      <ion-datetime
        :min="activeField === 'checkOut'
          ? (checkIn || today)
          : today"
        :max="activeField === 'checkIn'
          ? checkOut
          : undefined"
        :value="modelValue || undefined"
        @ionChange="(e) => $emit('update:modelValue', e.detail.value)"
        presentation="date"
      />

      <ion-button expand="block" @click="$emit('apply')">
        Apply
      </ion-button>
    </ion-content>
  </ion-modal>
</template>

<script setup>
import { IonModal, IonContent, IonDatetime, IonButton } from '@ionic/vue'

defineProps({
  isOpen: Boolean,
  modelValue: String,
  activeField: String,
  checkIn: String,
  checkOut: String,
  today: String
})

defineEmits(['close', 'apply', 'update:modelValue'])
</script>
