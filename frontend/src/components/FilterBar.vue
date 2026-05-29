<template>
  <ion-grid>
    <ion-row>
      <!-- Dates -->
      <ion-col size="6">
        <ion-item button @click="$emit('openDate', 'checkIn')">
          <ion-label>
            {{ filters.checkIn || 'Check-in' }}
          </ion-label>
        </ion-item>
      </ion-col>

      <ion-col size="6">
        <ion-item button @click="$emit('openDate', 'checkOut')">
          <ion-label>
            {{ filters.checkOut || 'Check-out' }}
          </ion-label>
        </ion-item>
      </ion-col>
    </ion-row>

    <!-- Persons -->
    <ion-row>
      <ion-col size="6">
        <ion-select
          :value="filters.persons"
          label="Persons"
          @ionChange="(e) => $emit('updatePersons', e.detail.value)"
        >
          <ion-select-option v-for="n in 4" :key="n" :value="n">
            {{ n }}
          </ion-select-option>
        </ion-select>
      </ion-col>

      <ion-col size="6" class="availability-toggle">
        <ion-label>Show only available Rooms</ion-label>
        <ion-checkbox :checked="filters.availableOnly" @ionChange="(e) => $emit('updateAvailable', e.detail.checked)" />
      </ion-col>
    </ion-row>

    <!-- Price -->
    <ion-row>
      <ion-col size="6">
        <ion-input
          :value="filters.minPrice"
          type="number"
          placeholder="Min €"
          @ionInput="(e) => $emit('updateMin', e.detail.value)"
        />
      </ion-col>

      <ion-col size="6">
        <ion-input
          :value="filters.maxPrice"
          type="number"
          placeholder="Max €"
          @ionInput="(e) => $emit('updateMax', e.detail.value)"
        />
      </ion-col>
    </ion-row>

    <!-- Error -->
    <ion-text color="danger" v-if="priceError">
      {{ priceError }}
    </ion-text>

    <ion-button expand="block" @click="$emit('apply')">
      Apply Filters
    </ion-button>
  </ion-grid>
</template>

<script setup>
import {
  IonGrid,
  IonRow,
  IonCol,
  IonItem,
  IonLabel,
  IonSelect,
  IonSelectOption,
  IonInput,
  IonButton,
  IonText,
  IonCheckbox
} from '@ionic/vue'

defineProps({
  filters: Object,
  priceError: String
})

defineEmits([
  'openDate',
  'updatePersons',
  'updateMin',
  'updateMax',
  'updateAvailable',
  'apply'
])
</script>

<style scoped>
.availability-toggle {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
</style>