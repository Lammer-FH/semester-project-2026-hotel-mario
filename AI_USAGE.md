# AI Usage Documentation

Group AE — Boutique Hotel Technikum booking application.
All AI-assisted work is documented here per issue and session.

---

## Planning & Specification — 2026-05-03 | Claude Sonnet 4.6

### 1. REST API Specification
**Generated:** Full `openapi.yaml` covering rooms (list, detail, availability), bookings (create, confirm), and all associated schemas at Richardson Maturity Level 2.  
**Outcome:** Accepted.  
**Human decision:** API-first approach was our choice. We reviewed the pagination structure, error response format, and RMM Level 2 design before committing.

### 2. Hotel Resource Design (Three Iterations)
First iteration added a `GET /hotel` endpoint with hotel data embedded on every room. We rejected this — repeating static data on every room response wastes bandwidth, and a separate `/hotel` endpoint requires an extra round-trip just for contact details. Second iteration removed both and left no hotel endpoint. Third iteration added hotel as a static embedded object inside `BookingConfirmation` only — the one place it is actually needed.  
**Outcome:** First two rejected; third accepted.  
**Human decision:** Hotel data is static and belongs on the confirmation page, not as a REST resource.

### 3. Simplify Extra Icon and Direction Schema
**Generated:** `icon` field added to `Extra`; `Direction` schema collapsed from a structured array to a plain string; `website` removed from `ContactOptions`.  
**Outcome:** Accepted.  
**Human decision:** A structured `Direction` array would be over-engineering for a single paragraph of text. The icon field was added so the backend drives icon selection rather than hardcoding it in the frontend.

### 4. ER Diagram
**Generated:** `er-diagram.md` — Mermaid ERD covering `ROOM`, `EXTRA`, `ROOM_EXTRA` (M:N), `BOOKING`.  
**Outcome:** Accepted (with `HOTEL` table removed by us).  
**Human decision:** One hotel, static data — no database table needed.

---

## Backlog — 2026-05-08 | Claude Sonnet 4.6 + GitHub Copilot (GPT-5.5, VS Code)

### 1. Generate Backlog Items
**Generated:** GitHub Issues 3–7 as user story drafts.  
**Outcome:** Accepted after review; subtask breakdown done manually.  
**Human decision:** Granular task decomposition requires knowledge of team capacity that AI cannot infer.

### 2. User Story Review
AI reviewed U1–U5 against the project spec and our documents, flagging gaps in Definition of Done, a U2 wording bug ("available rooms" — API returns all rooms), and missing validation rules in U3–U4.  
**Outcome:** Modified — gaps relevant to the current milestone adopted, others deferred.  
**Human decision:** We filtered against what the spec actually required. The U2 wording bug was correct and fixed.

---

## Issue #11 — CI Configuration — 2026-05-08 + 2026-05-23 | GitHub Copilot (GPT-5.5, VS Code) + Claude Sonnet 4.6

### 1. Initial CI Script (GitHub Copilot, GPT-5.5)
**Generated:** `.github/workflows/ci.yml` skeleton.  
**Outcome:** Modified — Node dependency commented out (frontend not yet implemented); PR trigger added.

### 2. Full CI Pipeline (Claude Sonnet 4.6)
**Generated:** `docker-build.yml`, `docker-compose-test.yml`, `build-docker.sh`, `build-frontend.sh`.  
**Outcome:** Modified — trigger conditions and branch filters adjusted manually across several commits.  
**Human decision:** AI used generic project structure assumptions; we corrected them to match the actual layout.

---

## Issue #12 — Set Up Spring Boot Backend — 2026-05-23 | Claude Sonnet 4.6

### 1. Docker Compose + Dockerfile
**Generated:** `docker-compose.yml`, `Dockerfile`, `application.properties` wired to MySQL container.  
**Outcome:** Accepted; base image updated to JDK 25 in a follow-up prompt.

### 2. Startup Scripts
**Generated:** `startup.sh` / `startup.bat` detecting Docker or Podman.  
**Outcome:** Accepted.  
**Human decision:** Explicit requirement to support both runtimes — only we knew team members use different container environments.

### 3. BUILD.md
**Generated:** Full build guide (prerequisites, Gradle, Docker, local dev, troubleshooting) referencing Zulu and Eclipse Temurin.  
**Outcome:** Accepted.  
**Human decision:** Non-Oracle JDK references were our requirement due to Oracle licensing concerns.

---

## Issue #52 — Debug Docker Setup — 2026-05-23 | Claude Sonnet 4.6

**Generated:** `Dockerfile.debug` (JDWP port 5005), `docker-compose.debug.yml`, Remote Debugging section in `BUILD.md`.  
**Outcome:** All accepted.

---

## Issue #44 — Clean Architecture Refactoring — 2026-05-23 | Claude Sonnet 4.6

