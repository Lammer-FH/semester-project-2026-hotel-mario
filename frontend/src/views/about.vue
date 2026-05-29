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
// Import CSS
import '@/theme/hoteltheme.css';

import {
  IonContent,
  IonHeader,
  IonPage,
  IonTitle,
  IonToolbar
} from '@ionic/vue';

import { ref, onMounted, onUnmounted } from 'vue';

const slideIndex = ref(0);

const slides = [
  {
    image: '/images/img_placeholder1.jpg',
    caption: 'Caption Text'
  },
  {
    image: '/images/img_placeholder2.jpg',
    caption: 'Caption Two'
  },
  {
    image: '/images/img_placeholder3.jpg',
    caption: 'Caption Three'
  },
  {
    image: '/images/img_placeholder4.jpg',
    caption: 'Caption Four'
  }
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
