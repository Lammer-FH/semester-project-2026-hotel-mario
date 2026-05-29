# AI Usage Documentation

Group AE

This file documents all meaningful uses of AI tools during the development of the Boutique Hotel Technikum booking application, as required by the project assignment.

---

# Assignment: Planning and Specification 03.05.2026


## Tool

**Claude Code** (Anthropic)
- Model: **Claude Sonnet 4.6** (`claude-sonnet-4-6`)
- Interface: Claude Code CLI (interactive agent)
- Date: 2026-05-03

---

## Usage Log

### 1. Generate REST API Specification

**Task:** Generate a REST API specification in OpenAPI 3.0 based on the project specification and user stories (U1–U5).

**Prompt:**
> Generate a REST API Specification in OpenAPI 3.0 for the User Stories described in `ProjektSpezifikation2026.pdf` and `project-assignment2026.pdf`

**What was generated:**
- Full `openapi.yaml` covering:
  - `GET /rooms` — paginated room listing (U2)
  - `GET /rooms/{roomId}` — room detail (U2)
  - `GET /rooms/{roomId}/availability` — availability check (U3)
  - `POST /bookings` — booking creation and confirmation (U4, U5)
- Schemas: `Room`, `Extra`, `RoomPage`, `AvailabilityResponse`, `BookingRequest`, `BookingConfirmation`, `ErrorResponse`, `ValidationErrorResponse`, `FieldError`

**Accepted:** Fully accepted.

**Human reasoning:** The team chose an API-first approach — define the contract before writing any code. Using AI to produce an OpenAPI 3.0 spec from the requirement documents let us validate the scope quickly. We reviewed the generated Richardson Maturity Level 2 design, pagination structure, and error response format and agreed they were the right choices before committing to them.

---

### 2. Add Hotel with Contact Options and Directions

**Task:** Extend the API spec with a Hotel resource including contact and directions, and link it to rooms.

**Prompt:**
> add a Hotel with Contact Options and Directions, add Hotel Model to the room

**What was generated:**
- New `GET /hotel` endpoint
- New schemas: `Hotel`, `Address`, `ContactOptions`, `Direction`
- `hotel` field added to `Room` schema

**Rejected.** See entry 3.

**Human reasoning:** After seeing the output we reconsidered the design. Embedding hotel data on every room response would repeat the same static object with every API call, and a dedicated `/hotel` endpoint would require clients to make an extra request just to get contact details. Neither felt right. We decided to revert and think again.

---

### 3. Undo Hotel Changes

**Task:** Revert the previous changes entirely.

**Prompt:**
> undo the last changes

**What happened:** All changes from entry 2 were reverted.

**Human reasoning:** This was a deliberate reset to avoid accumulating changes built on a design we had already decided to abandon. Cleaning up immediately prevented the spec from growing inconsistently while we figured out the right approach.

---

### 4. Add Hotel as Static Object to BookingConfirmation

**Task:** Re-add the hotel data, but only embedded as a static object inside `BookingConfirmation`, without a separate endpoint or linking it to `Room`.

**Prompt:**
> add the single hotel with attributes ContactOptions and Directions as a static object to BookingConfirmation

**What was generated:**
- `Hotel`, `Address`, `ContactOptions`, `Direction` schemas added
- `hotel` field added to `BookingConfirmation` (required)
- No separate `/hotel` endpoint

**Accepted:** Fully accepted.

**Human reasoning:** The insight from the failed attempt in entry 2 was that hotel data is static — there is only one hotel and it never changes. It does not belong as a REST resource; it belongs on the confirmation page where the guest needs the contact number and directions. Embedding it in `BookingConfirmation` is the only place it makes business sense.

---

### 5. Simplify Directions, Remove Website

**Task:** Simplify the `Direction` schema to a plain string, and remove the `website` field from `ContactOptions`.

**Prompt:**
> make directions simpler as a single string field, remove website from Hotel object

**What was changed:**
- `Hotel.directions` changed from `array of Direction objects` to a single `string`
- `Direction` schema removed entirely
- `ContactOptions.website` field removed

**Accepted:** Fully accepted.

**Human reasoning:** The AI generated a structured `Direction` array, but the project only needs simple text directions (e.g. "Take the U4 to Friedensbrücke"). A structured object would have been over-engineering for what is ultimately a single paragraph of text. Similarly, a website field is not required by any user story — we removed it rather than leaving dead schema weight.

---

### 6. Add Icon to Extra

**Task:** Add an icon field to the `Extra` schema to support icon display in the room listing (U2).

**Prompt:**
> add icon to Extra

**What was generated:**
- `icon` field (string, Bootstrap Icon name) added to `Extra` schema

**Accepted:** Fully accepted.

**Human reasoning:** While reviewing the room listing mockup we noticed that extras need to be displayed with icons. Rather than hardcoding icons in the frontend, we extended the API schema so the backend drives icon selection — consistent with the API-first design decision made in entry 1.

---

### 7. Generate ER Diagram

**Task:** Generate an ER diagram covering Room, Extra, Booking, and a M:N mapping table between Room and Extra.

**Prompt:**
> generate an ER Diagram with Room, Extra and Booking Confirmation, create a M:N Mapping table between room and extra

**What was generated:**
- `er-diagram.md` with a Mermaid ER diagram
- Entities: `ROOM`, `EXTRA`, `ROOM_EXTRA` (M:N mapping), `BOOKING`
- Relations: `ROOM ||--o{ ROOM_EXTRA`, `EXTRA ||--o{ ROOM_EXTRA`, `ROOM ||--o{ BOOKING`

**Accepted:** Fully accepted.

**Human reasoning:** We consciously excluded a `HOTEL` table. Because there is only one hotel and its data is static (hardcoded in the application), a database table would have added migration and seeding complexity with no benefit. The AI proposed `HOTEL` as a separate table; we reviewed and removed it.

## Summary

| # | Task | Accepted | Modified | Rejected |
|---|------|----------|----------|----------|
| 1 | Generate OpenAPI spec | ✓ | | |
| 2 | Add Hotel endpoint + Room link | | | ✓ |
| 3 | Undo hotel changes | ✓ | | |
| 4 | Hotel as static object in BookingConfirmation | ✓ | | |
| 5 | Simplify directions, remove website | ✓ | | |
| 6 | Add icon to Extra | ✓ | | |
| 7 | Generate ER diagram | ✓ | | |
---

## Artefacts Produced by AI

- [`openapi.yaml`](openapi.yaml) — REST API specification (OpenAPI 3.0)
- [`er-diagram.md`](er-diagram.md) — ER diagram (Mermaid)
- [`AI_USAGE.md`](AI_USAGE.md) — this file

---

# Assignment: Backlog 08.05.2026


## Tool

**Claude Code** (Anthropic)
- Model: **Claude Sonnet 4.6** (`claude-sonnet-4-6`)
- Interface: Claude Code CLI (interactive agent)
- Date: 2026-05-08

**ChatGPT**
- Model: **GPT-5.5**
- Date: 2026-05-08

