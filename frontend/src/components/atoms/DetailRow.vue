<template>
  <ion-item lines="none">
    <ion-label class="detail-row">
      <strong>{{ label }}:</strong>
      {{ value }}
    </ion-label>
  </ion-item>
</template>

<script setup lang="ts">
import { IonItem, IonLabel } from '@ionic/vue'

defineProps<{
  label: string
  value: string | number
}>()
</script>

<style scoped>
.detail-row {
  white-space: normal;
}
</style>
