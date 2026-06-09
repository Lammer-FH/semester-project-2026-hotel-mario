<template>
  <div>
    <div class="slideshow-container">
      <div
        v-for="(slide, index) in slides"
        :key="index"
        class="slide fade"
        v-show="current === index"
      >
        <div class="counter">{{ index + 1 }} / {{ slides.length }}</div>
        <img :src="slide.image" :alt="slide.caption" class="slide-img" />
        <div class="caption">{{ slide.caption }}</div>
      </div>
      <button class="prev" @click="prev" aria-label="Previous slide">❮</button>
      <button class="next" @click="next" aria-label="Next slide">❯</button>
    </div>

    <div class="dots">
      <span
        v-for="(_, index) in slides"
        :key="index"
        class="dot"
        :class="{ active: current === index }"
        @click="current = index"
      />
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue'

const props = defineProps<{
  slides: { image: string; caption: string }[]
  autoplayMs?: number
}>()

const current = ref(0)

function next() {
  current.value = (current.value + 1) % props.slides.length
}

function prev() {
  current.value = (current.value - 1 + props.slides.length) % props.slides.length
}

let timer: ReturnType<typeof setInterval> | undefined

onMounted(() => {
  if (props.autoplayMs) timer = setInterval(next, props.autoplayMs)
})

onUnmounted(() => {
  if (timer) clearInterval(timer)
})
</script>

<style scoped>
.slideshow-container {
  position: relative;
  overflow: hidden;
  border-radius: 8px;
}

.slide-img {
  width: 100%;
  height: 220px;
  object-fit: cover;
  display: block;
}

@media (min-width: 768px) {
  .slide-img {
    height: 300px;
  }
}

.counter {
  position: absolute;
  top: 8px;
  left: 8px;
  background: rgba(0, 0, 0, 0.45);
  color: #ffffff;
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 0.75em;
}

.caption {
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  background: rgba(0, 0, 0, 0.45);
  color: #ffffff;
  text-align: center;
  padding: 6px 8px;
  font-size: 0.85em;
}

.prev,
.next {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  background: rgba(0, 0, 0, 0.3);
  color: #ffffff;
  border: none;
  cursor: pointer;
  padding: 8px 12px;
  font-size: 1.1em;
  border-radius: 4px;
  user-select: none;
}

.prev { left: 6px; }
.next { right: 6px; }

.prev:hover,
.next:hover {
  background: rgba(0, 0, 0, 0.6);
}

.dots {
  text-align: center;
  margin-top: 6px;
}

.dot {
  display: inline-block;
  width: 10px;
  height: 10px;
  margin: 0 4px;
  background: var(--ion-color-medium);
  border-radius: 50%;
  cursor: pointer;
  opacity: 0.5;
  transition: opacity 0.2s;
}

.dot.active {
  background: var(--ion-color-primary);
  opacity: 1;
}

.fade {
  animation: fadeIn 0.4s ease-in;
}

@keyframes fadeIn {
  from { opacity: 0.6; }
  to   { opacity: 1; }
}
</style>
