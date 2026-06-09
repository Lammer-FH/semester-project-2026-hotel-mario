<template>
  <ion-page class="background">
    <ion-header :translucent="true">
      <ion-toolbar>
        <ion-title>Boutique Hotel Technikum</ion-title>
        <div class = "button-group margin-left-15">
          <ion-button router-link="/home"> Home </ion-button> 
          <ion-button router-link="/about"> About Us </ion-button> 
          <ion-button router-link="/room"> Rooms </ion-button>
        </div>
      </ion-toolbar>
    </ion-header>

    <ion-content :fullscreen="true">
      <div class="page-wrapper">

        <div class="container">

          <ion-card class="info-box">

            <h1>About Us</h1>
            <h2>Boutique Hotel Technikum</h2>

            <p>
              We are a family-owned company with a long tradition. We were founded last year by investment company xyz.
            </p>
            
            <!-- Slideshow -->
            <div class="slideshow-container">

              <div
                v-for="(slide, index) in slides"
                :key="index"
                class="mySlides fade"
                v-show="slideIndex === index"
              >
                <div class="numbertext">
                  {{ index + 1 }} / {{ slides.length }}
                </div>

                <img :src="slide.image" style="width:100%" />

                <div class="text">
                  {{ slide.caption }}
                </div>
              </div>

              <a class="prev" @click="plusSlides(-1)">❮</a>
              <a class="next" @click="plusSlides(1)">❯</a>
            </div>

            <!-- Dots -->
            <div style="text-align:center">
              <span
                v-for="(slide, index) in slides"
                :key="'dot-' + index"
                class="dot"
                :class="{ active: slideIndex === index }"
                @click="currentSlide(index)"
              >
              </span>
            </div>

            <h2>How can you reach us</h2>
            <table class="table-fullwidth">
              <tr>
                <th>Hotel name</th>
                <td>Boutique Hotel Technikum</td>
              </tr>
              <tr>
                <th>Street</th>
                <td>Höchstädtplatz 6</td>
              </tr>
              <tr>
                <th>City</th>
                <td>Vienna</td>
              </tr>
              <tr>
                <th>Postal Code</th>
                <td>1200</td>
              </tr>
              <tr>
                <th>Country</th>
                <td>Austria</td>
              </tr>
              <tr>
                <th>Latitude</th>
                <td>48.239298355714766</td>
              </tr>
              <tr>
                <th>Longitude</th>
                <td>16.377322480340275</td>
              </tr>
            </table>
            
            <h2>Directions</h2>
            <p>
              Take the U4 to Friedensbrücke, then walk 5 minutes north. By car, use the A22 and exit at Floridsdorf.
            </p>
          </ion-card>
        </div>
      </div>
    </ion-content>
    
    <Footer />
  </ion-page>
</template>

<script setup lang="ts">
// Import CSS
import '@/theme/hoteltheme.css';
import Footer from '@/components/molecules/Footer.vue';

import {
  IonContent,
  IonHeader,
  IonPage,
  IonTitle,
  IonToolbar,
  IonCard,
  IonButton
} from '@ionic/vue';

defineOptions({ name: 'AboutPage' });

import { ref, onMounted, onUnmounted } from 'vue';

const slideIndex = ref(0);

const slides = [
  { image: '/images/slide1.svg',   caption: 'Boutique Hotel Technikum' },
  { image: '/images/rooms/1.jpg',  caption: 'Elegantly furnished rooms with modern amenities' },
  { image: '/images/slide2.svg',   caption: 'Höchstädtplatz 6 · 1200 Vienna · U4 Friedensbrücke' },
  { image: '/images/slide3.svg',   caption: 'Continental breakfast available daily' },
];

function plusSlides(n: number) {
  slideIndex.value += n;

  if (slideIndex.value >= slides.length) {
    slideIndex.value = 0;
  }

  if (slideIndex.value < 0) {
    slideIndex.value = slides.length - 1;
  }
}

function currentSlide(n: number) {
  slideIndex.value = n;
}

let interval: ReturnType<typeof setInterval>;

onMounted(() => {
  interval = setInterval(() => {
    plusSlides(1);
  }, 10000);
});

onUnmounted(() => {
  clearInterval(interval);
});
</script>

<style scoped>
</style>
