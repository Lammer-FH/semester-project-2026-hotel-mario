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

## U4 — Book a Hotel Room: Backend Completion — 2026-06-09 | Claude Sonnet 4.6

The booking endpoint (`POST /api/v1/bookings`) was already scaffolded during Issue #44 (Clean Architecture Refactoring) — including `BookingRequestDto` with cross-field validators (`@EmailsMatch`, `@CheckOutAfterCheckIn`), `BookingConfirmationDto`, `BookingController`, `BookingServiceImpl`, and `BookingMapper`. The missing piece was that the service never checked availability before persisting, allowing double-bookings.

### 1. Availability Check and 409 Error Handling
**Generated:** `RoomNotAvailableException` (common/exception); 409 handler added to `GlobalExceptionHandler` returning structured error body `{status, message, errors[{field:"roomId", message}]}`; `BookingServiceImpl` injected with `RoomAvailabilityService` — checks availability before saving, returns empty Optional (→ 404) if room not found, throws `RoomNotAvailableException` (→ 409) if overlapping booking exists; `BookingControllerTest` extended with `createBooking_roomNotAvailable_returns409()` (8 tests total, all passing).  
**Outcome:** Accepted.  
**Human decision:** We identified that the existing service implementation would silently create conflicting bookings. AI was directed to reuse the existing `RoomAvailabilityService` rather than adding a direct repository dependency to `BookingServiceImpl`, keeping the component boundary intact.

---

## U5 — Improve Booking Confirmation: Backend Review — 2026-06-09 | Claude Sonnet 4.6

**Task:** Assess whether the backend needed changes to support U5 (extended confirmation with room details, directions, contact options, map coordinates).  
**Outcome:** No changes required. `BookingConfirmationDto` already includes all fields U5 needs — `room` (title, description, imageUrl, extras), `hotel` (directions, contact, address with latitude/longitude for map integration), and guest data. Latitude/longitude in `AddressDto` were identified as sufficient for a frontend map embed without any backend addition.  
**Human decision:** We reviewed each U5 requirement against the existing DTO fields before deciding to leave the backend untouched.

---

## Issue #39 — Display Critical Booking Errors to User (5xx / timeout + retry) — 2026-06-09 | Claude Sonnet 4.6

5xx responses fell through to the generic error card; no timeout was set on `fetch`; no retry option existed.  
**Generated:** `postRequest` now wraps `fetch` in an `AbortController` with a 10 s timeout — abort throws with `status: 0`; `sendBooking` resets error state before each attempt; `BookingDetailsView.vue` extracts submission into a reusable `submit()`, adds `isRetryable` computed (`status === 0 || status >= 500`), and shows "Try again" / "Connection failed" / "Server error" variants for retryable failures.  
**Outcome:** Accepted.  
**Human decision:** We identified all three missing pieces (timeout, 5xx branch, retry wiring) and scoped the fix to the booking flow only.

---

## Issue #39 — Display Critical Booking Errors to User (409 handling) — 2026-06-09 | Claude Sonnet 4.6

409 conflict was not distinguished from other errors: `useBookingStore` had no status code field, and `BookingDetailsView.vue` showed a generic error card with a Back button for all failures.  
**Generated:** `errorStatus` added to store state, set from `e.status` on catch; view now shows "Room no longer available" title and "Choose another room" button (navigates to room selection) for 409, generic error message and Back button for all other statuses.  
**Outcome:** Accepted.  
**Human decision:** We identified the missing 409 branch and directed the fix to use the existing `{ name: 'Room' }` route.

---

## Issue #39 — Display Critical Booking Errors to User — 2026-06-09 | Claude Sonnet 4.6

Two bugs found in the 400 error path: `postRequest` in `api.ts` passed the raw JSON error body as the error message instead of parsing it; `useBookingStore` stored the full Error object (`e`) in `errorMessage` instead of `e.message`, causing `[object Object]` in the UI.  
**Generated:** `postRequest` now parses the error body as `ValidationErrorResponseDto` and builds a readable `"field: message"` string; `errorMessage` assignment corrected to `e.message`.  
**Outcome:** Accepted.  
**Human decision:** We identified both bugs by tracing the call chain. No new display logic needed — the existing error card in `BookingDetailsView.vue` works once `errorMessage` is a plain string.

---

## U2 — Room Selection: Pagination Button Group — 2026-06-09 | Claude Sonnet 4.6

