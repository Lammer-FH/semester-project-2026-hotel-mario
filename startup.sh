#!/bin/bash

set -e

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Check for docker or podman
CONTAINER_CMD=""

if command -v docker &> /dev/null; then
    CONTAINER_CMD="docker"
    echo -e "${GREEN}✓ Docker found${NC}"
elif command -v podman &> /dev/null; then
    CONTAINER_CMD="podman"
    echo -e "${GREEN}✓ Podman found${NC}"
else
    echo -e "${RED}✗ Neither Docker nor Podman found. Please install one of them.${NC}"
    exit 1
fi

echo ""
echo -e "${YELLOW}Starting Hotel Mario Application...${NC}"
echo "Using: $CONTAINER_CMD"
echo ""

# Build and start containers
$CONTAINER_CMD compose up -d --build

# Wait for services to be ready
echo ""
echo -e "${YELLOW}Waiting for services to be ready...${NC}"
sleep 5

# Check if services are running
if $CONTAINER_CMD ps | grep -q hotelmario-mysql; then
    echo -e "${GREEN}✓ MySQL is running${NC}"
else
    echo -e "${RED}✗ MySQL failed to start${NC}"
    exit 1
fi

if $CONTAINER_CMD ps | grep -q hotelmario-app; then
    echo -e "${GREEN}✓ Spring Boot App is running${NC}"
else
    echo -e "${RED}✗ Spring Boot App failed to start${NC}"
    exit 1
fi

echo ""
echo -e "${GREEN}========================================${NC}"
echo -e "${GREEN}Hotel Mario is up and running!${NC}"
echo -e "${GREEN}========================================${NC}"
echo ""
echo "Application URL: http://localhost:8080"
echo "MySQL Port: 3306"
echo ""
echo "View logs:"
echo "  $CONTAINER_CMD compose logs -f"
echo ""
echo "Stop services:"
echo "  $CONTAINER_CMD compose down"
echo ""
