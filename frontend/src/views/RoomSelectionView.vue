
<template>
  <ion-page>
    <router-link to="/home" class="links">Startpage</router-link>
    <ion-header>
      <ion-toolbar>
        <ion-title>Room Selection</ion-title>
      </ion-toolbar>
    </ion-header>

    <ion-content>

      <FilterBar
        :filters="filterBarProps"
        :priceError="filterStore.priceError"
        @openDate="(f) => filterStore.openPicker(f, today)"
        @updatePersons="(v) => filterStore.persons = v"
        @updateMin="(v) => filterStore.minPrice = v ? Number(v) : null"
        @updateMax="(v) => filterStore.maxPrice = v ? Number(v) : null"
        @updateAvailable="(v) => filterStore.availableOnly = v"
        @apply="applyFilters"
      />

      <ion-text color="danger" v-if="filterStore.dateError" class="date-error">
        {{ filterStore.dateError }}
      </ion-text>

      <ion-text color="danger" v-if="roomStore.error" class="date-error">
        {{ roomStore.error }}
      </ion-text>

      <ion-text v-if="roomStore.loading" color="medium" class="date-error">
        Loading rooms...
      </ion-text>

      <ion-text v-else-if="filteredRooms.length === 0 && roomStore.rooms.length > 0" color="medium" class="empty-state">
        No rooms match your criteria for the selected dates.
      </ion-text>

      <RoomList v-else :rooms="paginatedRooms" />

      <DatePickerModal
        :today="today"
        :isOpen="filterStore.pickerOpen"
        :modelValue="filterStore.pickerTemp"
        :activeField="filterStore.pickerField"
        :checkIn="filterStore.checkIn"
        :checkOut="filterStore.checkOut"
        @update:modelValue="(v) => filterStore.pickerTemp = v"
        @apply="filterStore.applyPicker"
        @close="filterStore.closePicker"
      />

      <div class="pagination">
        <ion-button @click="previousPage" :disabled="currentPage === 1">Previous</ion-button>
        <span>{{ currentPage }} / {{ totalPages }}</span>
        <ion-button @click="nextPage" :disabled="currentPage === totalPages">Next</ion-button>
      </div>

    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import { IonPage, IonContent, IonHeader, IonToolbar, IonTitle, IonButton, IonText } from '@ionic/vue'

import FilterBar from '@/components/FilterBar.vue'
import RoomList from '@/components/RoomList.vue'
import DatePickerModal from '@/components/DatePickerModal.vue'

import { useRoomStore } from '@/stores/roomStore'
import { useFilterStore } from '@/stores/filterStore'

const roomStore = useRoomStore()
const filterStore = useFilterStore()

const currentPage = ref(1)
const pageSize = 5
const today = new Date().toISOString().split('T')[0]

const filterBarProps = computed(() => ({
  persons: filterStore.persons,
  checkIn: filterStore.checkIn,
  checkOut: filterStore.checkOut,
  minPrice: filterStore.minPrice,
  maxPrice: filterStore.maxPrice,
  availableOnly: filterStore.availableOnly,
}))

const filteredRooms = computed(() => {
  if (filterStore.availableOnly)
    return roomStore.rooms.filter(r => r.available === true)
  return roomStore.rooms
})

const totalPages = computed(() => Math.max(1, Math.ceil(filteredRooms.value.length / pageSize)))

const paginatedRooms = computed(() => {
  const start = (currentPage.value - 1) * pageSize
  return filteredRooms.value.slice(start, start + pageSize)
})

const nextPage = () => { if (currentPage.value < totalPages.value) currentPage.value++ }
const previousPage = () => { if (currentPage.value > 1) currentPage.value-- }

async function applyFilters() {
  if (filterStore.priceError) return
  filterStore.clearDateError()
  if (!filterStore.datesSelected) return

  const err = await roomStore.checkAvailability(filterStore.checkIn!, filterStore.checkOut!)
  if (err) {
    filterStore.setDateError(err)
    return
  }
  currentPage.value = 1
}

onMounted(() => {
  roomStore.fetchRooms()
})
</script>

<style scoped>
.links {
  padding: 12px 20px;
  display: inline-block;
}

.date-error {
  display: block;
  padding: 4px 16px;
}

.empty-state {
  display: block;
  padding: 24px 16px;
  text-align: center;
}
</style>
