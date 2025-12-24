# Kotlin Clean Architecture Example

[![Build and Test](https://github.com/jeremejazz/kotlin-clean-example/actions/workflows/build.yml/badge.svg?branch=main)](https://github.com/jeremejazz/kotlin-clean-example/actions/workflows/build.yml)

Clean Architecture implementation in Kotlin with a Phonebook CLI Application

## Clean Architecture

This architecture separates concerns into three distinct layers:

1. Domain Layer: Business logic (Entities, Use Cases, Repository Interfaces).
2. Data Layer: Data access (SQLite Implementation, Database connection).
3. Presentation Layer: User interaction (CLI Input/Output).
