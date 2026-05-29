const BASE = import.meta.env.VITE_API_URL as string

export interface ExtraDto {
  id: number
  name: string
  icon: string
}

export interface RoomDto {
  id: number
  title: string
  description: string
  pricePerNight: number
  extras: ExtraDto[]
}

export interface RoomPageDto {
  content: RoomDto[]
  totalElements: number
  totalPages: number
  page: number
  size: number
}

export interface AvailabilityResponseDto {
  roomId: number
  checkIn: string
  checkOut: string
  available: boolean
}

export interface ValidationErrorResponseDto {
  status: number
  message: string
  errors: Array<{ field: string; message: string }>
}

async function request<T>(path: string): Promise<T> {
  const res = await fetch(`${BASE}${path}`)
  if (!res.ok) {
    const err = Object.assign(new Error(await res.text()), { status: res.status })
    throw err
  }
  return res.json() as Promise<T>
}

export function getRooms(page = 0, size = 20): Promise<RoomPageDto> {
  return request(`/v1/rooms?page=${page}&size=${size}`)
}

export function checkRoomAvailability(
  roomId: number,
  checkIn: string,
  checkOut: string
): Promise<AvailabilityResponseDto> {
  return request(`/v1/rooms/${roomId}/availability?checkIn=${checkIn}&checkOut=${checkOut}`)
}