---

## Usage Log


### 1. Generate Backlog Items

**Task:** Create Backlog items for User Stories in Project Specification

**Prompt:**   
Attachement: ProjektSpezifikation2026.pdf
> Create User Stories out of the attached specification. Don't use emojis.

**What was generated:**
- GitHub Issue ID 3-7 were created with AI usage.
- Subtasks were created manually without AI usage.

**Accepted:** After review of the recommended items, we created them in GitHub.

**Human reasoning:** We used AI to produce a first draft quickly, but subtask breakdown was done manually. We felt granular task decomposition requires understanding of our team's working style and sprint capacity — something AI cannot infer from the spec alone.

---

### 2. AI-Assisted Review of Project Documents

**Task:** Provide project documents and explain the desired review.

**Prompt:**
API_specification.pdf; DB_Design.pdf; Paperprototype.pdf; ProjektSpezifikation2026.pdf
> folgend werde ich user stories schicken und du wirst mir bitte sagen auf grundlage der eigenen projekt-dokumente und der projektspezifikation, ob etwas fehlt/unvollständig ist/nicht passt an inhalt, kriterien, user stories, usw.

**What was generated:**

AI explained that it would review user stories against three dimensions:

  - Form of the user story (correct format, clarity)
  - Coverage of project specification (U1–U5 scope, recommended approach, definition of done)
  - Quality of acceptance criteria (testability, completeness, concreteness)

**Accepted**

**Human reasoning:** Before submitting user stories we wanted an external cross-check against our own project documents. By loading all four project documents into context and framing the task as a review rather than a generation, we got targeted feedback rather than generic suggestions.

---

### 3. AI Analysis of User Stories (Story by Story)
**Task:** Review each user story against the project specification and our own documents.

**Prompt:** 
- Existing user stories from GitHub Kanban board
- Diese features (user stories) teilen wir jeweils in einzelne sub-Tasks (thematlich eingeteilt) auf mit einzelnen großen unter-Aufgaben-Themen als Aufzählungen.

**What was generated:**

*U1 – Landing Page*
Good: story fits, responsive, static pages, content.
Missing: minimum pages not in AC (landing, about, imprint), navigation not defined.

*U2 – Room Selection*
Good: pagination, icons, 5 rooms, API reference.
Critical: API returns all rooms, not only "available" — story was incorrectly worded. Error handling missing.

*U3 – Check Availability*
Good: date picker, API, feedback.
Critical: validation rules incomplete (checkIn ≥ today, checkOut > checkIn).

*U4 – Booking*
Good: review step, fields, validation.
Critical: API rules missing (email == confirmation, room availability, 400/409). Success state unclear.

*U5 – Confirmation*
Good: contents, print, map, failure.
Critical: API data not formulated as mandatory, print criterion too vague.

Global: Definition of Done missing from ALL user stories.

**Modified:** AI output was used selectively. Some points were adopted, some dropped, and most suggestions were adapted to fit the existing project before use.

**Human reasoning:** The AI flagged real gaps — particularly the missing Definition of Done and the U2 wording bug about "available rooms." But not every suggestion was applicable. We filtered based on what the project specification actually required rather than accepting all recommendations. The critical point about U2 ("API returns all rooms, not only available ones") was correct and was fixed. Several other points were considered but deemed out of scope for the current milestone.

---

## Summary

| # | Task | Accepted | Modified | Rejected |
|---|------|----------|----------|----------|
| 1 | Generate Backlog Items | ✓ | | |
| 2 | AI review setup with project documents | ✓ |  | |
| 3 | AI analysis of user stories (story by story) |  | ✓ | |
---

## Artefacts Produced by AI

- GitHub Issues ID 3-7 — https://github.com/Lammer-FH/semester-project-2026-hotel-mario/issues

---

# Assignment Milestone 2

## Tool

**ChatGPT**
- Model: **GPT-5.5**
- Date: 2026-05-08

---

## Usage Log

### 1. Subtask: #11 CI configuration
**Task:** Erstellung eines CI Scripts

**Prompt:** GitHub Actions, if i want to build my backend, fronented and docker, how would i do this? My approach would be to create build scripts for each and then call via GitHubActions

**What was generated:**
> .github/workflows/ci.yml

**Modified:** Commented out installation of Node dependency for now. Added 
> pull_request:   
  branches: [ "main" ]

**Human reasoning:** The human already had a clear mental model ("build scripts called from GitHub Actions") and used ChatGPT to implement that idea, not to design it. The Node dependency was commented out immediately because the frontend did not exist yet — keeping it would have broken every CI run.

### 2. Extended CI Workflow — #11 CI configuration

**Tool:** **Claude Code** (Anthropic), Model: **Claude Sonnet 4.6**, Date: 2026-05-23

**Task:** Extend the initial CI prototype into a full multi-job GitHub Actions pipeline covering backend build, Docker build, and Docker Compose integration test.

**Prompt:**
> setup github action for backend and container image build

**What was generated:**
- `.github/workflows/docker-build.yml` — builds and pushes Docker image
- `.github/workflows/docker-compose-test.yml` — integration test using docker-compose
- `scripts/build-docker.sh` and `scripts/build-frontend.sh` — reusable build scripts called from CI
- Iterative fixes to `backend-build.yml` across multiple turns

**Modified:** Workflow trigger conditions, branch filters, and script contents adjusted manually across several commits (`025965a`, `fb587de`, `afd1a12`) to match the actual project layout.

**Human reasoning:** The AI-generated workflows used generic assumptions about project structure. We manually corrected trigger conditions and branch filters across multiple commits because only we knew which branches and paths were meaningful for our CI strategy.

---

## Summary

| # | Task | Accepted | Modified | Rejected |
|---|------|----------|----------|----------|
| 1 | Subtask: #11 CI configuration (ChatGPT) | | ✓ | |
| 2 | Extended CI workflow (Claude Code) | | ✓ | |

---

# Issue #12 — Set Up Spring Boot Backend (Docker + Local Dev)

## Tool

**Claude Code** (Anthropic)
- Model: **Claude Sonnet 4.6** (`claude-sonnet-4-6`)
- Interface: Claude Code CLI / VS Code extension (interactive agent)
- Date: 2026-05-23

---

## Usage Log

### 1. Docker Compose Setup

**Task:** Create a Docker Compose configuration running MySQL and the Spring Boot backend together.

**Prompt:**
> Generiere eine docker-compose yml mit 1x container mysql und 1x container: integriere ein bestehendes spring boot 4 projekt im ordner backend

**What was generated:**
- `docker-compose.yml` — MySQL service + Spring Boot service wired together
- `Dockerfile` — builds the Spring Boot application
- `backend/src/main/resources/application.properties` — datasource URL, credentials, JPA config pointing to the MySQL container

**Accepted:** Fully accepted.

---

### 2. Switch to JDK 25 Gradle Image

**Task:** Update the Dockerfile to use a JDK 25 base image with Gradle support.

**Prompt:**
> gradle image tag jdk25

