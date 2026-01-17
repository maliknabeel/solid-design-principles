# Spring Boot SOLID Examples

This project is a small Spring Boot application that focuses on the five SOLID principles.
Each principle has a bad and a good implementation side by side.

Packages follow this pattern:

- `com.example.solid.<principle>.bad`
- `com.example.solid.<principle>.good`

where `<principle>` is one of `srp`, `ocp`, `lsp`, `isp`, `dip`.

## Single Responsibility Principle (SRP)

SRP states that a class should have one reason to change.

### Bad code

Package: `com.example.solid.srp.bad`

- `BadUserService`
  - Saves a user
  - Sends a welcome email
  - Writes audit logs
  - All responsibilities are mixed inside one class.

### Good code

Package: `com.example.solid.srp.good`

- `UserService`
  - Coordinates user creation only.
- `UserRepository`
  - Persists users.
- `NotificationSender`
  - Sends welcome notifications.
- `AuditLogger`
  - Logs audit information.
- `InMemoryUserRepository`, `ConsoleNotificationSender`, `ConsoleAuditLogger`
  - Concrete implementations that can be replaced without changing `UserService`.

Each role has its own class, so future changes are localized.

## Open/Closed Principle (OCP)

OCP states that software entities should be open for extension but closed for modification.

### Bad code

Package: `com.example.solid.ocp.bad`

- `DiscountCalculator`
  - Has conditional logic based on `CustomerType` to calculate discounts.
  - Adding a new customer type requires modifying the class and adding new conditionals.

### Good code

Package: `com.example.solid.ocp.good`

- `DiscountPolicy`
  - Interface describing how a discount is applied.
- `StandardDiscountPolicy`, `PremiumDiscountPolicy`, `VipDiscountPolicy`
  - Implement different discount strategies.
- `OrderPriceCalculator`
  - Depends on `DiscountPolicy`.

To support a new discount type you implement a new `DiscountPolicy` and plug it into `OrderPriceCalculator` without changing existing classes.

## Liskov Substitution Principle (LSP)

LSP states that subtypes must be substitutable for their base types without breaking behavior.

### Bad code

Package: `com.example.solid.lsp.bad`

- `Document`
  - Has `getContent` and `setContent`.
- `ReadOnlyDocument`
  - Extends `Document` but throws `UnsupportedOperationException` in `setContent`.

Code that works with `Document` now has to know that some subclasses cannot be written to, breaking substitutability.

### Good code

Package: `com.example.solid.lsp.good`

- `ReadableDocument`
  - Read-only abstraction.
- `WritableDocument`
  - Extends `ReadableDocument` and adds `setContent`.
- `SimpleDocument`
  - Fully readable and writable.
- `ReadOnlyTextDocument`
  - Implements `ReadableDocument` only.

Clients that need write access depend on `WritableDocument`; read-only clients depend on `ReadableDocument` and can work with either implementation safely.

## Interface Segregation Principle (ISP)

ISP states that clients should not be forced to depend on interfaces they do not use.

### Bad code

Package: `com.example.solid.isp.bad`

- `Worker`
  - Interface with `work`, `eat`, `sleep`.
- `HumanWorker`
  - Uses all methods.
- `RobotWorker`
  - Needs only `work` but is forced to implement `eat` and `sleep` and throws exceptions.

Clients depending on `Worker` get methods that some implementations cannot support.

### Good code

Package: `com.example.solid.isp.good`

- `Workable`, `Eatable`, `Sleepable`
  - Small, focused interfaces.
- `HumanWorker`
  - Implements all three.
- `RobotWorker`
  - Implements only `Workable`.

Clients depend only on the capabilities they need, and implementations provide only what they can support.

## Dependency Inversion Principle (DIP)

DIP states that high-level modules should not depend on low-level modules; both should depend on abstractions.

### Bad code

Package: `com.example.solid.dip.bad`

- `SmtpEmailSender`
  - Concrete email sender.
- `PasswordResetService`
  - Instantiates `SmtpEmailSender` directly and calls it.

`PasswordResetService` is tightly coupled to a concrete implementation, which makes changing the email mechanism or testing difficult.

### Good code

Package: `com.example.solid.dip.good`

- `EmailSender`
  - Abstraction for sending emails.
- `SmtpEmailSender`
  - Concrete implementation, annotated with `@Component` so Spring can manage it.
- `PasswordResetService`
  - Annotated with `@Service` and depends on `EmailSender` via constructor injection.

High-level logic in `PasswordResetService` depends only on `EmailSender`, so different implementations can be provided (for example, a mock or an API-based sender) without changing the service code.

