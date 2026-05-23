# GitHub Actions Workflows

This directory contains automated CI/CD workflows for the Hotel Mario project.

## Workflows Overview

### 1. Backend Build & Test (`backend-build.yml`)

**Purpose:** Build and test the Spring Boot backend application.

**Triggers:**
- Push to `main`, `feature/**`, `bugfix/**` branches
- Pull requests to `main`
- Changes to: `backend/`, `Dockerfile`, `docker-compose.yml`

**What it does:**
- ✅ Checks out code
- ✅ Sets up JDK 25 (Amazon Corretto - stable, production-ready)
- ✅ Validates Gradle wrapper integrity
- ✅ Runs `gradle clean build` (without tests first for faster feedback)
- ✅ Executes `gradle test` for unit tests
- ✅ Uploads test reports as artifacts (30 days retention)
- ✅ Fails the workflow if tests fail
- ✅ Shows build artifact sizes

**Duration:** ~15-20 minutes

**Outputs:**
- Test reports: `backend/build/reports/tests/test/`
- Built JAR: `backend/build/libs/hotelmario-0.0.1-SNAPSHOT.jar`

**JDK Distribution:** Amazon Corretto (production-grade, free, no licensing issues)

---

### 2. Docker Build & Test (`docker-build.yml`)

**Purpose:** Build Docker image and verify it works with Docker Compose.

**Note:** This is a GitHub Classroom environment. Docker images are built locally and not pushed to a registry.

**Triggers:**
- Push to `main`, `feature/**`, `release/**` branches
- Pull requests to `main`
- Manual trigger (`workflow_dispatch`)
- Changes to: `backend/`, `Dockerfile`, `docker-compose.yml`

**What it does:**
- ✅ Sets up Docker Buildx
- ✅ Builds Docker image locally
- ✅ Tests image with Docker Compose
- ✅ Verifies MySQL connectivity
- ✅ Uses GitHub Actions cache for faster builds
- ✅ Generates build summary

**Image Tags (local):**
- `hotelmario:latest` - Latest build
- `hotelmario:<commit-sha>` - Specific commit

**Duration:** ~15-25 minutes

**Pull Request Behavior:**
- Builds image locally (no registry push)

---

### 3. Docker Compose Test (`docker-compose-test.yml`)

**Purpose:** Verify the complete Docker Compose setup works end-to-end.

**Triggers:**
- Push to `main`, `feature/**` branches
- Pull requests to `main`
- Changes to: `backend/`, `Dockerfile`, `docker-compose.yml`

**What it does:**
- ✅ Builds Docker images
- ✅ Starts services with `docker compose up -d`
- ✅ Waits for services to be ready (10 seconds)
- ✅ Tests MySQL connectivity
- ✅ Checks Spring Boot service status
- ✅ Tests application health endpoint
- ✅ Verifies network communication between containers
- ✅ Collects logs for debugging
- ✅ Cleans up all resources

**Duration:** ~10-15 minutes

**Health Checks:**
- MySQL: `mysqladmin ping`
- Network: `docker compose ps`, inter-container ping
- Application: Health check endpoint (if available)

---

## Usage

### Automatic Triggers

Workflows run automatically when you:
- Push to `main` or feature branches
- Create/update pull requests
- Commit changes to monitored paths

### Manual Trigger

Trigger Docker Build manually:

```bash
gh workflow run docker-build.yml --ref main
```

Or use GitHub Web UI:
1. Go to Actions tab
2. Select workflow
3. Click "Run workflow"

### View Workflow Status

**Web UI:**
- GitHub Repository → Actions tab
- Select workflow → View runs

**CLI:**
```bash
gh workflow list
gh workflow view backend-build.yml
gh run list --workflow=backend-build.yml
gh run view <run-id> --log
```

---

## Configuration

### Secrets & Permissions

**Permissions Used:**
- `contents: read` - Read repository code
- `packages: write` - Push to GitHub Container Registry
- `GITHUB_TOKEN` - Auto-provided by GitHub Actions

**Note:** No manual secrets configuration needed. GitHub provides `GITHUB_TOKEN` automatically.