### 1. Architecture Review of Boilerplate
**Generated:** Structured review identifying violations: wrong namespace, generated classes used as DTOs, redundant annotations, CORS filter anti-pattern.  
**Outcome:** Used as basis for the refactoring plan.  
**Human decision:** We scoped the review to structural issues only ("ignore missing business logic") to keep the output actionable.

### 2. Package Reorganization — Phase 1
**Generated:** Generated controller implementations, CORS filter, and unused configuration classes deleted; application code moved out of `io.swagger.*` into the correct namespace; `WebConfig`, `OpenApiConfig` created; `@ComponentScan` removed.  
**Outcome:** Accepted. `NotUndefinedValidator` exception handling changed from `e.printStackTrace()` to `throw new RuntimeException` by us before accepting.  
**Human decision:** We scoped this first pass to namespace and structural cleanup only — no entities or persistence yet — to keep the change set reviewable.

### 3. Controller Stub Data + POST Logic
**Generated:** `GET` endpoints returning spec-matching example data; `POST /bookings` with `@Valid`, cross-field validation, `201` response.  
**Outcome:** Accepted.  
**Human decision:** "No service or persistence yet" was deliberate — validate HTTP semantics in isolation before touching the database layer.

### 4. Full Clean Architecture Refactoring (Component + Layer Structure)
**Generated:** JPA and Lombok dependencies added; all entity classes (`RoomEntity`, `ExtraEntity`, `BookingEntity`); repository interfaces; service interfaces and implementations; versioned DTOs (`RoomDto`, `RoomPageDto`, `ExtraDto`, `BookingRequestDto`, `BookingConfirmationDto`, etc.); mapper classes; versioned controllers; `GlobalExceptionHandler` in `common/`.  
**Outcome:** Accepted after `BUILD SUCCESSFUL`. Spring Boot 4 `@WebMvcTest` package move corrected autonomously by AI during implementation.  
**Human decision:** Two-step approach — "come up with a plan" reviewed and confirmed before "do it all" was issued. The architecture plan specified component packages with `v1` versioning, `*Entity` naming, Lombok, and `@Builder`/`@Value` DTOs — all defined by us before any code was written.

### 5. Controller Tests
**Generated:** `RoomsControllerTest` (6 cases), `BookingsControllerTest` (7 cases) using `@WebMvcTest`, dynamic dates.  
**Outcome:** Accepted after all 13 tests passed.

### 6. Clean Architecture Verification
**Generated:** Review table identifying 5 remaining violations (dead code, wildcard return type, cross-component dependency, hotel data in mapper).  
**Outcome:** Documented as known technical debt; not all fixed immediately.  
**Human decision:** Conscious prioritisation — violations that did not block the PR were deferred.

---

## Issue #9 & #10 — Frontend Setup — 2026-05-25 | Lumo (Proton)

**Generated:** `Dockerfile-Frontend`.  
**Outcome:** Modified — output path corrected from `/app/www` to `/app/dist`; build context path fixed.

---

## Milestone 2 — RoomSelectionView Frontend — 2026-05-23 | Claude Sonnet 4.6

### 1. Create RoomSelectionView.vue
**Generated:** `RoomSelectionView.vue` from paper prototype — booking filter at top, scrollable room list below, mock API placeholders.  
**Outcome:** Modified — date picker refactored to modal-based interaction; Ionic component registration fixed; reactivity corrected (ref unwrapping, `@ionChange`/`@ionInput` replacing `v-model`); date and price validation added with computed error messages.  
**Human decision:** Starting from a paper prototype attachment gave AI enough visual context to scaffold the layout. All validation logic and the modal interaction pattern were added manually after reviewing the generated output.

### 2. Split View into Components
**Generated:** `RoomSelectionView.vue`, `DatePickerModal.vue`, `FilterBar.vue`, `RoomList.vue` — view split into separate component files.  
**Outcome:** Modified — additional validation logic added; pagination implemented; minor breakages from the split fixed manually.  
**Human decision:** Component split was our decision once the monolithic view was working. We directed the split and repaired the integration points.

---

## Frontend Prototype — 2026-05-24 | GitHub Copilot (GPT-5.5, VS Code)

**Generated:** HTML/CSS/JS prototype (index, rooms, room-details, booking, review, confirmation, about pages).  
**Outcome:** Modified — all pages manually overhauled for content accuracy, layout consistency, missing fields. Imprint page generated separately with WKO example; translated to English and corrected.

---

## Issues #17–#19 — Room Selection Frontend — 2026-05-26–29 | GitHub Copilot

**Generated:** Initial structure for `RoomSelectionView.vue`, `FilterBar.vue`, `DatePickerModal.vue`, `RoomList.vue` with mocked data.  
**Outcome:** Modified — date validation, pagination, Ionic component fixes, reactivity corrections applied manually.

