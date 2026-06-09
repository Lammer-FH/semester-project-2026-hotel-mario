<template>
  <span class="extra-chip">
    <ion-icon :name="icon" class="extra-icon" />
    {{ name }}
  </span>
</template>

<script setup lang="ts">
import { IonIcon } from '@ionic/vue'

defineProps<{ name: string; icon: string }>()
</script>

<style scoped>
.extra-chip {
  display: inline-flex;
  align-items: center;
  gap: 4px;
  background: var(--ion-color-light);
  border-radius: 12px;
  padding: 2px 8px;
  font-size: 0.8em;
}

.extra-icon {
  font-size: 1em;
}
</style>