**What was generated:**
- Updated `Dockerfile` base image tag to a JDK 25 variant

**Accepted:** Fully accepted.

**Human reasoning:** The AI initially generated a generic JDK image. We knew the project requires JDK 25 because the Gradle toolchain is configured for it — a short follow-up prompt corrected the tag rather than accepting a container that would fail to build the actual project.

---

### 3. Startup Scripts

**Task:** Generate cross-platform startup scripts for local development that work with both Docker and Podman.

**Prompt:**
> generate a startup.bat and startup.sh file, expect a docker or podman installation

**What was generated:**
- `startup.sh` (66 lines) — detects Docker or Podman, starts services
- `startup.bat` (83 lines) — Windows equivalent with the same detection logic

**Accepted:** Fully accepted.

**Human reasoning:** The explicit requirement to support both Docker and Podman came from us — not everyone on the team uses the same container runtime. The AI would not have inferred this requirement without the prompt specifying it.

---

### 4. BUILD.md Documentation

**Task:** Generate build documentation referencing non-Oracle JDKs.

**Prompt:**
> create a BUILD.md documentation, reference non oracle jdk like zulu or eclipse tamurin

**What was generated:**
- `BUILD.md` (545 lines) — full build guide covering prerequisites (Zulu / Eclipse Temurin JDK 25), Gradle commands, Docker setup, local development workflow, environment variables, and troubleshooting

**Accepted:** Fully accepted.

**Human reasoning:** The instruction to reference Zulu and Eclipse Temurin specifically was a deliberate choice — Oracle JDK licensing is a concern for university projects. We steered the AI toward the open alternatives rather than accepting a generic Java installation guide.

---

## Summary

| # | Task | Accepted | Modified | Rejected |
|---|------|----------|----------|----------|
| 1 | Docker Compose setup | ✓ | | |
| 2 | Switch to JDK 25 Gradle image | ✓ | | |
| 3 | Startup scripts (startup.sh / startup.bat) | ✓ | | |
| 4 | BUILD.md documentation | ✓ | | |

---

## Artefacts Produced by AI

- [`docker-compose.yml`](docker-compose.yml)
- [`Dockerfile`](Dockerfile)
- [`startup.sh`](startup.sh) / [`startup.bat`](startup.bat)
- [`BUILD.md`](BUILD.md)

---

# Issue #44 — Generate API from OpenAPI + Clean Architecture Refactoring

## Tool

**Claude Code** (Anthropic)
- Model: **Claude Sonnet 4.6** (`claude-sonnet-4-6`)
- Interface: Claude Code CLI / VS Code extension (interactive agent)
- Date: 2026-05-23

---

## Usage Log

### 1. Clean Architecture Review of Integrated Boilerplate

**Task:** Review the integrated Spring Boot 4 + swagger-codegen codebase for Clean Architecture violations.

**Prompt:**
> review the current generated and code for Clean Architecture issues — only reflect the current state, we had started a boilerplate spring boot 4 application and integrated the openapi generated spring boot code. ignore missing business logic and persistence for now

**What was generated:**
- Analysis of violations: `io.swagger.*` namespace as application package, generated classes used as DTOs and wired directly into Spring context, no separation of layers, `@ComponentScan` redundant with `@SpringBootApplication`, CORS filter not a Spring bean, removed `ObjectMapper` anti-pattern in controllers.

**Accepted:** Used as a basis for understanding which files to keep, move, or delete.

**Human reasoning:** We deliberately scoped the review — "ignore missing business logic and persistence for now" — because we wanted to understand structural violations without being overwhelmed by a list that included every unimplemented feature. This framing made the AI's output actionable immediately.

---

### 2. Package Reorganization and Clean Architecture Implementation (Phase 1)

**Task:** Move application code out of `io.swagger.*` into the application namespace, clean up generated infrastructure conflicts.

**Prompts:**
> reconsider package organization for io.swagger.api into more appropriate package structure, consider best practices for Clean Architecture and implement

**What was generated:**
- Deleted generated controller implementations (`BookingsApiController`, `RoomsApiController`), CORS filter (`ApiOriginFilter`), unused JAXB helper (`ApiResponseMessage`), and all generated configuration classes
- Moved `NotUndefined` / `NotUndefinedValidator` to `...validation/`
- Created `WebConfig` (CORS, date converters, view controllers), `OpenApiConfig`
- Created `adapter/web/HomeController`, `RoomsController`, `BookingsController`
- Updated all `io.swagger.model.*` classes to import `NotUndefined` from new package
- Removed explicit `@ComponentScan` from `HotelmarioApplication`
- Fixed `server.servlet.contextPath` to `/api`

**Accepted:** All changes accepted. The boundary decision — keep `io.swagger.api` interfaces and `io.swagger.model` classes as generated artifacts, own everything else — was reviewed and confirmed as correct.

**Modified:** `NotUndefinedValidator` exception handling was changed from `e.printStackTrace()` to `throw new RuntimeException(...)` on our initiative after reviewing the generated code.

**Human reasoning:** Reviewing the generated `NotUndefinedValidator` we saw it was silently swallowing exceptions with `e.printStackTrace()`. That is not acceptable in production code — we changed it before accepting the file. The AI generated functioning code but we applied our own quality standard.

---

### 3. Stub Controllers with OpenAPI Example Data

**Task:** Fill the GET endpoint stubs with response data matching the OpenAPI spec examples.

**Prompt:**
> fill the stub controllers with example data from openapi.yml for responses where data gets read

**What was generated:**
- `RoomsController`: `getRooms` returns `RoomPage` with 2 example rooms, `getRoomById` echoes requested id, `checkRoomAvailability` returns `available: true`
- All room data (title, price, extras) matches openapi.yaml example values

**Accepted:** Fully accepted. Verified against openapi.yaml.

---

### 4. BookingsController POST Logic

**Task:** Implement full controller-layer logic for `POST /bookings` without a service or persistence layer.

**Prompt:**
> add controller logic to post api bookings, validate input and implement the openapi spec, but dont build a service or persistence yet, just the controller

**What was generated:**
- Bean validation via `@Valid @RequestBody`
- `@ExceptionHandler(MethodArgumentNotValidException.class)` returning `ValidationErrorResponse`
- Cross-field validation: email match, `checkIn` ≥ today, `checkOut` > `checkIn`
- 201 response with `BookingConfirmation` built from request + hardcoded hotel/room data
- Unchecked cast `(ResponseEntity<BookingConfirmation>) (ResponseEntity<?>)` with `@SuppressWarnings("unchecked")` to satisfy the typed generated interface

**Accepted:** Fully accepted. Tested manually with curl confirming 201 response.

**Human reasoning:** "Don't build a service or persistence yet" was a deliberate constraint — we wanted working API validation and a correct response shape before touching the database layer. Building incrementally meant we could verify HTTP semantics in isolation without needing MySQL running.

---

### 5. Full Clean Architecture Refactoring (Component + Layer Structure)

