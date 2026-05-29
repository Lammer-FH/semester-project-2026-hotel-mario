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

        <!-- Footer -->
        <footer>
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
        </footer>

      </div>
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import {
  IonContent,
  IonHeader,
  IonPage,
  IonTitle,
  IonToolbar
} from '@ionic/vue';
</script>

<style scoped>

.background {
  color: #000000;
}

.content-background {
  background: rgb(41, 36, 36);
}

.page-wrapper {
  padding: 16px;
}

.nav {
  display: flex;
  gap: 16px;
  margin: 15px;
}

.hero h1 {
  text-align: center;
  color: #d40b0b;
}

.info-box {
  background: rgb(41, 36, 36);
  padding: 5px 30px 30px 30px;
  margin-bottom: 20px;
  border-radius: 16px;
  box-shadow: 0 5px 20px rgba(31, 30, 29, 0.1);
  line-height: 1.8;
  margin-top: 30px;
}

.button-group {
  margin-bottom: 20px;
}

.btn {
  margin-right: 15px;
  text-decoration: none;
  color: var(--ion-color-primary);
}

/* Slideshow */

* {
  box-sizing: border-box;
}

.mySlides {
  display: block;
}

img {
  vertical-align: middle;
  border-radius: 12px;
}

.slideshow-container {
  max-width: 1000px;
  position: relative;
  margin: auto;
  margin-top: 20px;
}

.prev,
.next {
  cursor: pointer;
  position: absolute;
  top: 50%;
  padding: 16px;
  margin-top: -22px;
  color: rgb(0, 0, 0);
  font-weight: bold;
  font-size: 18px;
  transition: 0.6s ease;
  user-select: none;
}

.next {
  right: 0;
}

.prev:hover,
.next:hover {
  background-color: rgba(255, 255, 255, 0.8);
}

.text {
  color: #ffffff;
  padding: 8px 12px;
  position: absolute;
  bottom: 8px;
  width: 100%;
  text-align: center;
}

.numbertext {
  color: #ffffff;
  padding: 8px 12px;
  position: absolute;
  top: 0;
}

.dot {
  cursor: pointer;
  height: 15px;
  width: 15px;
  margin: 10px 2px;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
}

.active,
.dot:hover {
  background-color: #717171;
}

.fade {
  animation-name: fade;
  animation-duration: 1.5s;
}

footer {
  background: rgb(41, 36, 36);
  color: white;
  padding: 30px;
  text-align: center;
  margin-top: 60px;
  border-radius: 16px;
}

@keyframes fade {
  from {
    opacity: .4
  }

  to {
    opacity: 1
  }
}
</style>