### Push to Registry (GitHub Classroom)

This is a **GitHub Classroom environment** where container registry access is not available.

To push images to a registry later, you would need:
1. Docker Hub account + credentials
2. Or GitHub Container Registry (GHCR) in a non-classroom environment

**Example for Docker Hub:**
```yaml
- name: Log in to Docker Hub
  uses: docker/login-action@v3
  with:
    username: ${{ secrets.DOCKERHUB_USERNAME }}
    password: ${{ secrets.DOCKERHUB_TOKEN }}

- name: Push to Docker Hub
  uses: docker/build-push-action@v5
  with:
    push: true
    tags: |
      yourusername/hotelmario:latest
      yourusername/hotelmario:${{ github.sha }}
```

Then add the secrets to your repository:
1. Go to Settings → Secrets and variables → Actions
2. Add `DOCKERHUB_USERNAME` and `DOCKERHUB_TOKEN`

---

## Workflow Customization
```yaml
- name: Set up JDK 25
  uses: actions/setup-java@v4
  with:
    java-version: '21'  # Change version here
    distribution: 'temurin'
```

**Add Docker Hub push:**
```yaml
- name: Log in to Docker Hub
  uses: docker/login-action@v3
  with:
    username: ${{ secrets.DOCKERHUB_USERNAME }}
    password: ${{ secrets.DOCKERHUB_TOKEN }}
```

Then add Docker Hub to `images` in metadata step.

**Change artifact retention:**
```yaml
- name: Upload test results
  uses: actions/upload-artifact@v4
  with:
    name: test-results
    retention-days: 30  # Default is 90
```

---

## Artifact Management

### Uploaded Artifacts

**Backend Build Test Results:**
- Path: `backend/build/reports/tests/test/`
- Retention: 90 days (default)
- Usage: View test reports in GitHub UI

**Download Artifacts:**

```bash
gh run download <run-id> --name test-results
```

### Cleanup Old Artifacts

GitHub automatically deletes artifacts after retention period expires.

---

## Troubleshooting

### Workflow Fails

1. **Check workflow logs:**
   ```bash
   gh run view <run-id> --log
   ```

2. **Common issues:**

   | Issue | Solution |
   |-------|----------|
   | Build timeout | Increase `timeout-minutes` in workflow |
   | OOM errors | Gradle may need more memory |
   | Network errors | Check internet connectivity |
   | Docker build fails | Check Dockerfile syntax |
   | MySQL health check fails | Verify MySQL configuration in docker-compose.yml |

3. **Rerun failed workflow:**
   ```bash
   gh run rerun <run-id>
   ```

### Push to GHCR Fails

1. Verify repository is public or PAT has `write:packages` scope
2. Check `GITHUB_TOKEN` permissions in repository settings
3. Ensure branch is `main` for image push

### Docker Compose Test Fails

1. Check Docker is available on runner: `docker --version`
2. View service logs: `docker compose logs`
3. Verify port 8080 and 3306 aren't in use

---

## Performance Optimization

### Build Cache

Both workflows use GitHub Actions cache:
- **Gradle cache:** Speeds up dependency resolution
- **Docker cache:** Reduces image build time

Cache is automatically managed; no configuration needed.

### Parallel Jobs

Workflows run sequentially to avoid resource contention. To run in parallel, modify `needs:` or create separate workflow files.

---

## Monitoring & Alerts

### GitHub Status

Check workflow status in repository homepage:
- Green checkmark ✅ = Success
- Red X ❌ = Failed
- Yellow circle ⏳ = In progress

### Email Notifications

GitHub sends email notifications for:
- Workflow failures
- Pull request check failures

Configure in GitHub Settings → Notifications.

---

## Related Documentation

- [GitHub Actions Docs](https://docs.github.com/en/actions)
- [Docker Build Push Action](https://github.com/docker/build-push-action)
- [Setup Java Action](https://github.com/actions/setup-java)
- [Docker Compose Docs](https://docs.docker.com/compose/)

---

**Last Updated:** 2026-05-23  
**Workflows Version:** 1.0
