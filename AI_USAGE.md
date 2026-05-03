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

---

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
