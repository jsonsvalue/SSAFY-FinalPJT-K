<template>
  <div class="navigation-container">
    <b-navbar variant="light" class="px-3 py-2">
      <div class="d-flex align-items-center gap-3">
        <!-- Grid/Menu Icon -->
        <b-button
          variant="light"
          class="nav-icon-btn"
          @click="handleGridClick"
        >
          <div class="grid-icon">
            <div class="grid-dot"></div>
            <div class="grid-dot"></div>
            <div class="grid-dot"></div>
            <div class="grid-dot"></div>
            <div class="grid-dot"></div>
            <div class="grid-dot"></div>
            <div class="grid-dot"></div>
            <div class="grid-dot"></div>
            <div class="grid-dot"></div>
          </div>
        </b-button>

        <!-- Messenger Icon -->
        <b-button
          variant="primary"
          class="nav-icon-btn messenger-btn"
          @click="handleMessengerClick"
        >
          <svg width="20" height="20" viewBox="0 0 24 24" fill="currentColor">
            <path d="M12 2C6.48 2 2 6.48 2 12c0 1.54.36 3.04 1.05 4.35L2 22l5.65-1.05C9.96 21.64 11.46 22 13 22h-1c5.52 0 10-4.48 10-10S17.52 2 12 2zm0 18c-1.4 0-2.74-.35-3.9-.99L6 20l.99-2.1C6.35 16.74 6 15.4 6 14c0-4.41 3.59-8 8-8s8 3.59 8 8-3.59 8-8 8z"/>
            <path d="m8 13 2.5-2.5L13 12l3-3"/>
          </svg>
        </b-button>

        <!-- Notification Bell Icon -->
        <b-button
          variant="light"
          class="nav-icon-btn"
          @click="handleNotificationClick"
        >
          <b-icon-bell-fill class="text-dark"></b-icon-bell-fill>
        </b-button>

        <!-- User Avatar -->
        <b-button
          variant="light"
          class="nav-icon-btn avatar-btn"
          @click="handleProfileClick"
        >
          <b-avatar
            src="https://hebbkx1anhila5yf.public.blob.vercel-storage.com/image-zy2tEM4z816vQuJTSOmFOBbXjLreiT.png"
            size="32"
            alt="User Avatar"
          ></b-avatar>
        </b-button>
      </div>
    </b-navbar>
  </div>
</template>

<script setup>
import { ref } from 'vue'

// Reactive data
const notifications = ref(3)

// Methods
const handleGridClick = () => {
  console.log('Grid menu clicked')
  // Add your grid menu logic here
}

const handleMessengerClick = () => {
  console.log('Messenger clicked')
  // Add your messenger logic here
}

const handleNotificationClick = () => {
  console.log('Notifications clicked')
  // Add your notification logic here
}

const handleProfileClick = () => {
  console.log('Profile clicked')
  // Add your profile menu logic here
}
</script>

<style scoped>
.navigation-container {
  background-color: #f8f9fa;
  border-bottom: 1px solid #dee2e6;
}

.nav-icon-btn {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  border: none;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 0;
  transition: all 0.2s ease;
}

.nav-icon-btn:hover {
  transform: scale(1.05);
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
}

.nav-icon-btn:focus {
  box-shadow: 0 0 0 2px rgba(0, 123, 255, 0.25);
}

.messenger-btn {
  background-color: #0084ff;
  color: white;
}

.messenger-btn:hover {
  background-color: #0066cc;
}

.grid-icon {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 2px;
  width: 16px;
  height: 16px;
}

.grid-dot {
  width: 4px;
  height: 4px;
  background-color: #65676b;
  border-radius: 50%;
}

.avatar-btn {
  padding: 4px;
}

.avatar-btn:hover {
  background-color: #e4e6ea;
}

/* Gap utility for older Bootstrap versions */
.gap-3 > * + * {
  margin-left: 1rem;
}
</style>