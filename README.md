# Spring Boot SOLID Examples

This project is a small Spring Boot application that focuses on the five SOLID principles.
Each principle has a bad and a good implementation side by side, plus a small UI controller.

Packages follow this pattern:

- `com.pafiast.solid.<principle>.bad`
- `com.pafiast.solid.<principle>.good`

where `<principle>` is one of `srp`, `ocp`, `lsp`, `isp`, `dip`. The web API lives in `com.pafiast.solid.ui`.

## Single Responsibility Principle (SRP)

SRP states that a class should have one reason to change.

### Bad code

Package: `com.pafiast.solid.srp.bad`

- `BadUserService`
  - Saves a user, sends a welcome email, and writes audit logs.
  - Mixes persistence, notification, and auditing concerns in a single class.
  - Any change in database, email, or logging behavior forces this class to change.

### Good code

Package: `com.pafiast.solid.srp.good`

- `UserService`
  - Coordinates the user creation use case and delegates work.
- `UserRepository`
  - Persists users.
- `NotificationSender`
  - Sends welcome notifications.
- `AuditLogger`
  - Logs audit information.
- `InMemoryUserRepository`, `ConsoleNotificationSender`, `ConsoleAuditLogger`
  - Concrete implementations that can be replaced without changing `UserService`.

Each role has its own class, so changes to persistence, notifications, or auditing are localized and `UserService` remains small and focused.

## Open/Closed Principle (OCP)

OCP states that software entities should be open for extension but closed for modification.

### Bad code

Package: `com.pafiast.solid.ocp.bad`

- `DiscountCalculator`
  - Uses conditional logic on `CustomerType` to calculate discounts.
  - Adding a new customer type means editing this class and extending the conditional.
  - The more types are added, the larger and more fragile the conditional block becomes.

### Good code

Package: `com.pafiast.solid.ocp.good`

- `DiscountPolicy`
  - Interface describing how a discount is applied.
- `StandardDiscountPolicy`, `PremiumDiscountPolicy`, `VipDiscountPolicy`
  - Implement different discount strategies.
- `OrderPriceCalculator`
  - Depends on `DiscountPolicy` instead of hard-coded conditionals.

To support a new discount type you implement a new `DiscountPolicy` and plug it into `OrderPriceCalculator` without changing the existing calculator class.

## Liskov Substitution Principle (LSP)

LSP states that subtypes must be substitutable for their base types without breaking behavior.

### Bad code

Package: `com.pafiast.solid.lsp.bad`

- `Document`
  - Has `getContent` and `setContent`.
- `ReadOnlyDocument`
  - Extends `Document` but throws `UnsupportedOperationException` from `setContent`.

Code that works with `Document` cannot safely substitute `ReadOnlyDocument`: callers must special‑case this subtype or risk runtime errors, which violates substitutability.

### Good code

Package: `com.pafiast.solid.lsp.good`

- `ReadableDocument`
  - Read-only abstraction.
- `WritableDocument`
  - Extends `ReadableDocument` and adds `setContent`.
- `SimpleDocument`
  - Fully readable and writable.
- `ReadOnlyTextDocument`
  - Implements `ReadableDocument` only.

Clients that need write access depend on `WritableDocument`; read-only clients depend on `ReadableDocument` and can safely work with both read‑only and writable implementations.

## Interface Segregation Principle (ISP)

ISP states that clients should not be forced to depend on interfaces they do not use.

### Bad code

Package: `com.pafiast.solid.isp.bad`

- `Worker`
  - Interface with `work`, `eat`, `sleep`.
- `HumanWorker`
  - Uses all methods.
- `RobotWorker`
  - Needs only `work` but is forced to implement `eat` and `sleep`, sometimes by throwing exceptions.

Clients depending on `Worker` see methods that some implementations cannot meaningfully support, and calls may fail at runtime.

### Good code

Package: `com.pafiast.solid.isp.good`

- `Workable`, `Eatable`, `Sleepable`
  - Small, focused interfaces that represent separate capabilities.
- `HumanWorker`
  - Implements all three.
- `RobotWorker`
  - Implements only `Workable`.

Clients depend only on the capabilities they need, and implementations provide only the operations they truly support.

## Dependency Inversion Principle (DIP)

DIP states that high-level modules should not depend on low-level modules; both should depend on abstractions.

### Bad code

Package: `com.pafiast.solid.dip.bad`

- `SmtpEmailSender`
  - Concrete email sender.
- `PasswordResetService`
  - Instantiates `SmtpEmailSender` directly with `new` and calls it.

`PasswordResetService` is tightly coupled to a specific SMTP implementation, which makes changing the email mechanism or testing difficult.

### Good code

Package: `com.pafiast.solid.dip.good`

- `EmailSender`
  - Abstraction for sending emails.
- `SmtpEmailSender`
  - Concrete implementation, annotated with `@Component` so Spring can manage it.
- `PasswordResetService`
  - Annotated with `@Service` and depends on `EmailSender` via constructor injection.

High-level logic in `PasswordResetService` depends only on `EmailSender`, so different implementations (SMTP, mock, API-based, etc.) can be provided without changing the service code.

