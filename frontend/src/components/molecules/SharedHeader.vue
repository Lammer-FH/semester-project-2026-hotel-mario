<template>
  <ion-header class="content-background">
      <ion-toolbar>
        <ion-title>Boutique Hotel Technikum</ion-title>
        <div class="button-group margin-left-15">
          <ion-button router-link="/home"> Home </ion-button>
          <ion-button router-link="/about"> About Us </ion-button>
          <ion-button router-link="/room"> Rooms </ion-button>
        </div>
      </ion-toolbar>
    </ion-header>
</template>

<script setup lang="ts">
import { IonHeader, IonToolbar, IonTitle, IonButton } from '@ionic/vue';
// Import CSS
import '@/theme/hoteltheme.css';
</script>

<style scoped>
</style>
