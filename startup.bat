@echo off
setlocal enabledelayedexpansion

REM Colors and formatting
for /F %%A in ('echo prompt $H ^| cmd') do set "BS=%%A"

set "GREEN=[92m"
set "RED=[91m"
set "YELLOW=[93m"
set "RESET=[0m"

REM Check for docker or podman
set "CONTAINER_CMD="

where docker >nul 2>nul
if !errorlevel! equ 0 (
    set "CONTAINER_CMD=docker"
    echo %GREEN%✓ Docker found%RESET%
) else (
    where podman >nul 2>nul
    if !errorlevel! equ 0 (
        set "CONTAINER_CMD=podman"
        echo %GREEN%✓ Podman found%RESET%
    ) else (
        echo %RED%✗ Neither Docker nor Podman found. Please install one of them.%RESET%
        pause
        exit /b 1
    )
)

echo.
echo %YELLOW%Starting Hotel Mario Application...%RESET%
echo Using: !CONTAINER_CMD!
echo.

REM Build and start containers
call !CONTAINER_CMD! compose up -d --build

if !errorlevel! neq 0 (
    echo %RED%✗ Failed to start containers%RESET%
    pause
    exit /b 1
)

REM Wait for services to be ready
echo.
echo %YELLOW%Waiting for services to be ready...%RESET%
timeout /t 5 /nobreak

REM Check if services are running
!CONTAINER_CMD! ps | find "hotelmario-mysql" >nul
if !errorlevel! equ 0 (
    echo %GREEN%✓ MySQL is running%RESET%
) else (
    echo %RED%✗ MySQL failed to start%RESET%
    pause
    exit /b 1
)

!CONTAINER_CMD! ps | find "hotelmario-app" >nul
if !errorlevel! equ 0 (
    echo %GREEN%✓ Spring Boot App is running%RESET%
) else (
    echo %RED%✗ Spring Boot App failed to start%RESET%
    pause
    exit /b 1
)

echo.
echo %GREEN%========================================%RESET%
echo %GREEN%Hotel Mario is up and running!%RESET%
echo %GREEN%========================================%RESET%
echo.
echo Application URL: http://localhost:8080
echo MySQL Port: 3306
echo.
echo View logs:
echo   !CONTAINER_CMD! compose logs -f
echo.
echo Stop services:
echo   !CONTAINER_CMD! compose down
echo.
pause
