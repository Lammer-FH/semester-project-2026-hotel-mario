#!/bin/bash
set -e

echo "========================================="
echo "Building Backend"
echo "========================================="

cd backend

# Make gradle wrapper executable
chmod +x gradlew

# Build the backend
echo "Running Gradle build..."
./gradlew clean build --warning-mode all -x test

# Run tests
echo "Running unit tests..."
./gradlew test --warning-mode all

echo ""
echo "✅ Backend build completed successfully"
echo "JAR location: build/libs/hotelmario-0.0.1-SNAPSHOT.jar"
