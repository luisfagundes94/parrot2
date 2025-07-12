# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Parrot is an Android application built with Jetpack Compose using a modular architecture. The app appears to be a translation application with a clean architecture approach, leveraging Hilt for dependency injection and modern Android development patterns.

## Build System and Commands

The project uses Gradle with Kotlin DSL (`.gradle.kts` files) and follows Android's modern build conventions.

### Essential Commands

```bash
# Build the project
./gradlew build

# Clean build
./gradlew clean

# Run unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest

# Install debug APK
./gradlew installDebug

# Generate lint report
./gradlew lint

# Check dependencies for updates
./gradlew dependencyUpdates
```

### Module-specific Commands

```bash
# Build specific modules
./gradlew :app:build
./gradlew :core:common:build
./gradlew :core:designsystem:build
./gradlew :feature:translation:build

# Run tests for specific modules
./gradlew :core:common:test
./gradlew :feature:translation:test
```

## Architecture

### Modular Structure

The project follows a multi-module architecture:

- **`app/`** - Main application module containing `MainActivity`, navigation setup, and app-level configuration
- **`core/`** - Shared foundation modules
  - **`common/`** - Base classes, utilities, DI modules, and shared presentation patterns
  - **`designsystem/`** - UI components, theme, colors, typography, and design tokens
- **`feature/`** - Feature-specific modules
  - **`translation/`** - Translation feature implementation

### Key Architectural Patterns

- **MVVM with State Management**: Uses a custom `ViewModel` base class in `core:common` that provides state management with `UiState` pattern
- **Dependency Injection**: Hilt is used throughout for DI, with modules defined in `core:common`
- **Navigation**: Type-safe navigation using Jetpack Navigation Compose with route classes
- **Adaptive UI**: Uses Material 3 adaptive components for responsive design across different screen sizes

### Technology Stack

- **UI**: Jetpack Compose with Material 3
- **Architecture**: MVVM + Repository pattern
- **DI**: Hilt
- **Navigation**: Navigation Compose with type-safe routes
- **Networking**: Retrofit with Gson converter
- **Build**: Gradle with Version Catalogs (libs.versions.toml)
- **Language**: Kotlin with JVM target 11

### Key Files and Patterns

- **`MainActivity.kt`**: Entry point using `@AndroidEntryPoint` and `ParrotTheme`
- **`ParrotApp.kt`**: Main app composable with adaptive navigation scaffold
- **`ParrotNavHost.kt`**: Navigation setup with type-safe routes
- **Base ViewModel**: Located at `core/common/src/main/java/com/luisfagundes/common/presentation/ViewModel.kt`
- **Design System**: All UI components and theming in `core:designsystem`

### Navigation Pattern

Navigation uses type-safe routes with sealed classes/objects. Features define their own route objects (e.g., `TranslationRoute`) and navigation functions.

### State Management

The project uses a custom state management pattern with:
- `UiState` interface for defining feature states
- Base `ViewModel` class that manages state updates
- State updates through protected `updateState` function

## Development Notes

- **Target SDK**: 36 (compileSdk and targetSdk)
- **Minimum SDK**: 24
- **Kotlin Version**: 2.2.0
- **Compose BOM**: 2025.06.01
- **Hilt Version**: 2.56.2

### Module Dependencies

Each feature module depends on `core:common` and `core:designsystem`. The app module depends on all feature modules and provides the navigation setup to wire everything together.