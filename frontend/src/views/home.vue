<template>
  <ion-page class="background">
    <ion-header class="content-background">
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

        <!-- Hero -->
        <section class="hero">
          <div class="info-box">

            <h1>Boutique Hotel Technikum</h1>

            <p>Welcome to our Hotel Website!</p>

            <div class="button-group">
              <router-link to="/about" class="btn">
                About Us
              </router-link>

              <router-link to="/rooms" class="btn">
                Rooms
              </router-link>
            </div>

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

            <p>
              Book now to get the room you deserve.
            </p>

            <router-link to="/rooms" class="btn">
              Find the perfect room now!
            </router-link>

          </div>
        </section>

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
