<template>
  <ion-page class="background">
    <Header />

    <ion-content :fullscreen="true" class="home-content">
      <div class="page-wrapper">
        <ion-card class="info-box">
          <h1 class="hero">Boutique Hotel Technikum</h1>

          <div class="card-grid">
            <!-- Left column: text -->
            <div class="card-left">
              <p class="intro">
                Welcome to the Boutique Hotel Technikum — a charming retreat in the heart of Vienna's
                20th district. Five individually designed rooms, from comfortable doubles to our
                flagship Executive Suite, just minutes from the U4 Friedensbrücke station.
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

              <div class="button-group cta">
                <ion-button router-link="/room" expand="block">
                  Find the perfect room
                </ion-button>
              </div>
            </div>

            <!-- Right column: slideshow -->
            <div class="card-right">
              <div class="slideshow-container">
                <div
                  v-for="(slide, index) in slides"
                  :key="index"
                  class="mySlides fade"
                  v-show="slideIndex === index"
                >
                  <div class="numbertext">{{ index + 1 }} / {{ slides.length }}</div>
                  <img :src="slide.image" :alt="slide.caption" class="slide-img" />
                  <div class="text">{{ slide.caption }}</div>
                </div>

                <a class="prev" @click="plusSlides(-1)">❮</a>
                <a class="next" @click="plusSlides(1)">❯</a>
              </div>

              <div class="dots">
                <span
                  v-for="(slide, index) in slides"
                  :key="'dot-' + index"
                  class="dot"
                  :class="{ active: slideIndex === index }"
                  @click="currentSlide(index)"
                />
              </div>
            </div>
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
import '@/theme/hoteltheme.css';
import Header from '@/components/molecules/SharedHeader.vue';

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
  { image: '/images/rooms/1.jpg',  caption: 'Elegantly furnished rooms with modern amenities' },
  { image: '/images/slide2.svg',   caption: 'Höchstädtplatz 6 · 1200 Vienna · U4 Friedensbrücke' },
  { image: '/images/slide3.svg',   caption: 'Continental breakfast available daily' },
];

function plusSlides(n: number) {
  slideIndex.value = (slideIndex.value + n + slides.length) % slides.length;
}

function currentSlide(n: number) {
  slideIndex.value = n;
}

let interval: ReturnType<typeof setInterval>;
onMounted(() => { interval = setInterval(() => plusSlides(1), 10000); });
onUnmounted(() => { clearInterval(interval); });
</script>

<style scoped>
/* ── Mobile-first base ── */
.page-wrapper {
  padding: 8px;
  box-sizing: border-box;
}

.info-box {
  margin: 0;
}

.hero {
  font-size: 1.4em;
  margin: 8px 0 12px;
  text-align: center;
}

.card-grid {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.intro {
  font-size: 0.95em;
  line-height: 1.6;
  margin-bottom: 12px;
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
  font-size: 0.9em;
}

.highlight ion-icon {
  font-size: 1.1em;
  color: var(--ion-color-primary);
  flex-shrink: 0;
}

.cta {
  margin-top: 8px;
}

.slideshow-container {
  position: relative;
  width: 100%;
  overflow: hidden;
  border-radius: 8px;
}

.slide-img {
  width: 100%;
  height: 220px;
  object-fit: cover;
  display: block;
}

.dots {
  text-align: center;
  margin-top: 6px;
}

/* ── Desktop layout (≥ 768px): side-by-side, no scroll ── */
@media (min-width: 768px) {
  .home-content::part(scroll) {
    overflow: hidden;
  }

  .page-wrapper {
    height: 100%;
    display: flex;
    align-items: center;
    padding: 16px;
  }

  .info-box {
    width: 100%;
    max-width: 1100px;
    margin: 0 auto;
  }

  .hero {
    font-size: 1.8em;
    margin-bottom: 16px;
  }

  .card-grid {
    flex-direction: row;
    align-items: center;
    gap: 32px;
  }

  .card-left {
    flex: 1;
    min-width: 0;
  }

  .card-right {
    flex: 1;
    min-width: 0;
  }

  .slide-img {
    height: 300px;
  }

  .intro {
    font-size: 0.95em;
  }
}
</style>
