<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-title>Room Selection</ion-title>
      </ion-toolbar>
    </ion-header>

    <ion-content class="ion-padding">

      <!-- 🔹 Booking Filter Section -->
      <div class="filter-container">
        <h2>Room selection</h2>
        <pre>{{ filters }}</pre>

        <ion-grid>
          <ion-row>
            <ion-col size="6">
              <ion-select
                :value="filters.persons"
                label="Persons"
                @ionChange="(e) => {
                  console.log('Persons changed:', e.detail.value)
                  filters.persons = e.detail.value
                }"
              >
                <ion-select-option :value="1">1</ion-select-option>
                <ion-select-option :value="2">2</ion-select-option>
                <ion-select-option :value="3">3</ion-select-option>
                <ion-select-option :value="4">4</ion-select-option>
              </ion-select>
            </ion-col>


            <ion-col size="6" class="availability-toggle">
              <ion-label>Only available</ion-label>
              <ion-checkbox v-model="filters.availableOnly" />
            </ion-col>
          </ion-row>

          <ion-row>
            <ion-col size="6">
              <ion-item button @click="openPicker('checkIn')">
                <ion-label>
                  {{ filters.checkIn ? formatDate(filters.checkIn) : 'Check-in' }}
                </ion-label>
              </ion-item>
            </ion-col>

            <ion-col size="6">
              <ion-item button @click="openPicker('checkOut')">
                <ion-label>
                  {{ filters.checkOut ? formatDate(filters.checkOut) : 'Check-out' }}
                </ion-label>
              </ion-item>
            </ion-col>
          </ion-row>

          <ion-modal :is-open="isPickerOpen" @didDismiss="closePicker">
            <ion-content>
              <ion-datetime
                :min="activeField === 'checkOut' ? filters.checkIn : undefined"
                :value="tempDate"
                @ionChange="(e) => tempDate = e.detail.value"
                presentation="date"
              ></ion-datetime>

              <ion-button expand="block" @click="applyDate">
                Apply
              </ion-button>
            </ion-content>
          </ion-modal>

          <ion-row>
            <ion-col size="6">
              <ion-input
                :value="filters.minPrice"
                type="number"
                placeholder="Min €"
                :max="filters.maxPrice || undefined"
                @ionInput="(e) => {
                        filters.minPrice = e.detail.value ? Number(e.detail.value) : null
                        validatePriceRange()
                }"
              />
            </ion-col>

            <ion-col size="6">
              <ion-input
                :value="filters.maxPrice"
                type="number"
                placeholder="Max €"
                :min="filters.minPrice || undefined"
                @ionInput="(e) => {
                        filters.maxPrice = e.detail.value ? Number(e.detail.value) : null
                        validatePriceRange()
                }"
              />


            </ion-col>
            <ion-text color="danger" v-if="priceError">
                {{ priceError }}
              </ion-text>
          </ion-row>
        </ion-grid>

        <ion-button expand="block" @click="applyFilters">
          Apply Filters
        </ion-button>

        <ion-alert
          :is-open="showErrorAlert"
          header="Invalid Input"
          :message="priceError"
          :buttons="['OK']"
          @didDismiss="showErrorAlert = false"
        />

      </div>

      <!-- 🔹 Room List -->
      <div class="room-list">
        <ion-card v-for="room in rooms" :key="room.id">
         <ion-card-header>
          <img :src="room.image" alt="Room image">
            <ion-card-title>{{ room.name }}</ion-card-title>
          </ion-card-header>

          <ion-card-content>
            <p>Price: {{ room.price }} €</p>
            <p>Availability: {{ room.available ? 'Available' : 'Not available' }}</p>
          </ion-card-content>
        </ion-card>
      </div>

    </ion-content>
  </ion-page>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import {
  IonPage,
  IonContent,
  IonRow,
  IonCol,
  IonDatetime,
  IonModal,
  IonItem,
  IonLabel,
  IonButton,
  IonSelect,
  IonSelectOption,
  IonCheckbox,
  IonInput,
  IonGrid,
  IonCard,
  IonCardContent,
  IonCardHeader,
  IonCardTitle,
  IonText,
  IonAlert

} from '@ionic/vue'


// 🔹 Filters state
const filters = ref({
  persons: 2,
  availableOnly: false,
  checkIn: null,
  checkOut: null,
  minPrice: null,
  maxPrice: null
})

const isPickerOpen = ref(false)
const activeField = ref(null)
const tempDate = ref(null)
const priceError = ref('')
const showErrorAlert = ref(false)

function applyFilters() {
  if (priceError.value) {
    console.warn('Invalid price range')
    showErrorAlert.value = true
    return
  }

  fetchRooms()
}


function openPicker(field) {

  console.log('Opening picker for:', field)
  console.log('Current value:', filters.value[field])

  activeField.value = field
  tempDate.value = filters.value[field]
  isPickerOpen.value = true

}

function closePicker() {
  isPickerOpen.value = false
}

function applyDate() {
  filters.value[activeField.value] = tempDate.value
  closePicker()
}

function formatDate(date) {
  return new Date(date).toLocaleDateString()
}


function validatePriceRange() {
  const { minPrice, maxPrice } = filters.value

  if (
    minPrice != null &&
    maxPrice != null &&
    Number(minPrice) > Number(maxPrice)
  ) {
    priceError.value = 'Min price must be less than or equal to max price'
  } else {
    priceError.value = ''
  }
}


// 🔹 Rooms state
const rooms = ref([])

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
        image: 'https://via.placeholder.com/400x200'
      },
      {
        id: 2,
        name: 'Deluxe Suite',
        price: 180,
        available: true,
        image: 'https://via.placeholder.com/400x200'
      },
      {
        id: 3,
        name: 'Budget Room',
        price: 80,
        available: false,
        image: 'https://via.placeholder.com/400x200'
      }
    ]

    rooms.value = data

  } catch (error) {
    console.error('Error fetching rooms:', error)
  }
}

// load initial data
onMounted(fetchRooms)
</script>

<style scoped>
.filter-container {
  background: #3a3737;
  padding: 16px;
  border-radius: 16px;
  margin-bottom: 20px;
}

.availability-toggle {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.room-list {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

ion-card img {
  width: 100%;
  height: 180px;
  object-fit: cover;
}
</style>
