# 🦜 Parrot

**Parrot** is a modern Android translation application built with Jetpack Compose and a clean modular architecture. The app provides seamless language translation capabilities with an adaptive, responsive UI that works beautifully across different screen sizes.

## 📱 Features

- **Real-time Translation**: Translate text between supported languages
- **Language Pair Swapping**: Easily swap source and target languages  
- **Adaptive UI**: Responsive design that adapts to different screen sizes
- **Material 3 Design**: Modern UI following Material Design 3 principles
- **Clean Architecture**: Well-structured, maintainable codebase with clear separation of concerns

## 🏗️ Architecture

### Modular Architecture

The project follows a **multi-module architecture** pattern for better separation of concerns, reusability, and maintainability:

```
parrot/
├── app/                    # Main application module
├── core/
│   ├── common/            # Shared foundation (DI, utilities, base classes)
│   ├── designsystem/      # UI components, theme, design tokens
│   └── testing/           # Testing utilities and rules
└── feature/
    └── translation/        # Translation feature implementation
```

### Architecture Patterns

- **MVVM + Repository Pattern**: Clear separation between UI, business logic, and data layers
- **Clean Architecture**: Domain-driven design with use cases and repositories
- **State Management**: Custom `UiState` pattern with reactive state updates
- **Dependency Injection**: Hilt for compile-time DI across all modules

### Key Components

#### Base ViewModel (`core/common/src/main/java/com/luisfagundes/common/presentation/ViewModel.kt`)
Custom base class providing:
- Generic state management with `UiState` interface
- Protected `updateState` function for controlled state mutations
- Integration with AndroidX ViewModel lifecycle

#### Navigation
- **Type-safe Navigation**: Using Jetpack Navigation Compose with sealed classes
- **Adaptive Navigation**: Material 3 adaptive navigation suite for different screen sizes
- **Route-based**: Clean route definitions per feature module

#### Design System (`core/designsystem/`)
- **Centralized UI Components**: Reusable components (`ParrotTextField`, `ParrotTopAppBar`, etc.)
- **Theming**: Consistent color schemes, typography, and spacing
- **Material 3**: Full Material Design 3 implementation

## 🛠️ Technologies Used

### Core Technologies
- **Kotlin** 2.2.0 - Primary programming language
- **Android SDK** - Target: 36, Min: 24
- **Jetpack Compose** - Modern declarative UI toolkit
- **Material 3** - Latest Material Design components
- **Gradle with Kotlin DSL** - Build system with version catalogs

### Architecture & DI
- **Hilt** 2.56.2 - Dependency injection
- **AndroidX Navigation Compose** - Type-safe navigation
- **AndroidX Lifecycle** - Lifecycle-aware components
- **Coroutines** - Asynchronous programming

### Networking & Data
- **Retrofit** 3.0.0 - HTTP client
- **Gson** - JSON serialization/deserialization

### Testing
- **JUnit 4** - Unit testing framework
- **MockK** - Mocking library
- **Turbine** - Testing Flow emissions
- **Espresso** - UI testing
- **Coroutines Test** - Testing coroutines

### Build Tools
- **Android Gradle Plugin** 8.11.1
- **KSP** - Kotlin Symbol Processing for Hilt
- **Version Catalogs** - Centralized dependency management

## 🧪 Testing

The project includes comprehensive testing at multiple levels:

### Unit Tests
- **ViewModel Tests**: Complete test coverage for `TranslationViewModel` using MockK and Turbine
- **Testing Utilities**: Custom test rules and mocks in `core/testing/`

## 📁 Project Structure

### App Module (`app/`)
- **MainActivity**: Entry point with Hilt integration
- **ParrotApp**: Main UI composition with adaptive navigation
- **Navigation**: Type-safe routing setup

### Core Modules

#### Common (`core/common/`)
- Base ViewModel and UiState interfaces
- Dependency injection modules
- Shared utilities and extensions
- Resource providers and dispatchers

#### Design System (`core/designsystem/`)
- Reusable UI components
- Theme definitions (colors, typography, spacing)
- Material 3 adaptive components

#### Testing (`core/testing/`)
- Test utilities and rules
- Main coroutine dispatcher rule for testing

### Feature Modules

#### Translation (`feature/translation/`)
- **Data Layer**: API services, repositories, mappers
- **Domain Layer**: Use cases, models, business logic
- **Presentation Layer**: ViewModels, UI states, Compose screens

## 🎨 UI/UX

- **Material 3 Design**: Latest Material Design principles
- **Adaptive Layout**: Responsive design for phones, tablets, and foldables
- **Edge-to-Edge**: Modern full-screen experience
- **Dynamic Theming**: System-aware theming support
- **Accessibility**: Following Android accessibility guidelines

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Code Style
- Follow Kotlin coding conventions
- Use the existing architecture patterns
- Write comprehensive tests for new features
- Follow the established module structure

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🔗 Additional Resources

- [Android Developer Guide](https://developer.android.com/)
- [Jetpack Compose Documentation](https://developer.android.com/jetpack/compose)
- [Material Design 3](https://m3.material.io/)
- [Hilt Documentation](https://dagger.dev/hilt/)

---

**Built with ❤️ using modern Android development practices**