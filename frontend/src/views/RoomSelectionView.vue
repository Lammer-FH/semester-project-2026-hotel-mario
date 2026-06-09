
<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-title>Room Selection</ion-title>
        <div class="button-group" slot="end">
          <ion-button router-link="/home">Home</ion-button>
          <ion-button router-link="/about">About Us</ion-button>
        </div>
      </ion-toolbar>
    </ion-header>

    <ion-content>
      <div class="page-container">

        <FilterBar
          :filters="filterBarProps"
          :priceError="filterStore.priceError"
          @openDate="(f) => filterStore.openPicker(f)"
          @updatePersons="(v) => filterStore.persons = v"
          @updateMin="(v) => filterStore.minPrice = v ? Number(v) : null"
          @updateMax="(v) => filterStore.maxPrice = v ? Number(v) : null"
          @updateAvailable="(v) => filterStore.availableOnly = v"
          @apply="applyFilters"
        />

        <ion-text color="danger" v-if="filterStore.dateError" class="status-text">
          {{ filterStore.dateError }}
        </ion-text>

        <ion-text color="danger" v-if="roomStore.error" class="status-text">
          {{ roomStore.error }}
        </ion-text>

        <ion-text v-if="roomStore.loading" color="medium" class="status-text">
          Loading rooms...
        </ion-text>

        <ion-text v-else-if="filteredRooms.length === 0 && roomStore.rooms.length > 0" color="medium" class="empty-state">
          No rooms match your criteria for the selected dates.
        </ion-text>

        <RoomList v-else :rooms="paginatedRooms" @select="handleRoomSelect" />

        <div class="pagination">
          <div class="btn-group">
            <ion-button fill="outline" @click="previousPage" :disabled="currentPage === 1">← Previous</ion-button>
            <span class="page-label">{{ currentPage }} / {{ totalPages }}</span>
            <ion-button fill="outline" @click="nextPage" :disabled="currentPage === totalPages">Next →</ion-button>
          </div>
        </div>

      </div>

      <DatePickerModal
        :today="today"
        :isOpen="filterStore.pickerOpen"
        :checkIn="filterStore.checkIn"
        :checkOut="filterStore.checkOut"
        :initialField="filterStore.pickerInitialField"
        @apply="onPickerApply"
        @close="filterStore.closePicker"
      />
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import { ref, computed, watch, onMounted } from 'vue'
import { IonPage, IonContent, IonHeader, IonToolbar, IonTitle, IonButton, IonText, IonButtons, alertController } from '@ionic/vue'

import FilterBar from '@/components/organisms/FilterBar.vue'
import RoomList from '@/components/organisms/RoomList.vue'
import DatePickerModal from '@/components/molecules/DatePickerModal.vue'

import { Room, useRoomStore } from '@/stores/useRoomStore'
import { useFilterStore } from '@/stores/useFilterStore'
import { useRouter } from 'vue-router'

const router = useRouter();  
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
  return roomStore.rooms.filter(r => {
    if (filterStore.availableOnly && filterStore.datesSelected && r.available !== true) return false
    if (filterStore.minPrice != null && r.price < filterStore.minPrice) return false
    if (filterStore.maxPrice != null && r.price > filterStore.maxPrice) return false
    return true
  })
})

const totalPages = computed(() => Math.max(1, Math.ceil(filteredRooms.value.length / pageSize)))

const paginatedRooms = computed(() => {
  const start = (currentPage.value - 1) * pageSize
  return filteredRooms.value.slice(start, start + pageSize)
})

const nextPage = () => { if (currentPage.value < totalPages.value) currentPage.value++ }
const previousPage = () => { if (currentPage.value > 1) currentPage.value-- }

watch(totalPages, (newTotal) => {
  if (currentPage.value > newTotal) currentPage.value = 1
})

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

async function onPickerApply(checkIn: string, checkOut: string) {
  filterStore.applyPicker(checkIn, checkOut)
  await applyFilters()
}

const handleRoomSelect = async (room: Room) => {
  if (!filterStore.checkIn || !filterStore.checkOut) {
    const alert = await alertController.create({
      header: 'Dates not set',
      message: 'Please set Check-in & Check-out first before booking.',
      buttons: ['OK'],
    });
    await alert.present();
    return;
  }

  if (room.available === false) {
    const alert = await alertController.create({
      header: 'Unavailable',
      message: 'Room is not available for the selected dates.',
      buttons: ['OK'],
    });
    await alert.present();
    return;
  }

  router.push({
    name: 'Booking',
    params: { roomId: room.id},
  });
};
 /*
async function handleRoomClick(){
  const room = props.room
  if (room.available === null) {
    const alert = await alertController.create({
      header: 'Dates not set',
      message: `Please set Check-in & Check-out first, before trying to book a room.`,
      buttons: ['OK'],
    });
    return alert.present();
  }

  if (room.available === false) {
    const alert = await alertController.create({
      header: 'Unavailable',
      message: `Room is not availabe for the selected date. Please choose another room or try to book at another date.`,
      buttons: ['OK'],
    });
    return alert.present();
  }

  router.push({name: 'Booking', params: {roomId: room.id}})

  }

const props = defineProps<{ room: Room }>()
*/ 
onMounted(() => {
  roomStore.fetchRooms()
})
</script>

<style scoped>
.page-container {
  max-width: 1100px;
  margin: 0 auto;
  padding: 8px 0;
}

.button-group {
  display: flex;
}

.status-text {
  display: block;
  padding: 4px 16px;
}

.empty-state {
  display: block;
  padding: 24px 16px;
  text-align: center;
}

.pagination {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 16px;
}

.btn-group {
  display: inline-flex;
  align-items: stretch;
  border: 1px solid var(--ion-color-primary);
  border-radius: 8px;
  overflow: hidden;
}

.btn-group ion-button {
  --border-radius: 0;
  --box-shadow: none;
  margin: 0;
}

.page-label {
  display: flex;
  align-items: center;
  padding: 0 16px;
  font-size: 0.9em;
  color: var(--ion-color-primary);
  border-left: 1px solid var(--ion-color-primary);
  border-right: 1px solid var(--ion-color-primary);
  white-space: nowrap;
}
</style>
