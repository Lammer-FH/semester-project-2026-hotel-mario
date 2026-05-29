# Build stage
FROM node:20-alpine AS builder

WORKDIR /app

# Copy package files first for better caching
COPY package*.json ./

# Install dependencies
RUN npm ci

# Copy rest of the source code
COPY . .

# Build the Ionic/Vue app
RUN npm run build

# Production stage (optional - for serving)
FROM nginx:alpine AS production

# Copy built files from builder stage
COPY --from=builder /app/www /usr/share/nginx/html

# Copy custom nginx config (optional)
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]