<template>
  <ion-page class="background">
    <ion-header :translucent="true">
      <ion-toolbar>
        <div class="nav">
          <ion-title>Boutique Hotel Technikum</ion-title>
          <router-link to="/">Home</router-link>
          <router-link to="/about">About Us</router-link>
          <router-link to="/rooms">Rooms</router-link>
        </div>
      </ion-toolbar>
    </ion-header>

    <ion-content :fullscreen="true">
      <div class="page-wrapper">

        <div class="container">

          <div class="info-box">
          
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

          </div>

        </div>
      </div>
    </ion-content>
    
    <ion-footer class="content-background">
      <ion-toolbar>
        <div class="footer-content"> 
          <p>© 2026 Boutique Hotel Technikum</p>
          <div>
            <router-link to="/home" class="btn">
              Home
            </router-link>

            <router-link to="/about" class="btn">
              About Us
            </router-link>

            <router-link to="/imprint" class="btn">
              Imprint
            </router-link>
          </div>
        </div>
      </ion-toolbar>
    </ion-footer>
  </ion-page>
</template>

<script setup lang="ts">
// Import CSS
import '@/theme/hoteltheme.css';

import {
  IonContent,
  IonHeader,
  IonPage,
  IonTitle,
  IonToolbar
} from '@ionic/vue';
</script>

<style scoped>
</style>
