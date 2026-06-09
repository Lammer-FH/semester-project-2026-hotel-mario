<template>
  <ion-footer class="content-background">
    <ion-toolbar>
      <div class="footer-content">
        <p>© 2026 Boutique Hotel Technikum</p>

        <div class="button-group">
          <ion-button router-link="/home">Home</ion-button>
          <ion-button router-link="/about">About Us</ion-button>
          <ion-button router-link="/imprint">Imprint</ion-button>
        </div>
      </div>
    </ion-toolbar>
  </ion-footer>
</template>

<script setup lang="ts">
import { IonFooter, IonToolbar, IonButton } from '@ionic/vue';
import '@/theme/hoteltheme.css';
</script>

<style scoped>
</style>
