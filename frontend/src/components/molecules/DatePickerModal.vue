<template>
  <ion-modal :is-open="isOpen" @didDismiss="$emit('close')">
    <ion-header>
      <ion-toolbar>
        <ion-buttons slot="start">
          <ion-button v-if="step === 2" @click="step = 1">
            <ion-icon name="arrow-back" />
          </ion-button>
          <ion-button v-else @click="$emit('close')">
            <ion-icon name="close" />
          </ion-button>
        </ion-buttons>
        <ion-title>{{ step === 1 ? 'Check-in · Step 1 of 2' : 'Check-out · Step 2 of 2' }}</ion-title>
      </ion-toolbar>
    </ion-header>

    <ion-content>
      <ion-datetime
        v-if="step === 1"
        presentation="date"
        :min="today"
        :value="tempCheckIn || undefined"
        @ionChange="onCheckInChange"
      />

      <ion-datetime
        v-else
        presentation="date"
        :min="tempCheckIn || today"
        :value="tempCheckOut || undefined"
        :highlighted-dates="checkInHighlight"
        @ionChange="onCheckOutChange"
      />

      <div class="summary">
        <ion-text color="medium" class="summary-line" v-if="step === 1 && tempCheckIn">
          Check-in: <strong>{{ tempCheckIn }}</strong>
        </ion-text>
        <ion-text color="medium" class="summary-line" v-if="step === 2">
          Check-in: <strong>{{ tempCheckIn }}</strong>
          &nbsp;·&nbsp;
          Check-out: <strong>{{ tempCheckOut || '—' }}</strong>
        </ion-text>

        <ion-button
          expand="block"
          :disabled="!tempCheckIn || !tempCheckOut"
          @click="apply"
        >
          Apply
        </ion-button>
      </div>
    </ion-content>
  </ion-modal>
</template>

<script setup lang="ts">
import { ref, computed, watch } from 'vue'
import {
  IonModal, IonContent, IonHeader, IonToolbar, IonTitle,
  IonButton, IonButtons, IonDatetime, IonText, IonIcon
} from '@ionic/vue'

const props = defineProps<{
  isOpen: boolean
  today: string
  checkIn: string | null
  checkOut: string | null
  initialField?: 'checkIn' | 'checkOut'
}>()

const emit = defineEmits<{
  (e: 'close'): void
  (e: 'apply', checkIn: string, checkOut: string): void
}>()

const step = ref(1)
const tempCheckIn = ref<string | null>(null)
const tempCheckOut = ref<string | null>(null)

watch(() => props.isOpen, (open) => {
  if (open) {
    tempCheckIn.value = props.checkIn ?? null
    tempCheckOut.value = props.checkOut ?? null
    step.value = (props.initialField === 'checkOut' && !!props.checkIn) ? 2 : 1
  }
})

const checkInHighlight = computed(() => {
  if (!tempCheckIn.value) return []
  return [{ date: tempCheckIn.value, textColor: '#ffffff', backgroundColor: '#3880ff' }]
})

function onCheckInChange(e: CustomEvent) {
  const val = (e.detail.value as string)?.split('T')[0] ?? null
  if (!val) return
  tempCheckIn.value = val
  if (tempCheckOut.value && tempCheckOut.value <= val) {
    tempCheckOut.value = null
  }
  setTimeout(() => { step.value = 2 }, 150)
}

function onCheckOutChange(e: CustomEvent) {
  tempCheckOut.value = (e.detail.value as string)?.split('T')[0] ?? null
}

function apply() {
  if (!tempCheckIn.value || !tempCheckOut.value) return
  emit('apply', tempCheckIn.value, tempCheckOut.value)
}
</script>

<style scoped>
.summary {
  padding: 12px 16px 24px;
}

.summary-line {
  display: block;
  text-align: center;
  margin-bottom: 12px;
  font-size: 0.9em;
}
</style>
