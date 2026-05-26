# Build Guide - Hotel Mario

This document provides comprehensive instructions for building, deploying, and running the Hotel Mario application.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Quick Start](#quick-start)
- [Project Structure](#project-structure)
- [Build Instructions](#build-instructions)
- [Docker Compose Setup](#docker-compose-setup)
- [Configuration](#configuration)
- [Development](#development)
- [Troubleshooting](#troubleshooting)

---

## Prerequisites

### Required Tools

- **Docker** or **Podman** (required for containerized deployment)
  - Docker: https://docs.docker.com/get-docker/
  - Podman: https://podman.io/docs/installation

- **Java Development Kit (JDK)**
  - JDK 25 or higher (for local development)
  - **Eclipse Temurin (Recommended):** https://adoptium.net/
    - Free, open-source, TCK-certified
    - Maintained by the Eclipse Foundation
  - **Azul Zulu:** https://www.azul.com/downloads/zulu/
    - Free, open-source, builds for multiple platforms
  - **Amazon Corretto:** https://aws.amazon.com/corretto/
    - Free, production-ready distribution
  - **Oracle JDK:** https://www.oracle.com/java/technologies/
    - Commercial license required for production use

- **Gradle** (bundled in the Docker image, optional for local builds)
  - Local Gradle: https://gradle.org/install/

- **MySQL** (optional for local development; included in Docker Compose)
  - MySQL 8.0 or higher

- **Node package manager (npm)** 
  - npm install & ionic quickstart: https://ionicframework.com/docs/vue/quickstart

### Supported Platforms

- Linux (Ubuntu, Debian, CentOS, etc.)
- macOS (Intel & Apple Silicon)
- Windows 10/11 with WSL2 or native Docker Desktop

---

## Quick Start

### Option 1: Using Startup Scripts (Recommended)

**Linux/macOS:**
```bash
chmod +x startup.sh
./startup.sh
```

**Windows:**
```cmd
startup.bat
```

The script will:
- ✓ Detect Docker or Podman
- ✓ Build the container images
- ✓ Start MySQL and Spring Boot containers
- ✓ Verify all services are running
- ✓ Display application URL and useful commands

### Option 2: Manual Docker Compose

```bash
# Build and start containers
docker compose up -d --build

# View logs
docker compose logs -f

# Stop services
docker compose down
```

**Access the application:**
- Fronted: http://localhost:8081/home
- API: http://localhost:8080
- MySQL: localhost:3306

---

## Project Structure

```
semester-project-2026-hotel-mario/
├── backend/                          # Spring Boot 4 application
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/                # Application source code
│   │   │   └── resources/
│   │   │       └── application.properties  # Spring Boot configuration
│   │   └── test/                    # Unit tests
│   ├── build.gradle                 # Gradle build configuration
│   ├── gradlew                      # Gradle wrapper (Unix)
│   ├── gradlew.bat                  # Gradle wrapper (Windows)
│   └── settings.gradle              # Gradle settings
|── frontend/
|   |── src/...                      # Vue applcation
|   |── package.json                 # Vue & Ionic dependencies
├── docker-compose.yml               # Docker Compose orchestration
├── Dockerfile                       # Multi-stage Docker build
├── startup.sh                       # Linux/macOS startup script
├── startup.bat                      # Windows startup script
├── BUILD.md                         # This file
└── README.md                        # Project overview
```

---

## Build Instructions

### Local Build SpringBoot (Without Docker)

**Requirements:** JDK 25, Gradle

```bash
cd backend

# Build the project
./gradlew build

# Run tests
./gradlew test

# Start the application (requires local MySQL)
./gradlew bootRun
```

**Generated Artifacts:**
- JAR file: `backend/build/libs/hotelmario-0.0.1-SNAPSHOT.jar`

### Docker Build

**Build and run with Docker Compose:**

```bash
# Build images and start containers
docker compose up -d --build

# Monitor build progress
docker compose logs -f spring-boot

# Check container status
docker compose ps
```

**Build-specific options:**

```bash
# Build without cache (fresh rebuild)
docker compose build --no-cache

# Build specific service only
docker compose build spring-boot

# Build and don't start
docker compose build
```

---

### Local Build Vue.js (Without Docker)

```bash
cd frontend

# Start vue.js in development mode
npm run dev
```
**Access Frontend build in Browser via:** `http://localhost:5173/` 

---

## Docker Compose Setup

### Services

#### 1. MySQL Database
- **Image:** mysql:8.0
- **Container:** hotelmario-mysql
- **Port:** 3306 (mapped from container)
- **Database:** hotelmario_db
- **Username:** hotelmario
- **Password:** hotelmario_password
- **Root Password:** rootpassword
- **Health Check:** MySQL ping verification (10s interval, 5s timeout)

#### 2. Spring Boot Application
- **Image:** Built from Dockerfile
- **Container:** hotelmario-app
- **Port:** 8080 (mapped from container)
- **Dependencies:** Waits for MySQL to be healthy
- **JVM Memory:** -Xmx512m -Xms256m

#### 3. Vue.js Application
- **Image:** Built from Dockerfile-Frontend
- **Container:** hotelmario-frontend
- **Port:** 8081 (mapped from container)

### Network

- **Network Name:** hotelmario-network
- **Type:** bridge (containers can communicate by service name)
- **DNS Resolution:** Service names resolve within the network (e.g., `mysql:3306`)

### Volumes

- **mysql_data:** Persistent storage for MySQL database
- **Location:** Docker-managed volume (auto-created)
- **Retention:** Survives `docker compose down` (use `docker volume rm` to delete)

---

## Configuration

### Environment Variables

#### Spring Boot Container

```yaml
SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/hotelmario_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
SPRING_DATASOURCE_USERNAME: hotelmario
SPRING_DATASOURCE_PASSWORD: hotelmario_password
SPRING_JPA_HIBERNATE_DDL_AUTO: update
JAVA_OPTS: -Xmx512m -Xms256m
```

- **DATASOURCE_URL:** MySQL connection string (uses Docker DNS `mysql:3306`)
- **HIBERNATE_DDL_AUTO:** `update` (auto-creates/updates tables), change to `validate` for production

### Application Properties

**File:** `backend/src/main/resources/application.properties`

```properties
spring.application.name=hotelmario

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/hotelmario_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=hotelmario
spring.datasource.password=hotelmario_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate Configuration
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=true
```

**To modify:** Edit the file and rebuild the Docker image.

### Ports

| Service | Port | Mapped To |
|---------|------|-----------|
| Spring Boot | 8080 | 8080 (localhost:8080) |
| MySQL | 3306 | 3306 (localhost:3306) |
| Vue.js | 8081 | 80 (localhost:80) |

To change mapped ports, edit `docker-compose.yml`:

```yaml
services:
  mysql:
    ports:
      - "3307:3306"  # External:Internal (localhost:3307 -> container:3306)
  spring-boot:
    ports:
      - "8081:8080"  # External:Internal (localhost:8081 -> container:8080)
  vue-js:
    ports:
     - "8081:80" # # External:Internal (localhost:8081 -> container:80)
```

---

## Development

### Local Development Setup

#### 1. Install Prerequisites

**Java Development Kit:**

Eclipse Temurin (Recommended):
```bash
# macOS (via Homebrew)
brew install temurin25

# Ubuntu/Debian
sudo apt-get install temurin-25-jdk

# Windows (via Chocolatey)
choco install temurin25
```

Azul Zulu:
```bash
# macOS (via Homebrew)
brew install zulu@25

# Other platforms: https://www.azul.com/downloads/zulu/
```

**MySQL:**
docker run -d --name mysql-local \
  -e MYSQL_ROOT_PASSWORD=rootpassword \
  -e MYSQL_DATABASE=hotelmario_db \
  -e MYSQL_USER=hotelmario \
  -e MYSQL_PASSWORD=hotelmario_password \
  -p 3306:3306 \
  mysql:8.0
```

#### 2. Build and Run Locally
```bash
cd backend

# Build
./gradlew build

# Run
./gradlew bootRun

# Run with custom properties
./gradlew bootRun --args='--server.port=8081'
```

#### 3. Testing
```bash
cd backend

# Run all tests
./gradlew test

# Run specific test
./gradlew test --tests ClassName

# Run with coverage
./gradlew test jacocoTestReport
```

### IDE Setup

#### IntelliJ IDEA
1. Open `backend/` directory as project
2. Gradle will auto-sync
3. Run main application class from IDE

#### Eclipse
1. Import as Gradle project
2. Right-click project → Gradle → Refresh Gradle Project

#### VS Code
1. Install "Extension Pack for Java"
2. Install "Gradle for Java"
3. Open workspace in `backend/` folder

---

## Troubleshooting

### Container Issues

#### Containers won't start
```bash
# Check container logs
docker compose logs

# Check specific service
docker compose logs mysql
docker compose logs spring-boot

# Check docker daemon
docker info

# Restart Docker daemon
# On macOS: restart Docker Desktop
# On Linux: sudo systemctl restart docker
```

#### Port already in use
```bash
# Find process using port 8080
lsof -i :8080  # macOS/Linux
netstat -ano | findstr :8080  # Windows

# Kill process
kill -9 <PID>  # macOS/Linux
taskkill /PID <PID> /F  # Windows

# Or change mapped port in docker-compose.yml
```

#### MySQL won't connect
```bash
# Check MySQL container
docker compose ps mysql

# Verify network
docker compose exec spring-boot ping mysql

# Check MySQL logs
docker compose logs mysql

# Restart MySQL
docker compose restart mysql
```

#### Out of memory errors
```
java.lang.OutOfMemoryError: Java heap space
```

**Solution:** Increase JVM memory in docker-compose.yml:
```yaml
environment:
  JAVA_OPTS: "-Xmx1024m -Xms512m"  # Increase heap size
```

### Build Issues

#### Gradle build fails
```bash
# Clear gradle cache
./gradlew clean

# Rebuild
./gradlew build

# Verbose output
./gradlew build --info
```

#### Docker image build fails
```bash
# Build without cache
docker compose build --no-cache

# Check Dockerfile
cat Dockerfile

# Build with verbose output
docker build --verbose -f Dockerfile -t hotelmario:test .
```

### Application Issues

#### Application won't start
```bash
# Check logs
docker compose logs -f spring-boot

# Check MySQL connectivity
docker compose exec spring-boot mysqladmin ping -h mysql -u hotelmario -photelmario_password

# Verify database exists
docker compose exec mysql mysql -u hotelmario -photelmario_password -e "SHOW DATABASES;"
```

#### Database connection refused
```
com.mysql.cj.jdbc.exceptions.CommunicationsException: Communications link failure
```

**Solution:**
1. Ensure MySQL container is running: `docker compose ps mysql`
2. Wait for MySQL health check to pass
3. Check connection string in application.properties

### Cleanup

```bash
# Stop containers (keep volumes)
docker compose down

# Stop and remove volumes
docker compose down -v

# Remove images
docker compose down --rmi all

# Full cleanup (containers, images, volumes)
docker compose down -v --rmi all
```

---

## Production Considerations

### Before Deploying to Production

1. **Change Default Passwords**
   - Update MySQL root password
   - Update hotelmario user password
   - Update Spring Boot database credentials

2. **Disable DDL Auto**
   ```properties
   spring.jpa.hibernate.ddl-auto=validate
   ```

3. **Enable HTTPS**
   - Configure SSL/TLS certificates
   - Add to Spring Boot configuration

4. **Database Backup**
   - Set up automated MySQL backups
   - Use external volume management

5. **Logging**
   - Configure centralized logging (ELK, Splunk, etc.)
   - Set appropriate log levels

6. **Resource Limits**
   ```yaml
   deploy:
     resources:
       limits:
         cpus: '1'
         memory: 512M
       reservations:
         cpus: '0.5'
         memory: 256M
   ```

---

## Java Distributions

This project is compatible with multiple Java distributions. Here's a comparison:

| Distribution | License | Support | Verification |
|---|---|---|---|
| **Eclipse Temurin** | Open Source | Long-term | TCK-certified |
| **Azul Zulu** | Open Source | Long-term | TCK-certified |
| **Amazon Corretto** | Open Source | Long-term | TCK-certified |
| Oracle JDK | Commercial | Long-term | TCK-certified |

**Installation Guides:**
- Eclipse Temurin: https://adoptium.net/installation/
- Azul Zulu: https://docs.azul.com/core/install
- Amazon Corretto: https://docs.aws.amazon.com/corretto/latest/corretto-25-ug/

---

## Support & Documentation

- **Spring Boot Docs:** https://spring.io/projects/spring-boot
- **Docker Docs:** https://docs.docker.com/
- **Gradle Docs:** https://docs.gradle.org/
- **MySQL Docs:** https://dev.mysql.com/doc/
- **Ionic Docs:** https://ionicframework.com/docs/

---

**Last Updated:** 2026-05-26  
**Version:** 1.0
