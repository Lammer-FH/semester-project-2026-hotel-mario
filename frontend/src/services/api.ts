const BASE: string = import.meta.env.VITE_API_URL ?? '/api'

export interface BookingDto{
  roomId: number,
  firstName: string,
  lastName: string,
  email: string,
  emailConfirmation: string
  checkIn: string,
  checkOut: string,
  breakfast: boolean
}

export interface BookingResponseDto{
  id: number,
  roomId: number,
  firstName: string,
  lastName: string,
  email: string,
  emailConfirmation: string
  checkIn: string,
  checkOut: string,
  breakfast: boolean

  room: RoomDto
  hotel: HotelDto

}

export interface HotelDto{
  name: string,
  address: AddressDto
  contact: ContactDto,
  directions: string
}

export interface ContactDto{
  phone: string,
  email: string
}

export interface AddressDto{
  street: string,
  city: string,
  postalCode: string,
  country: string,
  latitude: number,
  longitude: number,
}

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

async function postRequest<T>(
  path: string, 
  body: object
): Promise<T> {
  const response = await fetch(`${BASE}${path}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(body),
  })

  if (!response.ok) {
    const errorText = await response.text()
    let errorMessage = errorText || 'Post failed'
    try {
      const errorBody = JSON.parse(errorText) as ValidationErrorResponseDto
      if (errorBody.errors?.length) {
        errorMessage = errorBody.errors.map(e => `${e.field}: ${e.message}`).join(', ')
      } else if (errorBody.message) {
        errorMessage = errorBody.message
      }
    } catch { /* not JSON */ }
    const error = Object.assign(new Error(errorMessage), {
      status: response.status,
      statusText: response.statusText,
    })
    throw error
  }

  // Handle empty responses safely
  const text = await response.text()
  return text ? JSON.parse(text) as T : null as T
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

export function getRoom(roomId: number): Promise<RoomDto>
{
  return request(`/v1/rooms/${roomId}`)
}

export function bookRoom(bookingDto: BookingDto): Promise<BookingResponseDto>{
  return postRequest(`/v1/bookings`, bookingDto)
}

/*
function deserializeBookRoom(response: string){
  const dto: BookingResponseDto = BookingResponseDto {
                id = String(response.id),
                roomId = Number(response.roomId),
                firstName = response.firstName,
                lastName = response.lastName,
                email = response.email,
                emailConfirmation = response.emailConfirmation,
                checkIn = response.checkIn,
                checkOut = response.checkOut,
                breakfast = response.breakfast,

                address = AddressDto(

                )
}
*/
