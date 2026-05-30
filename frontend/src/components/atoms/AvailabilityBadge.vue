<template>
  <ion-badge
    v-if="available !== null"
    :color="available ? 'success' : 'danger'"
    class="availability-badge"
  >
    {{ available ? 'Available' : 'Not Available' }}
  </ion-badge>
</template>

<script setup lang="ts">
import { IonBadge } from '@ionic/vue'

defineProps<{ available: boolean | null }>()
</script>

<style scoped>
.availability-badge {
  margin-top: 4px;
}
</style>
