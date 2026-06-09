# Demo Guide

This document describes the seed data pre-loaded on first startup and suggests date ranges for demonstrating key features.

## Rooms (7 total)

| # | Name | Price | Extras |
|---|---|---|---|
| 1 | Standard Double Room | €89.99 | Wi-Fi, Parking |
| 2 | Superior Double Room | €119.99 | Wi-Fi, Coffee Machine, Parking |
| 3 | Deluxe Room | €149.99 | Wi-Fi, Coffee Machine, AC, Safe |
| 4 | Junior Suite | €199.99 | Wi-Fi, Coffee Machine, AC, Safe, Panorama View |
| 5 | Executive Suite | €279.99 | Wi-Fi, Coffee Machine, AC, Safe, Panorama View, Lounge Access |
| 6 | Standard Double Room | €99.99 | Wi-Fi, Coffee Machine |
| 7 | Superior Double Room | €139.99 | Wi-Fi, Coffee Machine, AC, Safe |

Page 1 shows rooms 1–5, page 2 shows rooms 6–7.

## Availability Demo

### Suggested date ranges

| Check-in | Check-out | Unavailable rooms | Available rooms |
|---|---|---|---|
| 2026-06-05 | 2026-06-10 | Standard Double (1), Deluxe (3) | 5 rooms |
| 2026-06-18 | 2026-06-22 | Superior Double (2) | 6 rooms |
| 2026-07-05 | 2026-07-10 | Junior Suite (4) | 6 rooms |
| 2026-07-15 | 2026-07-20 | Executive Suite (5) | 6 rooms |
| 2026-08-01 | 2026-08-07 | *(none)* | **all 7 rooms** |
| 2026-08-08 | 2026-08-14 | Superior Double variant (7) | 6 rooms |
| 2026-08-20 | 2026-08-25 | Deluxe (3) | 6 rooms |
| 2026-09-01 | 2026-09-05 | Executive Suite (5) | 6 rooms |

### Full booking schedule

| Room | Guest | Check-in | Check-out | Breakfast |
|---|---|---|---|---|
| Standard Double (1) | Anna Berger | 2026-06-02 | 2026-06-06 | No |
| Deluxe (3) | Thomas Gruber | 2026-06-05 | 2026-06-10 | Yes |
| Superior Double (2) | Max Müller | 2026-06-18 | 2026-06-22 | Yes |
| Junior Suite (4) | Sarah Weber | 2026-07-05 | 2026-07-10 | No |
| Executive Suite (5) | Julia Hofmann | 2026-07-15 | 2026-07-20 | Yes |
| Standard Double variant (6) | Klaus Fischer | 2026-07-22 | 2026-07-26 | No |
| Superior Double variant (7) | Maria Schneider | 2026-08-08 | 2026-08-14 | Yes |
| Deluxe (3) | Peter Huber | 2026-08-20 | 2026-08-25 | No |
| Executive Suite (5) | Anna Bauer | 2026-09-01 | 2026-09-05 | Yes |

## Notes

- The seeder only runs when the database is empty (`roomRepository.count() == 0`).  
  To reload seed data, clear the `room`, `booking`, `extra`, and `room_extra` tables and restart the backend.
- Room images are resolved by room ID: `frontend/public/images/rooms/{id}.jpg`.
