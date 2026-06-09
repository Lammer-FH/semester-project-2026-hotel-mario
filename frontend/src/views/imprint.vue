<template>
  <ion-page class="background">
    <Header />

    <ion-content :fullscreen="true">
      <div class="page-wrapper">

        <div class="container">

          <ion-card class="info-box">
          
            <h1>Imprint</h1>

            <h2>Company Information</h2>

            <p>
              <strong>Company Name:</strong><br>
              Boutique Hotel Technikum
            </p>

            <p>
              <strong>Location of the business license / address:</strong><br>
              Boutique Hotel Technikum<br>
              Höchstädtplatz 6<br>
              1200 Vienna, Austria
            </p>

            <p>
              <strong>Business Purpose:</strong><br>
              Hotel and Restaurant Industry<br>
              Rental of hotel rooms and suites
            </p>

            <h2>Contact Information</h2>

            <p>
              <strong>Phone Number:</strong><br>
              +43 1 333 40 777
            </p>

            <p>
              <strong>E-Mail Address:</strong><br>
              info@hotel-technikum.at
            </p>

            <h2>Commercial Register & UID</h2>

            <p>
              <strong>Commercial Register Number:</strong><br>
              FN 456789x
            </p>

            <p>
              <strong>UID-Nummber:</strong><br>
              ATU12345678
            </p>

            <h2>Memberships</h2>

            <p>
              Member of the Wirtschaftskammer Österreich (WKÖ)<br>
              Fachgruppe Hotellerie Wien
            </p>

            <h2>Legislation</h2>

            <p>
              Trade Regulation Act (GewO):<br>
              <a href="https://www.ris.bka.gv.at" target="_blank">
                www.ris.bka.gv.at
              </a>
            </p>

            <h2>Regulatory Authority</h2>

            <p>
              Vienna District Office
            </p>

            <h2>Job title</h2>

            <p>
              Hotel and Restaurant Industry
            </p>

            <h2>Awarding Country</h2>

            <p>
              Austria
            </p>

            <h2>Editorial line</h2>

            <p>
              This website is designed to showcase the Boutique Hotel Technikum, provide information about room rates, and facilitate online reservation for hotel stays.
            </p>

          </ion-card>

        </div>
      </div>
    </ion-content>
    
    <ion-footer class="content-background">
      <ion-toolbar>
        <div class="footer-content"> 
          <p>© 2026 Boutique Hotel Technikum</p>
          <div class="button-group">
            <ion-button router-link="/home"> Home </ion-button> 
            <ion-button router-link="/about"> About Us </ion-button> 
            <ion-button router-link="/imprint"> Imprint </ion-button>
          </div>
        </div>
      </ion-toolbar>
    </ion-footer>
  </ion-page>
</template>

<script setup lang="ts">
// Import CSS
import '@/theme/hoteltheme.css';
import Header from '@/components/molecules/SharedHeader.vue';

import {
  IonContent,
  IonHeader,
  IonPage,
  IonTitle,
  IonToolbar,
  IonCard,
  IonButton
} from '@ionic/vue';

defineOptions({ name: 'ImprintPage' });
</script>

<style scoped>
</style>
