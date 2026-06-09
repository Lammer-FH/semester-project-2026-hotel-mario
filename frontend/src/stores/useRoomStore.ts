import { defineStore } from 'pinia'
import { ref } from 'vue'
import { getRooms, checkRoomAvailability } from '@/services/api'

export interface RoomExtra {
  name: string
  icon: string
}

export interface Room {
  id: number
  name: string
  price: number
  image: string
  description: string
  extras: RoomExtra[]
  available: boolean | null
}

export const PAGE_SIZE = 5

export const useRoomStore = defineStore('rooms', () => {
  const rooms = ref<Room[]>([])
  const selectedRoom = ref<Room | null>(null)
  const loading = ref(false)
  const error = ref<string | null>(null)
  const totalPages = ref(1)
  const totalElements = ref(0)

  async function fetchRooms(page = 1) {
    loading.value = true
    error.value = null
    try {
      const data = await getRooms(page - 1, PAGE_SIZE)
      rooms.value = data.content.map(r => ({
        id: r.id,
        name: r.title,
        price: r.pricePerNight,
        image: `/images/rooms/${r.id}.jpg`,
        description: r.description,
        extras: r.extras.map(e => ({ name: e.name, icon: e.icon })),
        available: null,
      }))
      totalPages.value = data.totalPages
      totalElements.value = data.totalElements
    } catch {
      error.value = 'Failed to load rooms. Please try again.'
    } finally {
      loading.value = false
    }
  }

  async function checkAvailability(checkIn: string, checkOut: string): Promise<string | null> {
    const results = await Promise.all(
      rooms.value.map(room =>
        checkRoomAvailability(room.id, checkIn, checkOut).catch(e => e)
      )
    )

    const firstError = results.find(r => r instanceof Error)
    if (firstError) {
      try {
        const body = JSON.parse((firstError as Error).message)
        const field = body.errors?.[0]
        return field ? `${field.field}: ${field.message}` : 'Invalid date range'
      } catch {
        return 'Invalid date range'
      }
    }

    rooms.value = rooms.value.map((room, i) => ({
      ...room,
      available: (results[i] as { available: boolean }).available,
    }))
    return null
  }

  function resetAvailability() {
    rooms.value = rooms.value.map(r => ({ ...r, available: null as boolean | null }))
  }

  function selectRoom(room: Room) {
    selectedRoom.value = room
  }

  return { rooms, selectedRoom, loading, error, totalPages, totalElements, fetchRooms, checkAvailability, resetAvailability, selectRoom }
})
