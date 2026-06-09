import { BookingDto, BookingResponseDto, bookRoom } from '@/services/api';
import { defineStore } from 'pinia';

export const useBookingStore = defineStore('booking', {
  state: () => ({
    roomId: 0,
    firstName: '',
    lastName: '',
    email: '',
    emailConfirmation: '',
    checkIn: '',
    checkOut: '',
    breakfast: false,
    error: false,
    errorMessage: '',
    errorStatus: 0,
  }),

  actions: {
    reset() {
      this.roomId = 0;
      this.firstName = '';
      this.lastName = '';
      this.email = '';
      this.emailConfirmation = '';
      this.checkIn = '';
      this.checkOut = '';
      this.breakfast = false;
    },
    async sendBooking(): Promise<BookingResponseDto | null> {
        const dto: BookingDto = {
            roomId: this.roomId,
            firstName: this.firstName,
            lastName: this.lastName,
            email: this.email,
            emailConfirmation: this.emailConfirmation,
            checkIn: this.checkIn,
            checkOut: this.checkOut,
            breakfast: this.breakfast,
        };
        let results;
        try {
            results = await bookRoom(dto);
        }
        catch (e: any) {
            console.log(e)
            this.error = true;
            this.errorMessage = e.message;
            this.errorStatus = e.status ?? 0;
            results = null;
        }
        return results
    },
  },
});

