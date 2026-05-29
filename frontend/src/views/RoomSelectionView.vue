
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
        @apply="applyFilters"
      />

      <RoomList :rooms="paginatedRooms" />

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
import { IonPage, IonContent, IonHeader, IonToolbar, IonTitle, IonButton} from '@ionic/vue'

import FilterBar from '@/components/FilterBar.vue'
import RoomList from '@/components/RoomList.vue'
import DatePickerModal from '@/components/DatePickerModal.vue'

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

const filteredRooms = computed(() => {
  if (filters.value.availableOnly)
    return rooms.value.filter(room => room.available)
  else
    return rooms.value
})

const totalPages = computed(() => {
  return Math.ceil(filteredRooms.value.length / pageSize)
})

const paginatedRooms = computed(() => {
  const start = (currentPage.value - 1) * pageSize
  const end = start + pageSize

  return filteredRooms.value.slice(start, end)
})

const nextPage = () => {
  if (currentPage.value < totalPages.value) {
    currentPage.value++
  }
}

const previousPage = () => {
  if (currentPage.value > 1) {
    currentPage.value--
  }
}


function openPicker(field) {
  activeField.value = field
  tempDate.value =
    filters.value[field] ??
    new Date().toISOString().split('T')[0]

  isPickerOpen.value = true
}

function applyDate() {
  filters.value[activeField.value] = tempDate.value
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

function applyFilters() {
  if (priceError.value) return
  console.log('Apply filters:', filters.value)
}
// 🔹 Placeholder API call
async function fetchRooms() {
  try {
    // TODO: Replace with real API call
    // Example:
    // const res = await fetch('/api/rooms?filters=...');
    // const data = await res.json();

    const data = [
      {
        id: 1,
        name: 'Luxury Suite XXL',
        price: 300,
        available: true,
        //image: 'https://via.placeholder.com/400x200',
        descriptions: "Spacious suite with king-size bed, ocean view, and private balcony."
      },
      {
        id: 2,
        name: 'Deluxe Suite',
        price: 180,
        available: true,
        //image: 'https://via.placeholder.com/400x200',
        descriptions: "Just Deluxe."
      },
      {
        id: 3,
        name: 'Budget Room',
        price: 80,
        available: false,
        //image: 'https://via.placeholder.com/400x200',
        descriptions: "You should rather work, than going on vacation."
      },
      {
        id: 4,
        name: 'Budget Room2',
        price: 80,
        available: false,
        //image: 'https://via.placeholder.com/400x200',
        descriptions: "You should rather work, than going on vacation."
      },
      {
        id: 5,
        name: 'Budget Room3',
        price: 80,
        available: false,
        //image: 'https://via.placeholder.com/400x200',
        descriptions: "You should rather work, than going on vacation."
      },
      {
        id: 6,
        name: 'Budget Room3',
        price: 80,
        available: false,
        //image: 'https://via.placeholder.com/400x200',
        descriptions: "You should rather work, than going on vacation."
      }
    ]

    rooms.value = data

    rooms.value = data.map(room => ({
        ...room,
        image: `/images/rooms/${room.id}.jpg`
      }))


  } catch (error) {
    console.error('Error fetching rooms:', error)
  }
}


</script>

<style scoped>
.links {
  padding: 12px 20px;
  display: inline-block;
}
</style>

