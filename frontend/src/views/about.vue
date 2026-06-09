<template>
  <ion-page class="background">
    <Header />

    <ion-content :fullscreen="true">
      <div class="page-wrapper">
        <div class="container">
          <ion-card class="info-box">

            <h1>About Us</h1>
            <h2>Boutique Hotel Technikum</h2>

            <p>
              We are a family-owned company with a long tradition. We were founded last year by investment company xyz.
            </p>

            <ImageSlider :slides="slides" :autoplay-ms="10000" />

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
import '@/theme/hoteltheme.css'
import Header from '@/components/molecules/SharedHeader.vue'
import Footer from '@/components/molecules/SharedFooter.vue'
import ImageSlider from '@/components/molecules/ImageSlider.vue'
import { IonContent, IonPage, IonCard } from '@ionic/vue'

defineOptions({ name: 'AboutPage' })

const slides = [
  { image: '/images/slide1.svg',   caption: 'Boutique Hotel Technikum' },
  { image: '/images/rooms/1.jpg',  caption: 'Elegantly furnished rooms with modern amenities' },
  { image: '/images/slide2.svg',   caption: 'Höchstädtplatz 6 · 1200 Vienna · U4 Friedensbrücke' },
  { image: '/images/slide3.svg',   caption: 'Continental breakfast available daily' },
]
</script>

<style scoped>
</style>
