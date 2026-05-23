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

**Accepted:** Fully accepted. Richardson Maturity Level 2 compliance, pagination design, and error response structure were reviewed and kept as generated.

---

### 2. Add Hotel with Contact Options and Directions

**Task:** Extend the API spec with a Hotel resource including contact and directions, and link it to rooms.

**Prompt:**
> add a Hotel with Contact Options and Directions, add Hotel Model to the room

**What was generated:**
- New `GET /hotel` endpoint
- New schemas: `Hotel`, `Address`, `ContactOptions`, `Direction`
- `hotel` field added to `Room` schema

**Accepted/Modified/Rejected:** Partially rejected — see entry 3 below.

---

### 3. Undo Hotel Changes

**Task:** Revert the previous changes entirely.

**Prompt:**
> undo the last changes

**What happened:** All changes from entry 2 were reverted — `GET /hotel` endpoint, `Hotel`, `Address`, `ContactOptions`, `Direction` schemas removed, `hotel` removed from `Room`.

---

### 4. Add Hotel as Static Object to BookingConfirmation

**Task:** Re-add the hotel data, but only embedded as a static object inside `BookingConfirmation`, without a separate endpoint or linking it to `Room`.

**Prompt:**
> add the single hotel with attributes ContactOptions and Directions as a static object to BookingConfirmation

**What was generated:**
- `Hotel`, `Address`, `ContactOptions`, `Direction` schemas added
- `hotel` field added to `BookingConfirmation` (required)
- No separate `/hotel` endpoint

**Accepted:** Fully accepted. Embedding hotel info in the confirmation response is the correct design for U5 (directions and contact on the confirmation page).

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

---

### 6. Add Icon to Extra

**Task:** Add an icon field to the `Extra` schema to support icon display in the room listing (U2).

**Prompt:**
> add icon to Extra

**What was generated:**
- `icon` field (string, Bootstrap Icon name) added to `Extra` schema

**Accepted:** Fully accepted.

---

### 7. Generate ER Diagram

**Task:** Generate an ER diagram covering Room, Extra, Booking, and a M:N mapping table between Room and Extra.

**Prompt:**
> generate an ER Diagram with Room, Extra and Booking Confirmation, create a M:N Mapping table between room and extra

**What was generated:**
- `er-diagram.md` with a Mermaid ER diagram
- Entities: `ROOM`, `EXTRA`, `ROOM_EXTRA` (M:N mapping), `BOOKING`
- Relations: `ROOM ||--o{ ROOM_EXTRA`, `EXTRA ||--o{ ROOM_EXTRA`, `ROOM ||--o{ BOOKING`

**Accepted:** Fully accepted. The decision to not include a `HOTEL` table (static data, single instance) was reviewed and confirmed as correct.

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

---

### 2. Anfrage an KI + bereitgestellte Dokumente

**Task:** Bereitstellung der Projektdokumente und Erklärung der gewünschten Prüfung.

**Prompt:**
API_specification.pdf; DB_Design.pdf; Paperprototype.pdf; ProjektSpezifikation2026.pdf
folgend werde ich user stories schicken und du wirst mir bitte sagen auf grundlage der eigenen projekt-dokumente und der projektspezifikation, ob etwas fehlt/unvollständig ist/nicht passt an inhalt, kriterien, user stories, usw.

**What was generated:**

KI erklärt, dass sie User Stories anhand von drei Bereichen prüft:

  - Form der User Story
    - korrektes Format, 
    - Verständlichkeit.

- Abgleich mit Projektspezifikation U1–U5 Abdeckung

  - Details, Recommended Approach, Definition of Done

- Qualität der Acceptance Criteria

  - Testbarkeit

  - Vollständigkeit

  - Konkretheit (API, Felder, Verhalten)

**Accepted**  

---

### 3. KI-Analyse der User Stories (Story-für-Story)
**Task:** Bereitstellung der User Stories für Landing Page, Room Selection, Availability, Booking, Confirmation. Bewertung der User Stories gegen Projektspezifikation + eigene Dokumente.

**Prompt:** 

- Bisherige User Stories aus Github Kanban-Board

- Diese features (user stories) teilen wir jeweils in einzelne sub-Tasks (thematlich eingeteilt) auf mit einzelnen großen unter-Aufgaben-Themen als Aufzählungen.

**What was generated:**

*U1 – Landing Page*
Gut: Story passt, responsive, statische Seiten, Content.

Fehlt / Risiko:

- Mindestseiten nicht in AC (Landing, About, Imprint)

- Navigation nicht definiert

Verbesserung: AC für Navigation ergänzen.

*U2 – Room Selection*
Gut: Pagination, Icons, 5 Rooms, API‑Bezug.

Kritisch:

