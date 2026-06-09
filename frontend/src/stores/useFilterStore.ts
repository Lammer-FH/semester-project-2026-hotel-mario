import { defineStore } from 'pinia'
import { ref, computed } from 'vue'

export const useFilterStore = defineStore('filters', () => {
  // Filter form state
  const checkIn = ref<string | null>(null)
  const checkOut = ref<string | null>(null)
  const minPrice = ref<number | null>(null)
  const maxPrice = ref<number | null>(null)
  const availableOnly = ref(false)
  const dateError = ref('')

  // Date picker modal state
  const pickerOpen = ref(false)
  const pickerInitialField = ref<'checkIn' | 'checkOut'>('checkIn')

  const priceError = computed(() => {
    if (minPrice.value != null && minPrice.value < 0) return 'Price must be ≥ 0'
    if (maxPrice.value != null && maxPrice.value < 0) return 'Price must be ≥ 0'
    if (minPrice.value != null && maxPrice.value != null && maxPrice.value < minPrice.value) {
      return 'Max must be ≥ Min'
    }
    return ''
  })

  const datesSelected = computed(() => !!checkIn.value && !!checkOut.value)

  function openPicker(field: 'checkIn' | 'checkOut' = 'checkIn') {
    pickerInitialField.value = field
    pickerOpen.value = true
  }

  function applyPicker(newCheckIn: string, newCheckOut: string) {
    checkIn.value = newCheckIn
    checkOut.value = newCheckOut
    pickerOpen.value = false
  }

  function closePicker() {
    pickerOpen.value = false
  }

  function clearDateError() {
    dateError.value = ''
  }

  function setDateError(msg: string) {
    dateError.value = msg
  }

  return {
    checkIn, checkOut, minPrice, maxPrice, availableOnly, dateError,
    pickerOpen, pickerInitialField,
    priceError, datesSelected,
    openPicker, applyPicker, closePicker,
    clearDateError, setDateError,
  }
})
