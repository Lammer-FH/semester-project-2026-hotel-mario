
<template>
  <ion-page>
     <router-link to="/home" class="links">
        Startpage
    </router-link>
    <ion-header>
    <ion-toolbar>
      <ion-title>Room Selection</ion-title>
    </ion-toolbar>
  </ion-header>
    <ion-content>

      <FilterBar
        :filters="filters"
        :priceError="priceError"
        @openDate="openPicker"
        @updatePersons="(v) => filters.persons = v"
        @updateMin="onMinChange"
        @updateMax="onMaxChange"
        @updateAvailable="(v) => filters.availableOnly = v"
        @apply="applyFilters"
      />

      <ion-text color="danger" v-if="dateError" class="date-error">
        {{ dateError }}
      </ion-text>

      <ion-text v-if="filteredRooms.length === 0 && rooms.length > 0" color="medium" class="empty-state">
        No rooms match your criteria for the selected dates.
      </ion-text>

      <RoomList v-else :rooms="paginatedRooms" />

      <DatePickerModal
        :today="today"
        :isOpen="isPickerOpen"
        :modelValue="tempDate"
        :activeField="activeField"
        :checkIn="filters.checkIn"
        :checkOut="filters.checkOut"
        @update:modelValue="(v) => tempDate = v"
        @apply="applyDate"
        @close="isPickerOpen = false"
      />

      <div class="pagination">

      <ion-button
        @click="previousPage"
        :disabled="currentPage === 1"
      >
        Previous
      </ion-button>

      <span>
        {{ currentPage }} / {{ totalPages }}
      </span>

      <ion-button
        @click="nextPage"
        :disabled="currentPage === totalPages"
      >
        Next
      </ion-button>

</div>

    </ion-content>
  </ion-page>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { IonPage, IonContent, IonHeader, IonToolbar, IonTitle, IonButton, IonText } from '@ionic/vue'

import FilterBar from '@/components/FilterBar.vue'
import RoomList from '@/components/RoomList.vue'
import DatePickerModal from '@/components/DatePickerModal.vue'
import { getRooms, checkRoomAvailability } from '@/services/api'

const filters = ref({
  persons: 2,
  checkIn: null,
  checkOut: null,
  minPrice: null,
  maxPrice: null,
  availableOnly: false,
})

onMounted(() => {
  fetchRooms()
})

const rooms = ref([])
const currentPage = ref(1)
const pageSize = 5

const isPickerOpen = ref(false)
const activeField = ref(null)
const tempDate = ref(null)
const today = new Date().toISOString().split('T')[0]

const dateError = ref('')

const filteredRooms = computed(() => {
  if (filters.value.availableOnly)
    return rooms.value.filter(room => room.available === true)
  return rooms.value
})

const totalPages = computed(() => {
  return Math.max(1, Math.ceil(filteredRooms.value.length / pageSize))
})

const paginatedRooms = computed(() => {
  const start = (currentPage.value - 1) * pageSize
  return filteredRooms.value.slice(start, start + pageSize)
})

const nextPage = () => {
  if (currentPage.value < totalPages.value) currentPage.value++
}

const previousPage = () => {
  if (currentPage.value > 1) currentPage.value--
}

function openPicker(field) {
  activeField.value = field
  tempDate.value = filters.value[field] ?? today
  isPickerOpen.value = true
}

function applyDate() {
  filters.value[activeField.value] = tempDate.value?.split('T')[0] ?? null
  isPickerOpen.value = false
}

function onMinChange(v) {
  filters.value.minPrice = v ? Number(v) : null
}

function onMaxChange(v) {
  filters.value.maxPrice = v ? Number(v) : null
}

const priceError = computed(() => {
  const { minPrice, maxPrice } = filters.value
  if (minPrice != null && maxPrice != null && maxPrice < minPrice) {
    return 'Max must be ≥ Min'
  }
  return ''
})

async function applyFilters() {
  if (priceError.value) return
  dateError.value = ''

  const { checkIn, checkOut } = filters.value
  if (!checkIn || !checkOut) return

  const results = await Promise.all(
    rooms.value.map(room =>
      checkRoomAvailability(room.id, checkIn, checkOut).catch(e => e)
    )
  )

  const firstError = results.find(r => r instanceof Error)
  if (firstError) {
    try {
      const body = JSON.parse(firstError.message)
      const field = body.errors?.[0]
      dateError.value = field
        ? `${field.field}: ${field.message}`
        : 'Invalid date range'
    } catch {
      dateError.value = 'Invalid date range'
    }
    return
  }

  currentPage.value = 1
  rooms.value = rooms.value.map((room, i) => ({
    ...room,
    available: results[i].available,
  }))
}

async function fetchRooms() {
  try {
    const page = await getRooms(0, 20)
    rooms.value = page.content.map(r => ({
      id: r.id,
      name: r.title,
      price: r.pricePerNight,
      image: `/images/rooms/${r.id}.jpg`,
      description: r.extras.map(e => e.name).join(', '),
      available: null,
    }))
  } catch (e) {
    console.error('Error fetching rooms:', e)
  }
}
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