---

## Milestone 2 — Backend (U2/U3): Availability, Tests, Fixtures — 2026-05-23 | Claude Sonnet 4.6

### 1. Fix CI — H2 for Context Test
**Generated:** `backend/src/test/resources/application.properties` using H2 in-memory; `h2` added to `build.gradle`.  
**Outcome:** Accepted.  
**Human decision:** H2 for context load test keeps CI fast without requiring a MySQL container; actual DB behaviour tested separately with Testcontainers.

### 2. Interface–Implementation Pattern
**Generated:** `*Api` / `*Controller`, `*Service` / `*ServiceImpl`, `*Mapper` / `*MapperImpl` for both components.  
**Outcome:** Accepted.  
**Human decision:** Interface/implementation split was our architectural decision. AI implemented a pattern we had already chosen.

### 3. Schema and End-to-End Tests
**Generated:** `SchemaCreationTest` (H2, fast), `MysqlSchemaCreationTest` (Testcontainers, accurate), `EndToEndTest` (11 integration tests covering seeded data and full request cycle).  
**Outcome:** Accepted. Fragile assertions self-corrected by AI during implementation; reviewed and confirmed by us.  
**Human decision:** Two separate schema tests (fast + accurate) was a deliberate strategy — not merged into one.

### 4. Entity Alignment with ER Diagram
**Generated:** Singular table names (`room`, `extra`, `booking`), `BigDecimal pricePerNight` — aligned to ER diagram as source of truth.  
**Outcome:** Accepted.  
**Human decision:** "Don't update the ER diagram" was explicit — code had drifted, ER diagram was authoritative.

### 5. DataSeeder
**Generated:** `DataSeeder implements ApplicationRunner` — 7 extras, 5 rooms (€89.99–€279.99), 3 bookings, idempotency guard.  
**Outcome:** Accepted.

### 6. Rename Plural Class Names
**Generated:** `BookingsService` → `BookingService`, `RoomsController` → `RoomController` across all files.  
**Outcome:** Accepted — naming consistency with ER diagram and Spring conventions.

### 7. Clean Architecture Review and Violation Fixes
Five violations identified and fixed: cross-component `RoomRepository` injection in `BookingServiceImpl`; business validation moved to `@EmailsMatch` / `@CheckOutAfterCheckIn` constraint annotations; `Page<RoomEntity>` in service interface replaced with `PageResult<T>`; `DataSeeder` moved to `config/`; dead swagger-generated code deleted.  
**Outcome:** Accepted. All 35 tests passed after each fix.  
**Human decision:** Review was run twice to ensure completeness. When AI began implementing real availability logic during a fix session, we interrupted immediately — the goal was architecture violations only, not feature advancement.

---

## U2/U3 — Availability Backend & Frontend (#20–25) — 2026-05-29 | Claude Sonnet 4.6

### 1. Room Availability Repository, Service, Controller
**Generated:** `RoomAvailabilityRepository` with JPQL overlap query (`checkIn < :checkOut AND checkOut > :checkIn`); `RoomAvailabilityService` using `Optional<Boolean>` to distinguish 404 from unavailable; `RoomAvailabilityController` with `InvalidDateRangeException` and 400 responses; controller slice tests and E2E tests.  
**Outcome:** Accepted.  
**Human decision:** Fully separate `RoomAvailability*` layer (not extending `RoomRepository`) was our architecture call. The `Optional<Boolean>` pattern encodes two failure modes without exceptions — AI proposed it, we confirmed it.

### 2. Real API Calls + nginx Fix
**Generated:** Typed `api.ts` (DTOs, `getRooms`, `checkRoomAvailability`); `RoomSelectionView.vue` wired to real API; `nginx.conf` proxy block; `.dockerignore`.  
**Outcome:** Accepted after live curl test confirmed rooms load and badges appear.  
**Human decision:** nginx `proxy_pass` trailing-slash bug (stripping `/api/` before forwarding) was found by running a curl test, not code review. AI diagnosed the root cause and applied the fix; we verified before accepting.

---

## Issue #57 — Pinia State Management — 2026-05-29 | Claude Sonnet 4.6

**Generated:** `useRoomStore.ts` (rooms, selectedRoom, loading/error, fetchRooms, checkAvailability); `useFilterStore.ts` (dates, persons, price range, available-only, picker modal lifecycle); Pinia registered in `main.ts`; `RoomSelectionView.vue` refactored to use both stores; `frontend/.env.example`.  
**Outcome:** Accepted.  
**Human decision:** After reviewing AI's proposed split, we agreed on the store boundaries: API data in `useRoomStore`, form/modal state in `useFilterStore`. `selectedRoom` was added as the handoff for the future booking flow (U4). File naming (`useRoomStore.ts`) follows the Pinia convention from course materials.

---

