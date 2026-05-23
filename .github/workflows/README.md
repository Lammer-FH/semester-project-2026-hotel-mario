# GitHub Actions CI/CD Pipeline

This directory contains the CI/CD pipeline for the Hotel Mario project.

## Main Pipeline: `ci.yml`

**Purpose:** Complete continuous integration pipeline with backend build and Docker image build.

**Triggers:**
- Push to `main`, `feature/**`, `bugfix/**` branches  
- Pull requests to `main`

### Pipeline Jobs

#### 1. Backend Build & Test
- ✅ Sets up JDK 25 (Amazon Corretto)
- ✅ Runs `gradle clean build` (excluding tests first for speed)
- ✅ Executes `gradle test` for unit tests
- ✅ Uploads test reports (30-day retention)
- ✅ Shows build artifact summary
- **Duration:** ~15-20 minutes

**Outputs:**
- JAR: `backend/build/libs/hotelmario-0.0.1-SNAPSHOT.jar`
- Test Reports: `backend/build/reports/tests/test/`

#### 2. Docker Build & Test (depends on: backend)
- ✅ Sets up Docker Buildx
- ✅ Builds Docker image locally (`hotelmario:latest`)
- ✅ Tests with Docker Compose
- ✅ Verifies MySQL connectivity
- ✅ Uses GitHub Actions caching
- ✅ Cleans up test environment
- **Duration:** ~15-25 minutes

**Image Tags:**
- `hotelmario:latest` - Latest build
- `hotelmario:${commit-sha}` - Specific commit

---

## Usage

### Automatic Triggers

Workflows run automatically:
- On push to monitored branches
- On pull requests to `main`

### View Pipeline Status

**GitHub Web UI:**
- Repository → Actions tab
- Select "CI Pipeline"

**Command Line:**
```bash
# List workflows
gh workflow list

# View specific run
gh run view <run-id> --log

# View latest run
gh run list --workflow=ci.yml
```

### Download Test Results

```bash
gh run download <run-id> --name test-results
```

Open `index.html` to view detailed test report.

---

## Configuration

### JDK Distribution
- **Current:** Amazon Corretto 25
- **Why:** Production-ready, free, stable in GitHub Actions

### Build Caching
- **Gradle:** Dependency cache (auto-managed)
- **Docker:** Layer cache (auto-managed)

### Artifact Retention
- **Test Results:** 30 days

---

## Environment

- **Runtime:** ubuntu-latest
- **Java:** JDK 25 (Amazon Corretto)
- **Build Tool:** Gradle 9.4.1
- **Container:** Docker + Docker Compose
- **Timeout:** 30 min (backend) + 45 min (docker)

---

## Troubleshooting

### Build Fails

1. **Check logs:**
```bash
gh run view <run-id> --log
```

2. **Common issues:**

| Issue | Solution |
|-------|----------|
| Gradle timeout | Backend timeout is 30min - check for hanging processes |
| Docker test fails | Verify docker-compose.yml is valid |
| MySQL fails | Check MySQL credentials in compose file |
| OOM errors | Increase `timeout-minutes` |

3. **Rerun workflow:**
```bash
gh run rerun <run-id>
```

### Debugging Locally

```bash
# Build backend
cd backend
./gradlew clean build

# Test Docker Compose
docker compose up -d
docker compose down -v
```

---

## Future Enhancements

- Add frontend build (when frontend exists)
- Push Docker images to registry (when not classroom environment)
- Add integration tests
- Add code coverage reporting
- Add security scanning

---

**Last Updated:** 2026-05-23  
**Pipeline Version:** 1.0
