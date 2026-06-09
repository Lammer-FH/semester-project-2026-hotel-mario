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
        size="cover"
        presentation="date"
        :min="today"
        :value="tempCheckIn || undefined"
        @ionChange="onCheckInChange"
      />

      <ion-datetime
        v-else
        size="cover"
        presentation="date"
        :min="minCheckOut"
        :value="tempCheckOut || undefined"
        :highlighted-dates="rangeHighlights"
        @ionChange="onCheckOutChange"
        @pointermove="onPointerMove"
        @pointerleave="hoveredDate = null"
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
const hoveredDate = ref<string | null>(null)

watch(() => props.isOpen, (open) => {
  if (open) {
    tempCheckIn.value = props.checkIn ?? null
    tempCheckOut.value = props.checkOut ?? null
    hoveredDate.value = null
    step.value = (props.initialField === 'checkOut' && !!props.checkIn) ? 2 : 1
  }
})

const minCheckOut = computed(() => {
  if (!tempCheckIn.value) return props.today
  const d = new Date(tempCheckIn.value + 'T00:00:00')
  d.setDate(d.getDate() + 1)
  return d.toISOString().split('T')[0]
})

// Returns a new function whenever check-in, check-out, or hovered date changes,
// causing ion-datetime to re-evaluate highlights on every date cell.
const rangeHighlights = computed(() => {
  const start = tempCheckIn.value
  const end = hoveredDate.value || tempCheckOut.value

  return (dateIsoString: string) => {
    const date = dateIsoString.split('T')[0]
    if (!start) return undefined

    if (date === start) {
      return { backgroundColor: '#3880ff', textColor: '#ffffff' }
    }

    if (!end || end <= start) return undefined

    if (date === end) {
      return { backgroundColor: '#3880ff', textColor: '#ffffff' }
    }

    if (date > start && date < end) {
      return { backgroundColor: 'rgba(56, 128, 255, 0.18)', textColor: '#ffffff' }
    }

    return undefined
  }
})

function onPointerMove(event: PointerEvent) {
  for (const el of event.composedPath() as Element[]) {
    if (el instanceof HTMLElement && el.tagName === 'BUTTON') {
      const { day, month, year } = el.dataset
      if (day && month && year) {
        const candidate = `${year}-${month.padStart(2, '0')}-${day.padStart(2, '0')}`
        if (candidate !== hoveredDate.value) hoveredDate.value = candidate
        return
      }
    }
  }
  if (hoveredDate.value !== null) hoveredDate.value = null
}

function onCheckInChange(e: CustomEvent) {
  const val = (e.detail.value as string)?.split('T')[0] ?? null
  if (!val) return
  tempCheckIn.value = val
  if (tempCheckOut.value && tempCheckOut.value <= val) tempCheckOut.value = null
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
  padding: 8px 16px 12px;
}

.summary-line {
  display: block;
  text-align: center;
  margin-bottom: 8px;
  font-size: 0.9em;
}
</style>