## Milestone 2 — Frontend Polish, Atomic Design, Config & Seed Data — 2026-05-30 | Claude Sonnet 4.6

### 1. Ionicons Registration
**Generated:** `addIcons(...)` call in `main.ts` registering all icons used by the application.  
**Outcome:** Accepted.  
**Human decision:** We reported that icons were not rendering. AI diagnosed the root cause as a missing `addIcons` registration required by Vite's tree-shaken build, and registered the specific icon set used by the application rather than importing the full bundle.

### 2. Room Selection Desktop Layout
**Generated:** `FilterBar.vue` — responsive `ion-col` sizing (single row on desktop); `RoomList.vue` — horizontal card layout (image left 280px, content right); `RoomSelectionView.vue` — max-width container, nav in toolbar, stray link removed.  
**Outcome:** Accepted.  
**Human decision:** Horizontal card is a standard hotel booking UI pattern. Navigation placement in the toolbar was our explicit requirement to match `home.vue`.

### 3. Price Filter Wiring and Input Validation
**Generated:** `filteredRooms` computed extended to apply min/max price range; `priceError` extended to reject negatives; `min="0"` on inputs.  
**Outcome:** Accepted.

### 4. Availability Filter and Auto-Trigger Corrections
**Generated:** `onPickerApply()` auto-fires availability check after date confirm; `filteredRooms` guard prevents filtering when no dates entered; availability checkbox disabled until both dates are set.  
**Outcome:** Accepted.  
**Human decision:** Two issues were identified during testing and corrected: date changes required a separate button press before taking effect; the available-only filter was incorrectly hiding rooms when availability was unknown (no dates entered). Both were reported by us and fixed by AI.

### 5. Pagination Fix
**Generated:** `watch(totalPages)` resets `currentPage` to 1 when the filtered list shrinks below the current page.  
**Outcome:** Accepted.  
**Human decision:** We identified that applying filters could leave the user on a page that no longer exists (e.g. page 2 of 1). AI implemented the watcher as the correct Vue 3 mechanism for this cross-computed side effect.

### 6. Atomic Design Component Hierarchy
**Generated:** `atoms/ExtraChip.vue`, `atoms/AvailabilityBadge.vue`, `molecules/RoomCard.vue`, `molecules/DatePickerModal.vue`, `organisms/RoomList.vue`, `organisms/FilterBar.vue`; imports updated; old flat files removed. All new components use typed props via the `Room` interface.  
**Outcome:** Accepted.  
**Human decision:** Component classification (atom/molecule/organism) was agreed on in discussion before implementation began. The mapping — chip and badge as atoms, card and modal as molecules, list and filter bar as organisms — was confirmed by us before AI wrote any code.

### 7. Environment Variable Configuration
**Generated:** `VITE_API_URL ?? '/api'` fallback in `api.ts`; `${DB_URL:...}`, `${DB_USERNAME:...}`, `${DB_PASSWORD:...}`, `${DB_DDL_AUTO:update}`, `${API_PREFIX:/api}` in `application.properties`; `backend/.env.example`.  
**Outcome:** Accepted.  
**Human decision:** Application must run without any configuration for local development; production overrides via environment variables only.

### 8. Seed Data Expansion and Demo Documentation
**Generated:** 7 rooms (two variants of existing types with different extras and pricing), 9 bookings spread Jun–Sep 2026 with deliberate gaps; `DEMO.md` with room table, booking schedule, and suggested demo date ranges; `pageSize` restored to 5 (U2 DoD requirement).  
**Outcome:** Accepted.  
**Human decision:** Same room types with different extras was our explicit requirement — realistic hotel offering without inventing new categories. At least one "all rooms available" window (Aug 1–7) was a deliberate design choice for demo clarity.

### 9. BUILD.md Update
**Generated:** Project structure updated to Atomic Design, configuration section replaced with env var table, frontend dev section corrected, Seed Data section added.  
**Outcome:** Accepted.

---

## AI_USAGE.md Compaction — 2026-05-30 | Claude Sonnet 4.6

The original `AI_USAGE.md` grew to ~1500 lines across the project. To improve readability without losing content, it was restructured into this compact format.

**What was removed:**
- Verbatim prompt quotes (intent is captured in the Task description)
- File-by-file "what was generated" lists (detail is in git history)
- Per-section "Artefacts Produced by AI" tables
- Per-section summary tables (accept/modify/reject verdict is conveyed inline)
- Closely related iterations merged into single entries (e.g. the three Hotel resource design iterations)

**What was preserved:**
- Every tool, model, date, and issue reference
- Every accept / modify / reject outcome
- All human reasoning and decision points
- Full chronological session structure

**Outcome:** Reduced from ~1500 lines to ~240 lines. All major developments retained.

**Review for accuracy:** Before replacing the original, statements were checked for overstatements. Five corrections were applied — see git history for details.
