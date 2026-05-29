import { defineStore } from 'pinia'
import { ref } from 'vue'
import { getRooms, checkRoomAvailability } from '@/services/api'

export interface Room {
  id: number
  name: string
  price: number
  image: string
  description: string
  available: boolean | null
}

export const useRoomStore = defineStore('rooms', () => {
  const rooms = ref<Room[]>([])
  const selectedRoom = ref<Room | null>(null)
  const loading = ref(false)
  const error = ref<string | null>(null)

  async function fetchRooms() {
    loading.value = true
    error.value = null
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
    rooms.value = rooms.value.map(r => ({ ...r, available: null }))
  }

  function selectRoom(room: Room) {
    selectedRoom.value = room
  }

  return { rooms, selectedRoom, loading, error, fetchRooms, checkAvailability, resetAvailability, selectRoom }
})