**Task:** Refactor the entire backend to match the lecturer's Clean Architecture pattern: component packages, versioned API layer, entities, repositories, services, DTOs, mappers.

**Prompt:**
> come up with a plan to refactor accordingly [to the lecturer's guidelines: component packages with v1 versioning, @Entity models named *Entity with Lombok, @Repository JpaRepository, @Service, @RestController + @RequestMapping, DTOs with @Builder @Value @JsonProperty]
> do it all

**What was generated:**

*Dependencies (`build.gradle`):*
- Added `spring-boot-starter-data-jpa`, Lombok (`compileOnly` + `annotationProcessor`), `lombok.config` with `config.stopBubbling = true`
- Removed `jakarta.xml.bind-api` and `spring-plugin-core` (no longer needed after deleting generated boilerplate)

*Entity classes (unversioned core):*
- `RoomEntity` — `@Entity @Table(name="rooms") @Getter @Setter`, `@ManyToMany` to `ExtraEntity`
- `ExtraEntity` — `@Entity @Table(name="extras") @Getter @Setter`
- `BookingEntity` — `@Entity @Table(name="bookings") @Getter @Setter`, `@ManyToOne` to `RoomEntity`

*Repository interfaces (unversioned core):*
- `RoomRepository extends JpaRepository<RoomEntity, Long>` with `@Repository`
- `BookingRepository extends JpaRepository<BookingEntity, UUID>` with `@Repository`

*Service classes (unversioned core):*
- `RoomsService` — `@Service`, constructor injection of `RoomRepository`; `findAll`, `findById`, `isAvailable` (stub returning `true`)
- `BookingsService` — `@Service`, constructor injection of both repositories; `create` validates room exists, sets `createdAt`, persists

*DTO classes (versioned, in `api/dtos/v1/`):*
- Rooms: `RoomDto`, `RoomPageDto`, `AvailabilityResponseDto`, `ExtraDto`
- Bookings: `BookingRequestDto`, `BookingConfirmationDto`, `AddressDto`, `ContactDto`, `HotelDto`
- Common: `ValidationErrorResponseDto`, `FieldErrorDto`
- `BookingRequestDto` uses `@Data @Builder @NoArgsConstructor @AllArgsConstructor` to allow Jackson deserialization (requires a mutable, no-args constructible class)

*Mapper classes (versioned, in `api/mapper/v1/`):*
- `RoomMapper` — `@Component`, manual `RoomEntity → RoomDto` and `ExtraEntity → ExtraDto`
- `BookingMapper` — `@Component`, `BookingRequestDto → BookingEntity` and `BookingEntity → BookingConfirmationDto`; hotel data hardcoded as stub

*Controllers (versioned, in `api/v1/`):*
- `RoomsController` — `@RestController @RequestMapping("/v1/rooms")`
- `BookingsController` — `@RestController @RequestMapping("/v1/bookings")`

*Exception handling:*
- `GlobalExceptionHandler` — `@RestControllerAdvice` in `common/api/v1/`

**Accepted:** Fully accepted after build verification (`BUILD SUCCESSFUL`).

**Modified by AI during implementation:**
- Discovered `@WebMvcTest` moved packages in Spring Boot 4 — corrected autonomously

**Human reasoning:** We asked the AI to "come up with a plan" first, then reviewed it before saying "do it all." This two-step approach meant we confirmed the architecture before any code was written. The AI caught a Spring Boot 4 package move autonomously during implementation — we let that fix proceed without interruption because the rationale was clearly explained.

---

### 6. Controller Tests with @WebMvcTest

**Task:** Write `@WebMvcTest` slice tests for both controllers, mocking the service and mapper layer.

**Prompt:**
> write a controller test class for rest endpoints

**What was generated:**
- `RoomsControllerTest` — 6 tests: paged list (default params), custom page/size params, find by id found, find by id not found (404), availability available, availability unavailable
- `BookingsControllerTest` — 7 tests: valid request (201), email mismatch (400 with field error), checkout before checkin (400 with field error), missing `firstName` (400), invalid email format (400), past `checkIn` (400), room not found (404)
- Dynamic date strings (`LocalDate.now().plusDays(...)`) used throughout to avoid hardcoded future dates becoming stale

**Accepted:** Fully accepted after all 14 tests passed.

**Modified:** During implementation, a Spring Boot 4 compatibility issue was found and fixed:
1. `@WebMvcTest` import updated to new package

---

### 7. Clean Architecture Verification

**Task:** Verify the refactored codebase against each of the lecturer's Clean Architecture criteria.

**Prompt:**
> verify clean architecture

**What was generated:**
- Structured review table mapping each criterion to the current implementation
- Identified 5 remaining issues: orphaned `HomeController` in `adapter/web/`, dead `checkIn` validation branch in `BookingsController`, `ResponseEntity<?>` wildcard return type, `BookingsService` directly importing `RoomRepository` across component boundary, hotel data hardcoded inside `BookingMapper`

**Accepted:** Used as reference for known technical debt.

**Human reasoning:** Rather than fixing all issues immediately, we documented them as known technical debt. This was a conscious prioritization decision — not every violation needed to block this PR. The list became the input for the next session's work.

---

## Summary

| # | Task | Accepted | Modified | Rejected |
|---|------|----------|----------|----------|
| 1 | Clean Architecture review of boilerplate | ✓ | | |
| 2 | Package reorganization + phase 1 refactoring | ✓ | ✓ (exception handling) | |
| 3 | Stub controllers with OpenAPI example data | ✓ | | |
| 4 | BookingsController POST logic | ✓ | | |
| 5 | Full Clean Architecture refactoring | ✓ | ✓ (Spring Boot 4 package moves) | |
| 6 | Controller tests with @WebMvcTest | ✓ | ✓ (Spring Boot 4 package moves) | |
| 7 | Clean Architecture verification | ✓ | | |

---

## Artefacts Produced by AI

- [`backend/src/main/java/.../components/`](backend/src/main/java/at/technikumwien/mse25/awt/hotelmario/components/) — full component package structure (entities, repositories, services, DTOs, mappers, controllers)
- [`backend/src/main/java/.../common/`](backend/src/main/java/at/technikumwien/mse25/awt/hotelmario/common/) — shared DTOs and GlobalExceptionHandler
- [`backend/src/test/.../components/`](backend/src/test/java/at/technikumwien/mse25/awt/hotelmario/components/) — RoomsControllerTest, BookingsControllerTest
- [`backend/lombok.config`](backend/lombok.config) — Lombok configuration
- [`backend/build.gradle`](backend/build.gradle) — updated dependencies

---

# Issue #52 — Add Backend Debug Port to Docker Image

## Tool

**Claude Code** (Anthropic)
- Model: **Claude Sonnet 4.6** (`claude-sonnet-4-6`)
- Interface: Claude Code CLI (interactive agent)
# Issue #9 & #10 — Set up Vue.js + Frontend build config

## Tool

**Lumo** (Proton)
- Model: **Lumo(multiple specialized models)** (`version: 2026-05-22`)
- Interface: Browser
- Date: 2026-05-25

---

## Usage Log

### 1. Create Debug Dockerfile with Remote JVM Debug Port

**Task:** Create a `Dockerfile.debug` that exposes port 5005 for remote JVM debugging via JDWP, keeping the same build stage as the production `Dockerfile`.

**Prompt:**
> create a Dockerfile for debugging purposes, expose a port for remote JVM debugging and enter what you have changed into the AI_USAGE.md file

**What was generated:**
- `Dockerfile.debug` — identical build stage to `Dockerfile`; runtime stage adds `-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005` to the JVM startup arguments and exposes port `5005` alongside `8080`

**Accepted:** Fully accepted.

---

### 2. Create Debug Docker Compose File

**Task:** Create a `docker-compose.debug.yml` that uses `Dockerfile.debug`, maps port 5005 for the JDWP debugger, and keeps the same MySQL setup as the production compose file.

**Prompt:**
> create a corresponding docker-compose file and append to the AI_USAGE.md file

**What was generated:**
- `docker-compose.debug.yml` — MySQL service (identical to production) + Spring Boot service built from `Dockerfile.debug`; port `5005` mapped to the host alongside `8080`; separate named volume (`mysql_data_debug`) and network (`hotelmario-debug-network`) to avoid conflicts with the production stack

**Accepted:** Fully accepted.

---

### 3. Append Remote Debugging Section to BUILD.md

**Task:** Document how to start the debug Docker Compose stack and attach a JDWP debugger in the build guide.

**Prompt:**
> append in the build guide how to run the docker compose for debugging, append what you did in the AI_USAGE.md file

**What was generated:**
- New `## Remote Debugging` section in `BUILD.md` added to the Table of Contents and inserted after the Docker Compose Setup section; covers:
  - `docker compose -f docker-compose.debug.yml up -d --build` startup command
  - IntelliJ IDEA attach instructions (Remote JVM Debug run configuration)
  - VS Code attach instructions (`launch.json` snippet)
  - Teardown commands (`down` / `down -v`)
  - Security note warning against production use of the debug image

**Accepted:** Fully accepted.

---
### 1. Create Dockerfile for vue.js

**Task:** Setup a Dockerfile to be used in docker-compose.yml for running vue.js & ionic in a container

**Prompt:**
> can you help me setup a build script so that vue js with inoic is built in docker?

**What was generated:**
- `Dockerfile-Frontend`

**Accepted:** Accepted Dockerfile, after some manual changes.

**Modified:** Fixed path in Dockerfile from `/app/www` to `app/dist`.   
Fixed path in file to `frontend/` instead of `.`

## Summary

| # | Task | Accepted | Modified | Rejected |
|---|------|----------|----------|----------|
| 1 | Create Dockerfile.debug with JDWP debug port | ✓ | | |
| 2 | Create docker-compose.debug.yml | ✓ | | |
| 3 | Append Remote Debugging section to BUILD.md | ✓ | | |
| 1 | Create Dockerfile for vue.js |  | ✓ (path and port modification) | |

---

## Artefacts Produced by AI

- `./Dockerfile-Frontend`


# Issues #17, #18 #19 — Hotel Room Selection

## Tool

**Copilot** (Microsoftn)
- Model: **Lumo(multiple specialized models)** (`version: 2026-05-22`)
- Interface: Browser
- Date: 2026-05-26 - 2026-05-29
- [`Dockerfile.debug`](Dockerfile.debug) — debug image with remote JVM debugging on port 5005
- [`docker-compose.debug.yml`](docker-compose.debug.yml) — compose stack wiring MySQL and the debug Spring Boot image together
- [`BUILD.md`](BUILD.md) — Remote Debugging section with IDE attach instructions

---
- `./Dockerfile-Frontend`

# Milestone 2 — Backend Implementation (U2/U3): Clean Architecture, Testing, and Fixtures

## Tool

**Claude Code** (Anthropic)
- Model: **Claude Sonnet 4.6** (`claude-sonnet-4-6`)
- Interface: Claude Code VS Code extension (interactive agent)
- Date: 2026-05-23

---

## Usage Log

### 1. Create RoomSelectionView.vue

**Task:** Create a RoomSelectionView.vue with mocked API calls to start development.

**Prompt:**
> Can you code a view for this image? At the top there is a booking selection, in the second half of the screen there is a scrollable overview of rooms inlcuding images. For needed api calls code a placeholder including a comment that this should be an api call
- Attachment: Paperprotoype

**What was generated:**
- `RoomSelectionView`

**Accepted:** Accepted RoomSelectionView.vue, after some manual changes and some prompts.

**Modified**
- Implemented date filter UI (check-in / check-out) using ion-datetime
- Refactored datepicker to modal-based interaction (hidden until click)
- Fixed Ionic component registration issues (ion-page, ion-select, etc.)
- Debugged reactivity issues (ref vs unwrapped state)
- Replaced unreliable v-model with @ionChange / @ionInput handling
- Implemented persons selection with ion-select
- Added date validation logic (check-out ≥ check-in)
- Added price validation (min ≤ max)
- Implemented reactive validation with computed properties
- Improved UX with error messages instead of auto-correction
- Added popup error handling using ion-alert
- Converted API helper to TypeScript with generics

### 2. Split View into components

**Task:** Split RoomSelectionView.vue into components

**Prompt:**
> Can we split RoomSelectionView into components?

**What was generated:**
- `RoomSelectionView`
- `DatePickerModal`
- `FilterBar`
- `RoomList`

**Accepted:** Accepted components, after some manual changes and some prompts.

**Modified**
- Split View into components
- Added addtional validation logic
- Added pagination
- Small adjustments that broke during split
### 1. Fix CI — HotelmarioApplicationTests Fails Without MySQL

**Task:** `HotelmarioApplicationTests` failed in GitHub Actions because no MySQL was available. Fix without requiring a container in CI.

**Prompt:**
> fix HotelmarioApplicationTests CI failure (no MySQL in GitHub Actions)

**What was generated:**
- `backend/src/test/resources/application.properties` — H2 in-memory datasource for all tests, `ddl-auto=create-drop`
- `testRuntimeOnly 'com.h2database:h2'` added to `build.gradle`

**Accepted:** Fully accepted.

**Human reasoning:** We specifically wanted a fix that did not require a MySQL container in the CI context test (which would have been slow and required secrets). Using H2 for the Spring Boot context load test was the right tradeoff — the actual database behavior is tested separately with Testcontainers in `SchemaCreationTest` and `EndToEndTest`.

---

### 2. Interface–Implementation Pattern

**Task:** Introduce interfaces for `BookingsController`, `BookingsService`, `BookingMapper`, and the rooms equivalents to decouple components and enable testability.

**Prompt:**
> introduce interfaces for concrete classes for decoupling and testability, i.e. in BookingsController, BookingsService, BookingMapper

**What was generated:**
- `RoomApi` / `RoomController` — API interface with Spring MVC annotations; `@RestController` implements it
- `RoomService` / `RoomServiceImpl` — service interface + implementation
- `RoomMapper` / `RoomMapperImpl` — mapper interface + implementation
- Same pattern for the bookings component
- `adapter/web/` package deleted after `ConflictingBeanDefinitionException` was detected

**Accepted:** Fully accepted.

**Human reasoning:** The interface/implementation split was our own architectural decision, not AI-suggested. We directed the AI to implement a pattern we had already decided on. The `ConflictingBeanDefinitionException` was a real runtime error caught by the AI autonomously — we let that fix proceed.

---

### 3. Schema Creation Tests (H2 + MySQL Testcontainers)

**Task:** Write unit tests that verify the DDL is created correctly on startup, first with H2, then with a fresh MySQL container.

**Prompts:**
> write a unit test to verify ddl creation on initial startup
> create a unit test to verify ddl creation by using a fresh mysql container

**What was generated:**
- `SchemaCreationTest` — `@DataJpaTest`, H2, checks singular table names (`room`, `extra`, `room_extra`, `booking`) and key columns
- `MysqlSchemaCreationTest` — `@SpringBootTest @Testcontainers`, `MySQLContainer` via `@ServiceConnection`, same assertions without `.toUpperCase()` (MySQL is case-sensitive on Linux)
- `testImplementation 'org.springframework.boot:spring-boot-starter-data-jpa-test'` added for the moved `@DataJpaTest` package in Spring Boot 4

**Accepted:** Fully accepted. Fixed Testcontainers 2.x API change (`MySQLContainer<?>` → `MySQLContainer`) during implementation.

**Human reasoning:** We wrote two separate schema tests deliberately — one fast (H2, runs in seconds) and one accurate (MySQL, runs against the real database engine). The H2 test gives quick feedback in development; the MySQL test gives confidence that the schema works in production conditions. The AI implemented both as requested without merging them into one.

---

### 4. Entity and ER Diagram Alignment

**Task:** Verify and align entity structure, table names, and field types against the ER diagram as source of truth.

**Prompt:**
> dont update the er diagram, refactor entities and naming, attributes in the code to the previous correct er diagram as source of truth

**What was generated:**
- `RoomEntity` — `@Table(name = "room")`, `BigDecimal pricePerNight`
- `ExtraEntity` — `@Table(name = "extra")`
- `BookingEntity` — `@Table(name = "booking")`
- `RoomDto.pricePerNight` changed from `Double` to `BigDecimal`

**Accepted:** Fully accepted.

**Human reasoning:** "Don't update the ER diagram" was explicit — the diagram was already agreed upon and correct. The code had drifted from it during refactoring (plural table names, `Double` instead of `BigDecimal`). We treated the ER diagram as the authoritative source and directed the AI to align the code to it, not the other way around.

---

### 5. DataSeeder with Initial Fixtures

**Task:** Seed rooms, extras, and bookings at startup when tables are empty; all rooms belong to the single hotel.

**Prompt:**
> add fixtures for rooms, extras and some bookings into the database in initial startup when tables are empty. rooms should all be from the same hotel, there is only one hotel

**What was generated:**
- `DataSeeder implements ApplicationRunner` — guards with `roomRepository.count() > 0`; seeds 7 extras, 5 rooms (€89.99–€279.99), 3 bookings (Anna Berger, Thomas Gruber, Julia Hofmann)
- `ExtraRepository extends JpaRepository<ExtraEntity, Long>`

**Accepted:** Fully accepted.

**Human reasoning:** The constraint "there is only one hotel" shaped the seeder design — rather than generating a hotel entity, the AI kept hotel data static in the mapper (matching our earlier API design decision). The idempotency guard (`count() > 0`) was correct behavior and we verified it before accepting.

---

### 6. End-to-End Tests

**Task:** Write tests that verify seeded data is present and the full architecture works end to end against a real MySQL container.

**Prompt:**
> write tests to verify the seeded data is there and our architecture works end to end

**What was generated:**
- `EndToEndTest` — `@SpringBootTest @AutoConfigureMockMvc @Testcontainers`, MySQL container via `@ServiceConnection`; 11 tests covering room count, titles, prices, extras per room, availability, `POST /v1/bookings` (201 + 404), seeded guest email containment

**Accepted:** Fully accepted.

**Modified during implementation:** The AI initially wrote fragile JSONPath filter expressions and a brittle booking-count assertion. Both were replaced: direct `/v1/rooms/{id}` calls instead of filter expressions, email-containment check instead of exact count. These changes were AI-initiated corrections made during implementation — we reviewed and confirmed them.

**Human reasoning:** We asked for tests that "verify the seeded data is there and our architecture works end to end" — a deliberately broad brief that gave the AI latitude to decide what mattered to test. We then reviewed each test for brittleness and the AI self-corrected two cases before we needed to ask.

---

### 7. Rename Plural Class Names to Singular

**Task:** Rename `BookingsService` → `BookingService`, `RoomsController` → `RoomController`, etc. across all components.

**Prompt:**
> refactor plural class names like BookingsService to BookingService

**What was generated:**
- All 8 plural-named source files and 2 plural-named test files replaced with singular equivalents
- Internal field and variable references updated throughout

**Accepted:** Fully accepted.

**Human reasoning:** This was a naming consistency decision we made after reviewing the code. Spring conventions and our ER diagram use singular names (`room`, `booking`) — the class names should match. This was pure refactoring, no new behavior.

---

### 8. Clean Architecture Review and Violation Fixes

**Task:** Review the full codebase against Clean Architecture guidelines, then fix the identified violations.

**Prompts:**
> review the project on the clean architecture guidelines again
> 1 and 6 [fix cross-component dependency and availability stub]
> [interrupted] stop implementing features that are not yet implemented, stubs are ok
> check again against clean architecture
> 1-4 [fix remaining violations]

**What was identified and fixed:**

| # | Violation | Fix |
|---|---|---|
| 1 | `BookingServiceImpl` injected `RoomRepository` directly (cross-component) | Replaced with `RoomService` |
| 2 | Business validation (email match, checkout order) in `BookingController` | Moved to `@EmailsMatch` and `@CheckOutAfterCheckIn` class-level `@Constraint` validators on `BookingRequestDto`; duplicate `checkIn` check removed |
| 3 | `RoomService.findAll()` returned `Page<RoomEntity>` (Spring Data type in service interface) | Replaced with `PageResult<T>` record in `common/` |
| 4 | `DataSeeder` in root application package | Moved to `config/` package |
| 5 | Dead `io.swagger.*` generated code (12 files) | Deleted |

**Accepted:** Fully accepted. All 35 tests continued to pass after each fix.

**Human reasoning:** This session involved the most active human steering of any session. We ran the clean architecture review twice to ensure nothing was missed the first pass. When the AI started implementing real availability query logic (item 6 from the first review), we interrupted immediately — "stop implementing features that are not yet implemented, stubs are ok" — because the goal was fixing architecture violations, not advancing features. We then selected only items 1–4 from the second review, explicitly leaving the availability logic as a known stub for a future sprint. This kind of selective, sequenced engagement — review, interrupt, re-review, select — was entirely human-driven.

---

## Summary

| # | Task | Accepted | Modified | Rejected |
|---|------|----------|----------|----------|
| 1 | Create RoomSelectionView.vue |  | ✓ (added validation, etc.) | |
| 2 | Split View into components |  | ✓ (added validation, added pagination, small fixes) | |
| 1 | Fix CI (H2 for application test) | ✓ | | |
| 2 | Interface–implementation pattern | ✓ | | |
| 3 | Schema creation tests (H2 + MySQL) | ✓ | | |
| 4 | Entity and ER diagram alignment | ✓ | | |
| 5 | DataSeeder with initial fixtures | ✓ | | |
| 6 | End-to-end tests | ✓ | | |
| 7 | Rename plural class names to singular | ✓ | | |
| 8 | Clean architecture review and fixes | ✓ | | |

---

## Artefacts Produced by AI

- `./frontend/src/views/RoomSelectionView.vue`
- `./frontend/src/components/FilterBar.vue`
- `./frontend/src/components/DatePickerModal.vue`
- `./frontend/src/components/RoomList.vue`
- [`backend/src/main/java/.../config/DataSeeder.java`](backend/src/main/java/at/technikumwien/mse25/awt/hotelmario/config/DataSeeder.java) — startup fixture seeder
- [`backend/src/main/java/.../common/PageResult.java`](backend/src/main/java/at/technikumwien/mse25/awt/hotelmario/common/PageResult.java) — framework-agnostic pagination wrapper
- [`backend/src/test/.../SchemaCreationTest.java`](backend/src/test/java/at/technikumwien/mse25/awt/hotelmario/SchemaCreationTest.java) — H2 DDL verification
- [`backend/src/test/.../MysqlSchemaCreationTest.java`](backend/src/test/java/at/technikumwien/mse25/awt/hotelmario/MysqlSchemaCreationTest.java) — MySQL DDL verification
- [`backend/src/test/.../EndToEndTest.java`](backend/src/test/java/at/technikumwien/mse25/awt/hotelmario/EndToEndTest.java) — full-stack integration tests

---

# Milestone 2 — Progress Status

*Assessed: 2026-05-23*

## Deliverables

| Deliverable | Status | Notes |
|---|---|---|
| U1: Hotel website (landing page, about, imprint) | ❌ Not started | Frontend only — no Vue project exists |
| U2: Room selection — backend | ✅ Complete | `GET /v1/rooms` (paginated), `GET /v1/rooms/{roomId}`, 5 seeded rooms with extras and icons |
| U2: Room selection — frontend | ❌ Not started | No components, no Pinia store, no Axios integration |
| U3: Availability check — backend | ✅ Complete (stub) | `GET /v1/rooms/{roomId}/availability` responds correctly; always returns `available: true` pending real query |
| U3: Availability check — frontend | ❌ Not started | No date picker, no availability UI |
| Working backend | ✅ Complete | Spring Boot 4, MySQL, Docker Compose, 35 tests passing |
| Working frontend | ❌ Not started | `scripts/build-frontend.sh` contains only a placeholder echo |
| Frontend: Atomic Design structure | ❌ Not started | No Vue project scaffolded |
| Frontend: Pinia state management | ❌ Not started | — |
| Frontend: Axios API calls | ❌ Not started | — |
| Updated REST API docs (`openapi.yaml`) | ✅ Complete | All 4 endpoints defined with full request/response schemas |
| Updated DB model (`er-diagram.md`) | ✅ Complete | 4 tables: `room`, `extra`, `room_extra`, `booking` |
| CI pipeline | ✅ Complete | GitHub Actions for backend build and Docker |

## Backend — What Is Built

**Rooms API**
- `GET /v1/rooms?page=0&size=5` → paginated `RoomPage` with extras (title, description, pricePerNight, imageUrl, extras[name, icon])
- `GET /v1/rooms/{id}` → single room or 404
- `GET /v1/rooms/{id}/availability?checkIn=...&checkOut=...` → `{ available: true }` (stub)

**Bookings API**
- `POST /v1/bookings` → validates input (email match, date order, Bean Validation), persists, returns `BookingConfirmation` with hardcoded hotel details

**Seeded data:** 5 rooms (€89.99–€279.99), 7 extras, 3 bookings

**Architecture:** Clean Architecture — service interfaces, DTO constraint validators, framework-agnostic `PageResult<T>`, component packages, 35 tests

## What Is Still Missing for M2

1. **Vue.js 3 + Ionic project** — needs to be scaffolded (Vite, TypeScript, Ionic)
2. **U1 pages** — landing page, about, imprint (static content, no backend dependency)
3. **U2 frontend** — room list with pagination, extras with Bootstrap Icons, `rooms` Pinia store, Axios call to `GET /v1/rooms`
4. **U3 frontend** — date picker integrated into room cards/detail, availability feedback, Axios call to `GET /v1/rooms/{id}/availability`
5. **U3 backend** — real overlap query in `RoomServiceImpl.isAvailable()` against `BookingRepository`

---
# Assignment Frontend Prototype

## Tool

**ChatGPT**
- Model: **GPT-5.5**
- Date: 2026-05-24

---

## Usage Log

### 1. Creation of frontend HTML/CSS/JS files following the paperprototype
**Task:** Creation of frontend HTML/CSS/JS files following the paperprototype

**Prompt:** Create code for .html files and pages. Only HTML, CSS, and JS should be used for design. Create the individual pages for me. [Paperprototype PDF uploaded]

**What was generated:**
- Projectstrukture overview:
  - hotel-booking-app/
    - index.html
    - rooms.html
    - room-details.html
    - booking.html
    - review.html
    - confirmation.html
    - about.html
    - imprint.html
    - css/
      - style.css
    - js/
      - app.js
    - images/
      - room1.jpg
      - room2.jpg
      - room3.jpg
      - hotel.jpg
- style.css Stylesheet with styling for all HTML files
- app.js JavaScript code for booking.html page: checking if all fields are filled (not containing check if content is correct format)
- index.html
- rooms.html
- room-details.html
- booking.html
- review.html
- confirmation.html
- about.html

**Modified:** All generated html pages needed to be modified, because pages are not equivalent in content, layout, fields to the requirement given, the paperprototype. Therefore manual overhaul of all pages fixing inconsistent layout, missing content, incorrect content, missing fields. In total were the generated pages useful because of the generated basic structure. style.css modified to create consistency between pages and a better looking layout. Alled html and js code for slideshows on index.html, about.html, room-details.html, confirmation.html. No images were generated, therefore I created five placeholder images myself (size: 1920x1080) and a smaller image (44x44) as icon placeholder for the room extras. Imprint page not generated. Inconsistent appearance of the footer on the pages; settle with one footer design and applied this design to all other pages.


### 2. Creation of imprint HTML page following the paperprototype

**Task:** Create the legal notice page. Supplement any missing information with relevant and appropriate data, as in the following example.

**Prompt:** Create the legal notice page. Supplement any missing information with relevant and appropriate data, as in the following example. [attached the example imprint page for a sole proprietorship company not registered in the commercial register from the WKO page]

**What was generated:**
- imprint.html

**Modified:** The basic structure of the imprint.html was acceptable. The contents of the page were generated in german, therefore manually translated into english. The data of the imprint were not these from the paperprototype, therefore manually replaced with the correct imprint data from the paperprototype. 

---

## Summary

| # | Task | Accepted | Modified | Rejected |
|---|------|----------|----------|----------|
| 1 | Creation of frontend HTML/CSS/JS files following the paperprototype | | ✓ | |
| 2 | Creation of imprint HTML page following the paperprototype | | ✓ | |

---


# U2 / U3 — Room Availability Backend & Frontend (#17 #20 #21 #22 #23 #24 #25)

## Tool

**Claude Code** (Anthropic)
- Model: **Claude Sonnet 4.6** (`claude-sonnet-4-6`)
- Interface: Claude Code VS Code extension (interactive agent)
- Date: 2026-05-29

---

## Usage Log

### 1. Room Availability Repository (#20)

**Task:** Create a dedicated JPA repository for the availability use case with a JPQL overlap-detection query.

**Prompt:**
> create a use case specific controller, service and repository

**What was generated:**
- `RoomAvailabilityRepository` extending `JpaRepository<RoomEntity, Long>` with a custom `isAvailableForPeriod` JPQL query using `NOT EXISTS` to detect booking overlaps (`checkIn < :checkOut AND checkOut > :checkIn`)

**Accepted:** Fully accepted.

**Human reasoning:** We directed the AI to create a fully separate `RoomAvailability*` layer rather than extending the existing `RoomRepository`, keeping availability logic isolated per clean architecture. The overlap condition (`checkIn < checkOut AND checkOut > checkIn`) was verified manually against edge cases before accepting.

---

### 2. Room Availability Service (#21)

**Task:** Implement service that returns `Optional<Boolean>` to distinguish room-not-found (404) from unavailable (false).

**Prompt:**
> is #21 fulfilled?

**What was generated:**
- `RoomAvailabilityService` interface and `RoomAvailabilityServiceImpl` using `Optional<Boolean>` — `Optional.empty()` signals 404, `Optional.of(false)` signals booked

**Accepted:** Fully accepted.

**Human reasoning:** Using `Optional<Boolean>` as the return type was a deliberate design choice to encode two distinct failure modes without throwing exceptions. The AI proposed this pattern; we confirmed it was the right abstraction before accepting.

---

### 3. Room Availability Controller (#22)

**Task:** Implement REST controller for `GET /v1/rooms/{roomId}/availability` with date range validation returning 400.

**Prompt:**
> add validation for date range input errors

**What was generated:**
- `RoomAvailabilityController` implementing `RoomAvailabilityApi`
- `InvalidDateRangeException` in `common/exception/` with field name and message
- Handler in `GlobalExceptionHandler` returning 400 with `ValidationErrorResponseDto`
- Validation in controller: `checkIn` must be today or later, `checkOut` must be after `checkIn`
- Controller slice tests (5 cases) and E2E tests (3 cases) added to `EndToEndTest`

**Accepted:** Fully accepted.

**Human reasoning:** Availability concern was completely separated from `RoomService` and `RoomController` by human decision — the AI implemented the separation as directed. The `InvalidDateRangeException` pattern was chosen to reuse the existing `GlobalExceptionHandler` infrastructure rather than validating inside each controller.

---

### 4. Real API Calls — Room List and Availability (#17 #23 #24 #25)

**Task:** Replace mock data in `RoomSelectionView` with real backend calls; fix nginx reverse proxy.

**Prompt:**
> now add the fetch calls to the backend, see docker-compose.yml for hostname / api lives under "/api"

**What was generated:**
- `api.ts` — typed DTOs (`RoomDto`, `RoomPageDto`, `AvailabilityResponseDto`) and functions `getRooms()`, `checkRoomAvailability()`
- `RoomSelectionView.vue` — `fetchRooms()` calls real API on mount; `applyFilters()` checks availability for all rooms in parallel; surfaces backend 400 errors with field-level message; shows empty-state when no rooms match
- `RoomList.vue` — availability badge hidden until dates are checked (`available !== null`)
- `.dockerignore` — excludes `node_modules` from build context
- `nginx.conf` — `/api/` proxy block to `http://spring-boot:8080`; trailing slash bug fixed after discovering Spring Boot context path is `/api`

**Accepted:** Fully accepted after manual testing confirmed rooms load and availability badges appear correctly.

**Human reasoning:** The nginx `proxy_pass` trailing slash bug (stripping `/api/` before forwarding, causing 404 on all backend calls) was identified by running a live curl test — not by code review. The AI diagnosed the root cause (`server.servlet.contextPath=/api` in `application.properties`) and applied the one-character fix. We verified with a follow-up curl before accepting.

---

## Summary

| # | Task | Accepted | Modified | Rejected |
|---|------|----------|----------|----------|
| 1 | Room availability repository (#20) | ✓ | | |
| 2 | Room availability service (#21) | ✓ | | |
| 3 | Room availability controller + validation (#22 #25) | ✓ | | |
| 4 | Real API calls + nginx fix (#17 #23 #24) | ✓ | | |

---

## Artefacts Produced by AI

- [`backend/.../rooms/repository/RoomAvailabilityRepository.java`](backend/src/main/java/at/technikumwien/mse25/awt/hotelmario/components/rooms/repository/RoomAvailabilityRepository.java)
- [`backend/.../rooms/service/RoomAvailabilityService.java`](backend/src/main/java/at/technikumwien/mse25/awt/hotelmario/components/rooms/service/RoomAvailabilityService.java)
- [`backend/.../rooms/service/RoomAvailabilityServiceImpl.java`](backend/src/main/java/at/technikumwien/mse25/awt/hotelmario/components/rooms/service/RoomAvailabilityServiceImpl.java)
- [`backend/.../rooms/api/v1/RoomAvailabilityApi.java`](backend/src/main/java/at/technikumwien/mse25/awt/hotelmario/components/rooms/api/v1/RoomAvailabilityApi.java)
- [`backend/.../rooms/api/v1/RoomAvailabilityController.java`](backend/src/main/java/at/technikumwien/mse25/awt/hotelmario/components/rooms/api/v1/RoomAvailabilityController.java)
- [`backend/.../common/exception/InvalidDateRangeException.java`](backend/src/main/java/at/technikumwien/mse25/awt/hotelmario/common/exception/InvalidDateRangeException.java)
- [`frontend/src/services/api.ts`](frontend/src/services/api.ts)
- [`frontend/src/views/RoomSelectionView.vue`](frontend/src/views/RoomSelectionView.vue)
- [`nginx.conf`](nginx.conf)
- [`.dockerignore`](.dockerignore)