- API liefert alle Rooms, nicht nur „available“ → Story falsch formuliert

- Fehlerhandling fehlt

Verbesserung: Story korrigieren + AC für API‑Fehler ergänzen.

*U3 – Check Availability*
Gut: Date Picker, API, Feedback.

Kritisch:

- Validierungsregeln unvollständig (checkIn ≥ today, checkOut > checkIn)

Verbesserung: AC für Validierung + Fehlerfälle ergänzen.

*U4 – Booking*
Gut: Review Step, Felder, Validation.

Kritisch:

- API‑Regeln fehlen (email == confirmation, room availability, 400/409)

- Erfolg unklar (Booking ID? Details?)

Verbesserung: AC für API‑Regeln + Erfolgsdetails ergänzen.

*U5 – Confirmation*
Gut: Inhalte, Print, Map, Failure.

Kritisch:

- API‑Daten nicht als verpflichtend testbar formuliert

- Print‑Kriterium zu vage

Verbesserung: AC für Hotel‑Daten + präzises Print‑Verhalten ergänzen.

Globaler Kritischer Punkt:

Definition of Done fehlt bei ALLEN User Stories

Spezifikation verlangt DoD explizit

**Modified:** Result of AI was used partially. Some points were used, some deleted, most of the new content adapted to existing project before using.

---

## Summary

| # | Task | Accepted | Modified | Rejected |
|---|------|----------|----------|----------|
| 1 | Generate Backlog Items | ✓ | | |
| 2 | Anfrage an KI + bereitgestellte Dokumente | ✓ |  | |
| 3 | KI-Analyse der User Stories (Story-für-Story) |  | ✓ | |
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

---

### 3. Startup Scripts

**Task:** Generate cross-platform startup scripts for local development that work with both Docker and Podman.

**Prompt:**
> generate a startup.bat and startup.sh file, expect a docker or podman installation

**What was generated:**
- `startup.sh` (66 lines) — detects Docker or Podman, starts services
- `startup.bat` (83 lines) — Windows equivalent with the same detection logic

**Accepted:** Fully accepted.

---

### 4. BUILD.md Documentation

**Task:** Generate build documentation referencing non-Oracle JDKs.

**Prompt:**
> create a BUILD.md documentation, reference non oracle jdk like zulu or eclipse tamurin

**What was generated:**
- `BUILD.md` (545 lines) — full build guide covering prerequisites (Zulu / Eclipse Temurin JDK 25), Gradle commands, Docker setup, local development workflow, environment variables, and troubleshooting

**Accepted:** Fully accepted.

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

---

### 5. Full Clean Architecture Refactoring (Component + Layer Structure)

**Task:** Refactor the entire backend to match the lecturer's Clean Architecture pattern: component packages, versioned API layer, entities, repositories, services, DTOs, mappers.