The pagination UI was a single "Next" button; the spec requires a button group (Previous / page counter / Next) styled as a connected control.  
**Generated:** `RoomSelectionView.vue` pagination section replaced with a `.btn-group` flex container holding two `ion-button fill="outline"` elements (Previous / Next) with a `.page-label` span (current / total) between them. CSS uses `border: 1px solid var(--ion-color-primary)` on the group and removes individual button box-shadows to form a single connected unit.  
**Outcome:** Accepted.  
**Human decision:** Spec was used directly as the layout reference.

---

## Frontend — Light Mode Readability Fix — 2026-06-09 | Claude Sonnet 4.6

Landing page content (`.info-box`) was invisible in light mode because `color: #ffffff` was hardcoded. Ion-toolbar text was also white-on-white due to Ionic's shadow DOM eating the inherited color.  
**Generated:** Removed `color: #ffffff` from `.info-box`; changed `.background` text color to `var(--ion-text-color)`; added shadow DOM overrides for `.content-background ion-toolbar` via `--background`, `--color`, `--border-color` CSS custom properties; changed `.footer-content` to `color: var(--ion-toolbar-color, #ffffff)` for dark mode fallback.  
**Outcome:** Accepted.  
**Human decision:** We isolated the Ionic shadow DOM as the reason a plain `color` override was insufficient and used CSS custom properties to pierce it.

---

## U2 — Room Selection: Two-Step Date Range Picker — 2026-06-09 | Claude Sonnet 4.6

The previous date flow opened two separate modals (one for check-in, one for check-out), requiring the user to open, confirm, and close twice. This was identified as poor UX relative to a single combined range-picker interaction.  
**Generated:** `DatePickerModal.vue` fully rewritten as a two-step modal: step 1 picks check-in (auto-advances to step 2 after 150 ms for visual feedback), step 2 picks check-out with the selected check-in highlighted via `highlightedDates`, a summary bar shows both dates, a single Apply button emits both values. Header toolbar shows Back (step 2) or Close (step 1). `useFilterStore` simplified: removed `pickerField` and `pickerTemp`, added `pickerInitialField`; `openPicker(field)` sets initial step, `applyPicker(checkIn, checkOut)` replaces `setDate`. `RoomSelectionView.vue` bindings and `onPickerApply` updated accordingly. `main.ts` registers `arrowBack` and `close` icons.  
**Outcome:** Accepted.  
**Human decision:** We chose to keep both temp dates in the modal's local state (not the store) so partial selection never pollutes the filter until Apply is pressed.

---

## Peer Review 2 Fixes — 2026-06-09 | Claude Sonnet 4.6

Peer review 2 identified two areas for improvement: component complexity (duplicated template logic across views) and code maintainability (no shared atoms for repeated UI patterns).

**Relevant changes made:**
- Extracted `DetailRow` atom to replace 16 duplicated `ion-item` label/value blocks across `BookingDetailsView` and `BookingReviewView`
- Extracted `ImageSlider` molecule to replace ~80 lines of duplicated slideshow logic in `home.vue` and `about.vue`
- Applied existing `ExtraChip` atom in `BookingDetailsView` for consistency with `RoomCard`

Items identified but not actioned: `HotelConfig` → `HotelDto` cross-layer dependency (known, low priority); `FilterBar` untyped props. Both were scoped out as non-functional and deferred.

**Outcome:** Accepted. TypeScript type check passes with no errors after all changes.  
**Human decision:** We scoped the changes to high-impact duplications only and explicitly decided not to refactor the cross-layer `HotelConfig` dependency, as it does not affect runtime correctness and was not flagged by the reviewer as a blocking issue.

---

## Peer Review Fixes — 2026-06-09 | Claude Sonnet 4.6

### 1. Move PageResult to common/model/
**Generated:** `PageResult.java` moved from `common/` to `common/model/`; import updated in `RoomController`, `RoomService`, `RoomServiceImpl`, `RoomControllerTest`.  
**Outcome:** Accepted.  
**Human decision:** Peer review identified `common/` as the wrong sub-package — model types belong in `common/model/` by convention.

### 2. Real Server-Side Pagination
**Generated:** `useRoomStore.fetchRooms(page)` now passes a 1-indexed page from the UI, converting to 0-indexed (`page - 1`) when calling `getRooms(page - 1, PAGE_SIZE)`; `totalPages` and `totalElements` stored from the backend `PageResult` response; `PAGE_SIZE = 5` exported as constant; `RoomSelectionView` `nextPage`/`previousPage` are now async — they call `fetchRooms(page)` and re-run `checkAvailability` if dates are set; local `paginatedRooms` computed removed.  
**Outcome:** Accepted.  
**Human decision:** Peer review identified that `fetchRooms` was hardcoded to `getRooms(0, 20)`, rendering pagination buttons cosmetic. We confirmed the fix should keep client-side price/availability filtering on the fetched page (not implemented server-side).

