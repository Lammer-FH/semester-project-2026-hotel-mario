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
  },
});