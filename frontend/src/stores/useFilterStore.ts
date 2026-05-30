import { defineStore } from 'pinia'
import { ref, computed } from 'vue'

export const useFilterStore = defineStore('filters', () => {
  // Filter form state
  const checkIn = ref<string | null>(null)
  const checkOut = ref<string | null>(null)
  const persons = ref(2)
  const minPrice = ref<number | null>(null)
  const maxPrice = ref<number | null>(null)
  const availableOnly = ref(false)
  const dateError = ref('')

  // Date picker modal state
  const pickerOpen = ref(false)
  const pickerField = ref<'checkIn' | 'checkOut' | null>(null)
  const pickerTemp = ref<string | null>(null)

  const priceError = computed(() => {
    if (minPrice.value != null && minPrice.value < 0) return 'Price must be ≥ 0'
    if (maxPrice.value != null && maxPrice.value < 0) return 'Price must be ≥ 0'
    if (minPrice.value != null && maxPrice.value != null && maxPrice.value < minPrice.value) {
      return 'Max must be ≥ Min'
    }
    return ''
  })

  const datesSelected = computed(() => !!checkIn.value && !!checkOut.value)

  function setDate(field: 'checkIn' | 'checkOut', value: string | null) {
    if (field === 'checkIn') checkIn.value = value
    else checkOut.value = value
  }

  function openPicker(field: 'checkIn' | 'checkOut', today: string) {
    pickerField.value = field
    pickerTemp.value = (field === 'checkIn' ? checkIn.value : checkOut.value) ?? today
    pickerOpen.value = true
  }

  function applyPicker() {
    if (pickerField.value) {
      setDate(pickerField.value, pickerTemp.value?.split('T')[0] ?? null)
    }
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
    checkIn, checkOut, persons, minPrice, maxPrice, availableOnly, dateError,
    pickerOpen, pickerField, pickerTemp,
    priceError, datesSelected,
    setDate, openPicker, applyPicker, closePicker,
    clearDateError, setDateError,
  }
})
