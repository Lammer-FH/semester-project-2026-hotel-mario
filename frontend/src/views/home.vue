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
  background-color: #717171;
  border-radius: 50%;
  display: inline-block;
}

.active,
.dot:hover {
  background-color: #bbb;
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