**Prompt:**
> come up with a plan to refactor accordingly [to the lecturer's guidelines: component packages with v1 versioning, @Entity models named *Entity with Lombok, @Repository JpaRepository, @Service, @RestController + @RequestMapping, DTOs with @Builder @Value @JsonProperty]
> do it all

**What was generated:**

*Dependencies (`build.gradle`):*
- Added `spring-boot-starter-data-jpa`, Lombok (`compileOnly` + `annotationProcessor`), `lombok.config` with `config.stopBubbling = true` and `lombok.jacksonized.jacksonVersion += 3`
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
- Rooms: `RoomDto`, `RoomPageDto`, `AvailabilityResponseDto`, `ExtraDto` — all `@Value @Builder @JsonProperty`; `@JsonFormat` on date fields
- Bookings: `BookingRequestDto`, `BookingConfirmationDto`, `AddressDto`, `ContactDto`, `HotelDto`
- Common: `ValidationErrorResponseDto`, `FieldErrorDto`
- `BookingRequestDto` uses `@Data @Builder @NoArgsConstructor @AllArgsConstructor` instead of `@Value` — necessary because Jackson 3 cannot deserialize Lombok `@Value` immutable classes without `@Jacksonized` targeting the Jackson 3 namespace (Lombok 1.18.46 defaults to Jackson 2 annotations when both are on classpath)

*Mapper classes (versioned, in `api/mapper/v1/`):*
- `RoomMapper` — `@Component`, manual `RoomEntity → RoomDto` and `ExtraEntity → ExtraDto`
- `BookingMapper` — `@Component`, `BookingRequestDto → BookingEntity` and `BookingEntity → BookingConfirmationDto`; hotel data hardcoded as stub until a `HotelEntity` exists

*Controllers (versioned, in `api/v1/`):*
- `RoomsController` — `@RestController @RequestMapping("/v1/rooms")`, injects `RoomsService` + `RoomMapper`
- `BookingsController` — `@RestController @RequestMapping("/v1/bookings")`, injects `BookingsService` + `BookingMapper`, cross-field validation remains in controller

*Exception handling:*
- `GlobalExceptionHandler` — `@RestControllerAdvice` in `common/api/v1/`, handles `MethodArgumentNotValidException` globally

*Deletion:*
- All `io.swagger.model.*` classes
- `io.swagger.api.BookingsApi`, `RoomsApi`, `ApiException`, `NotFoundException`
- Old `adapter/web/BookingsController`, `RoomsController`
- `validation/NotUndefined`, `NotUndefinedValidator`

*application.properties fix:*
- Removed `spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect` — class was removed in Hibernate 6+; Spring Boot 4 (Hibernate 7) auto-detects the dialect

**Accepted:** Fully accepted after build verification (`BUILD SUCCESSFUL`).

**Modified by AI during implementation:**
- Discovered `@WebMvcTest` moved packages in Spring Boot 4 (`org.springframework.boot.test.autoconfigure.web.servlet` → `org.springframework.boot.webmvc.test.autoconfigure`) — corrected autonomously
- Discovered `lombok.jacksonized.jacksonVersion += THREE` is invalid syntax; correct value is `3` — corrected after build output

---

### 6. Controller Tests with @WebMvcTest

**Task:** Write `@WebMvcTest` slice tests for both controllers, mocking the service and mapper layer.

**Prompt:**
> write a controller test class for rest endpoints

**What was generated:**
- `RoomsControllerTest` — 6 tests: paged list (default params), custom page/size params, find by id found, find by id not found (404), availability available, availability unavailable; uses `@MockitoBean` for `RoomsService` and `RoomMapper`
- `BookingsControllerTest` — 7 tests: valid request (201), email mismatch (400 with field error), checkout before checkin (400 with field error), missing `firstName` (400 bean validation), invalid email format (400 bean validation), past `checkIn` (400 via `@FutureOrPresent`), room not found (404); uses `@MockitoBean` for `BookingsService` and `BookingMapper`
- Dynamic date strings (`LocalDate.now().plusDays(...)`) used for date-sensitive tests to avoid hardcoded future dates becoming stale

**Accepted:** Fully accepted after all 14 tests passed.

**Modified:** During implementation, two Spring Boot 4 compatibility issues were found and fixed:
1. `@WebMvcTest` import updated to new package (see task 5)
2. `BookingRequestDto` switched from `@Value @Builder @Jacksonized` to `@Data @Builder @NoArgsConstructor @AllArgsConstructor` to enable Jackson 3 deserialization

---

### 7. Clean Architecture Verification

**Task:** Verify the refactored codebase against each of the lecturer's Clean Architecture criteria.

**Prompt:**
> verify clean architecture

**What was generated:**
- Structured review table mapping each criterion (component packages, versioning, entities, repositories, services, controllers, DTOs) to the current implementation
- Identified 5 remaining issues: orphaned `HomeController` in `adapter/web/`, dead `checkIn` validation branch in `BookingsController`, `ResponseEntity<?>` wildcard return type, `BookingsService` directly importing `RoomRepository` across component boundary, hotel data hardcoded inside `BookingMapper`

**Accepted:** Used as reference for known technical debt. Issues were documented but not all fixed (out of scope for this issue/sprint).

---

## Summary

| # | Task | Accepted | Modified | Rejected |
|---|------|----------|----------|----------|
| 1 | Clean Architecture review of boilerplate | ✓ | | |
| 2 | Package reorganization + phase 1 refactoring | ✓ | ✓ (exception handling) | |
| 3 | Stub controllers with OpenAPI example data | ✓ | | |
| 4 | BookingsController POST logic | ✓ | | |
| 5 | Full Clean Architecture refactoring | ✓ | ✓ (Spring Boot 4 / Jackson 3 compatibility) | |
| 6 | Controller tests with @WebMvcTest | ✓ | ✓ (DTO style for Jackson 3) | |
| 7 | Clean Architecture verification | ✓ | | |

---

## Artefacts Produced by AI

- [`backend/src/main/java/.../components/`](backend/src/main/java/at/technikumwien/mse25/awt/hotelmario/components/) — full component package structure (entities, repositories, services, DTOs, mappers, controllers)
- [`backend/src/main/java/.../common/`](backend/src/main/java/at/technikumwien/mse25/awt/hotelmario/common/) — shared DTOs and GlobalExceptionHandler
- [`backend/src/test/.../components/`](backend/src/test/java/at/technikumwien/mse25/awt/hotelmario/components/) — RoomsControllerTest, BookingsControllerTest
- [`backend/lombok.config`](backend/lombok.config) — Lombok configuration
- [`backend/build.gradle`](backend/build.gradle) — updated dependencies

---