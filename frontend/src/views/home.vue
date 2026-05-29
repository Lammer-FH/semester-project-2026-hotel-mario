<template>
  <ion-page class="background">
    <ion-header class="content-background">
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

          <ion-card class="info-box">
            <h1 class="hero">Boutique Hotel Technikum</h1>

            <p class="intro">
              Welcome to the Boutique Hotel Technikum — a charming retreat in the heart of Vienna's
              20th district. We offer five individually designed rooms ranging from comfortable
              doubles to our flagship Executive Suite, all within walking distance of the U4
              Friedensbrücke station.
            </p>

            <div class="highlights">
              <div class="highlight">
                <ion-icon name="location" />
                <span>Höchstädtplatz 6, 1200 Vienna</span>
              </div>
              <div class="highlight">
                <ion-icon name="bed" />
                <span>5 individually designed rooms</span>
              </div>
              <div class="highlight">
                <ion-icon name="wifi" />
                <span>Free Wi-Fi in all rooms</span>
              </div>
              <div class="highlight">
                <ion-icon name="restaurant" />
                <span>Continental breakfast available</span>
              </div>
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

            <div class ="button-group">
              <ion-button router-link="/room"> Find the perfect room now! </ion-button> 
            </div>

          </ion-card>
        
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

import {
  IonContent,
  IonHeader,
  IonPage,
  IonTitle,
  IonToolbar,
  IonCard,
  IonButton,
  IonFooter,
  IonIcon
} from '@ionic/vue';

import { ref, onMounted, onUnmounted } from 'vue';

defineOptions({ name: 'HotelHome' });

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
.intro {
  font-size: 1em;
  line-height: 1.6;
  margin-bottom: 16px;
}

.highlights {
  display: flex;
  flex-direction: column;
  gap: 8px;
  margin-bottom: 16px;
}

.highlight {
  display: flex;
  align-items: center;
  gap: 8px;
  font-size: 0.95em;
}

.highlight ion-icon {
  font-size: 1.2em;
  color: var(--ion-color-primary);
  flex-shrink: 0;
}
</style>