---

## Component Refactoring: Atomic Design — 2026-06-09 | Claude Sonnet 4.6

### 1. DetailRow Atom
**Generated:** `atoms/DetailRow.vue` — `<ion-item lines="none">` with `<strong>{{ label }}:</strong> {{ value }}` layout; typed props `label: string`, `value: string | number`. Applied in `BookingDetailsView.vue` (replacing 11 inline `ion-item` blocks) and `BookingReviewView.vue` (replacing 5 `ion-item` blocks with `<b>` tags).  
**Outcome:** Accepted.  
**Human decision:** We identified both views had identical label/value row structure duplicated by hand.

### 2. ImageSlider Molecule
**Generated:** `molecules/ImageSlider.vue` — slideshow with `slides: { image, caption }[]` prop, `autoplayMs` prop, `prev`/`next` arrow buttons, dot indicators, fade animation, responsive height (220 px mobile / 300 px desktop), counter overlay. Applied in `home.vue` and `about.vue`, replacing ~40 lines of duplicated slideshow template + script logic in each.  
**Outcome:** Accepted.  
**Human decision:** Both pages had identical slideshow logic with no shared component. We identified this as the highest-value deduplication target.

### 3. ExtraChip in BookingDetailsView
**Generated:** `BookingDetailsView.vue` extras section now uses `<ExtraChip v-for="extra in response.room.extras">` instead of inline `ion-chip` elements; imports cleaned up (`IonChip`, `IonIcon` removed).  
**Outcome:** Accepted.  
**Human decision:** `ExtraChip` already existed as an atom in `RoomCard` — reusing it in the confirmation view was consistent with the existing component hierarchy.

### 4. Room Image in Booking Confirmation
**Generated:** `BookingDetailsView.vue` shows `<img :src="\`/images/rooms/${response.room.id}.jpg\`">` after the "Room" section heading.  
**Outcome:** Modified — initial version used `response.room.imageUrl` (backend value like `/images/rooms/standard.jpg`); corrected to use the same ID-based pattern (`/images/rooms/${id}.jpg`) that `useRoomStore` uses, matching the numbered files in `public/images/rooms/`.  
**Human decision:** We identified that the backend imageUrl values do not match the actual filenames in the frontend public folder.

---

## U2 — Re-check Availability on Room Selection Mount — 2026-06-09 | Claude Sonnet 4.6

When navigating back to room selection with dates already set in the filter store, availability badges were stale — `onMounted` only fetched rooms without re-checking availability.  
**Generated:** `onMounted` made `async`; after `await fetchRooms(1)`, checks `filterStore.datesSelected` and calls `checkAvailability` if true, propagating any error to the date error banner.  
**Outcome:** Accepted.  
**Human decision:** We identified the stale-badge scenario and directed the fix to use the existing `checkAvailability` path rather than adding new logic.

---

## Presentation Deck — 2026-06-09 | Claude Sonnet 4.6

**Generated:** `presentation.pptx` (8 slides) via a Python `python-pptx` script: title slide with tech-stack chips, live demo step guide, AI workflow slide (clean architecture scaffolding from ER diagram + API spec, with `@EmailsMatch` code example), what-surprised-us slide (3 wins / 3 missteps), peer review 2 response slide (component refactoring before/after), architecture overview (backend layers + frontend atomic design), Q&A.  
**Outcome:** Modified — generated deck used as a starting point; slides were manually reviewed, restructured, and finalized as `AWT Presentation Group AE.pdf`.  
**Human decision:** We chose the clean-architecture scaffolding as the AI workflow example (over the date picker) because it is a simpler, more universally applicable story. Content, wording, and visual layout were reviewed and adjusted manually before the final version was produced.

---

## BUILD.md Update — 2026-06-09 | Claude Sonnet 4.6

**Generated:** Review identified stale content; updated `BUILD.md`: JAR filename (`0.0.1-SNAPSHOT` → `1.0.0`), component lists in the project structure tree (`DetailRow` in atoms; `ImageSlider`, `BookingForm`, `SharedHeader/Footer` in molecules; `BookingView`, `BookingReviewView`, `BookingDetailsView` in views; `useBookingStore` in stores), two broken code fences (MySQL `docker run` block, `npm install` block), last-updated date.  
**Outcome:** Accepted.  
**Human decision:** We directed the review; AI identified and applied all stale entries in one pass.

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
