package com.pafiast.solid.ui;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pafiast.solid.dip.bad.PasswordResetService;
import com.pafiast.solid.isp.bad.RobotWorker;
import com.pafiast.solid.isp.bad.Worker;
import com.pafiast.solid.isp.good.Workable;
import com.pafiast.solid.lsp.bad.Document;
import com.pafiast.solid.lsp.bad.ReadOnlyDocument;
import com.pafiast.solid.ocp.bad.CustomerType;
import com.pafiast.solid.ocp.bad.DiscountCalculator;
import com.pafiast.solid.ocp.good.OrderPriceCalculator;
import com.pafiast.solid.ocp.good.PremiumDiscountPolicy;
import com.pafiast.solid.srp.bad.BadUserService;
import com.pafiast.solid.srp.good.AuditLogger;
import com.pafiast.solid.srp.good.ConsoleAuditLogger;
import com.pafiast.solid.srp.good.ConsoleNotificationSender;
import com.pafiast.solid.srp.good.InMemoryUserRepository;
import com.pafiast.solid.srp.good.NotificationSender;
import com.pafiast.solid.srp.good.UserRepository;
import com.pafiast.solid.srp.good.UserService;

@RestController
@RequestMapping("/api")
public class SolidExampleApiController {

    @GetMapping("/srp/bad")
    public String runSrpBad() {
        BadUserService service = new BadUserService();
        service.createUser("Alice", "alice@example.com");
        StringBuilder builder = new StringBuilder();
        builder.append("Single Responsibility Principle (SRP) – Bad example\n");
        builder.append("SRP says a class should have one reason to change.\n\n");
        builder.append("Here BadUserService does everything itself:\n");
        builder.append("- Saves user Alice <alice@example.com>\n");
        builder.append("- Sends the welcome email\n");
        builder.append("- Writes the audit log\n\n");
        builder.append("Problems:\n");
        builder.append("- Many reasons to change are inside one class (database, email, logging).\n");
        builder.append("- A small change in any of these concerns forces this class to be edited.\n");
        builder.append("- The class becomes hard to test and reuse because it mixes responsibilities.");
        return builder.toString();
    }

    @GetMapping("/srp/good")
    public String runSrpGood() {
        UserRepository userRepository = new InMemoryUserRepository();
        NotificationSender notificationSender = new ConsoleNotificationSender();
        AuditLogger auditLogger = new ConsoleAuditLogger();
        UserService service = new UserService(userRepository, notificationSender, auditLogger);
        service.createUser("Bob", "bob@example.com");
        StringBuilder builder = new StringBuilder();
        builder.append("Single Responsibility Principle (SRP) – Good example\n");
        builder.append("Each class has one clear reason to change.\n\n");
        builder.append("UserService now coordinates collaborators instead of doing the work:\n");
        builder.append("- InMemoryUserRepository persists Bob <bob@example.com>\n");
        builder.append("- ConsoleNotificationSender sends the welcome notification\n");
        builder.append("- ConsoleAuditLogger records the audit entry\n\n");
        builder.append("Benefits:\n");
        builder.append("- Changing persistence, email, or logging touches only that specific class.\n");
        builder.append("- UserService stays small and focused on the use case.\n");
        builder.append("- Testing is easier because each responsibility can be mocked or swapped independently.");
        return builder.toString();
    }

    @GetMapping("/ocp/bad")
    public String runOcpBad() {
        DiscountCalculator calculator = new DiscountCalculator();
        double discount = calculator.calculateDiscount(CustomerType.PREMIUM, 100.0);
        double finalPrice = 100.0 - discount;
        StringBuilder builder = new StringBuilder();
        builder.append("Open/Closed Principle (OCP) – Bad example\n");
        builder.append("OCP says code should be open for extension but closed for modification.\n\n");
        builder.append("Here DiscountCalculator uses conditionals on CustomerType:\n");
        builder.append("- Input: customerType = PREMIUM, price = 100.0\n");
        builder.append("- Discount: ").append(discount).append("\n");
        builder.append("- Final price: ").append(finalPrice).append("\n\n");
        builder.append("Problems:\n");
        builder.append("- Every new customer type requires editing DiscountCalculator.\n");
        builder.append("- The conditional logic grows over time and becomes fragile.\n");
        builder.append("- Existing behavior can break when adding a new branch.");
        return builder.toString();
    }

    @GetMapping("/ocp/good")
    public String runOcpGood() {
        OrderPriceCalculator calculator = new OrderPriceCalculator(new PremiumDiscountPolicy());
        double finalPrice = calculator.calculatePrice(100.0);
        double originalPrice = 100.0;
        double discount = originalPrice - finalPrice;
        StringBuilder builder = new StringBuilder();
        builder.append("Open/Closed Principle (OCP) – Good example\n");
        builder.append("OrderPriceCalculator depends on the DiscountPolicy abstraction instead of conditionals.\n\n");
        builder.append("Scenario:\n");
        builder.append("- Input price: ").append(originalPrice).append("\n");
        builder.append("- Policy in use: PremiumDiscountPolicy\n");
        builder.append("- Discount: ").append(discount).append("\n");
        builder.append("- Final price: ").append(finalPrice).append("\n\n");
        builder.append("Benefits:\n");
        builder.append("- To support a new customer type, create a new DiscountPolicy implementation.\n");
        builder.append("- OrderPriceCalculator stays closed for modification but open to new policies.\n");
        builder.append("- Behavior is easier to extend and reason about.");
        return builder.toString();
    }

    @GetMapping("/lsp/bad")
    public String runLspBad() {
        Document document = new ReadOnlyDocument();
        try {
            document.setContent("New content");
            StringBuilder builder = new StringBuilder();
            builder.append("Liskov Substitution Principle (LSP) – Bad example\n");
            builder.append("LSP says subclasses must be usable anywhere their base type is expected.\n\n");
            builder.append("If ReadOnlyDocument allowed setContent:\n");
            builder.append("- Code typed against Document could unexpectedly mutate a read‑only document.\n");
            builder.append("- Callers would not be able to rely on the contract of the base type.");
            return builder.toString();
        } catch (UnsupportedOperationException ex) {
            StringBuilder builder = new StringBuilder();
            builder.append("Liskov Substitution Principle (LSP) – Bad example\n");
            builder.append("ReadOnlyDocument extends Document but setContent breaks the expected behavior.\n\n");
            builder.append("Operation:\n");
            builder.append("- Document document = new ReadOnlyDocument()\n");
            builder.append("- document.setContent(\"New content\") -> UnsupportedOperationException\n\n");
            builder.append("Problems:\n");
            builder.append("- Code that works with Document must now know which subclasses throw exceptions.\n");
            builder.append("- ReadOnlyDocument is not a true substitute for Document, so LSP is violated.");
            return builder.toString();
        }
    }

    @GetMapping("/lsp/good")
    public String runLspGood() {
        com.pafiast.solid.lsp.good.ReadableDocument readableDocument =
                new com.pafiast.solid.lsp.good.ReadOnlyTextDocument("Initial content");
        String content = readableDocument.getContent();
        StringBuilder builder = new StringBuilder();
        builder.append("Liskov Substitution Principle (LSP) – Good example\n");
        builder.append("Read-only and writable responsibilities are modeled with separate abstractions.\n\n");
        builder.append("Operation:\n");
        builder.append("- ReadableDocument document = new ReadOnlyTextDocument(\"Initial content\")\n");
        builder.append("- document.getContent() -> ").append(content).append("\n\n");
        builder.append("Benefits:\n");
        builder.append("- Code that only needs to read depends on ReadableDocument.\n");
        builder.append("- WritableDocument extends ReadableDocument for clients that need writes.\n");
        builder.append("- All implementations respect the expectations of the type they implement, so substitutability holds.");
        return builder.toString();
    }

    @GetMapping("/isp/bad")
    public String runIspBad() {
        Worker worker = new RobotWorker();
        worker.work();
        try {
            worker.eat();
            StringBuilder builder = new StringBuilder();
            builder.append("Interface Segregation Principle (ISP) – Bad example\n");
            builder.append("ISP says clients should not be forced to depend on methods they do not use.\n\n");
            builder.append("Here Worker has work, eat, and sleep.\n");
            builder.append("RobotWorker really only needs work, but is forced to implement all methods.\n");
            builder.append("Even if eat does not throw, the interface still exposes operations that do not make sense.");
            return builder.toString();
        } catch (UnsupportedOperationException ex) {
            StringBuilder builder = new StringBuilder();
            builder.append("Interface Segregation Principle (ISP) – Bad example\n");
            builder.append("Worker interface requires work, eat, and sleep.\n\n");
            builder.append("Operation sequence:\n");
            builder.append("- RobotWorker.work()\n");
            builder.append("- RobotWorker.eat() -> UnsupportedOperationException\n\n");
            builder.append("Problems:\n");
            builder.append("- RobotWorker is forced to implement methods it cannot support.\n");
            builder.append("- Clients depending on Worker see methods that may fail at runtime.\n");
            builder.append("- The interface is too large and does not reflect the real capabilities.");
            return builder.toString();
        }
    }

    @GetMapping("/isp/good")
    public String runIspGood() {
        Workable worker = new com.pafiast.solid.isp.good.RobotWorker();
        worker.work();
        StringBuilder builder = new StringBuilder();
        builder.append("Interface Segregation Principle (ISP) – Good example\n");
        builder.append("Capabilities are split into small, focused interfaces.\n\n");
        builder.append("RobotWorker only implements Workable:\n");
        builder.append("- Workable worker = new RobotWorker()\n");
        builder.append("- worker.work() succeeds\n\n");
        builder.append("Benefits:\n");
        builder.append("- Clients that only need work depend on Workable.\n");
        builder.append("- HumanWorker can implement Eatable and Sleepable separately.\n");
        builder.append("- Implementations expose only the operations they truly support.");
        return builder.toString();
    }

    @GetMapping("/dip/bad")
    public String runDipBad() {
        PasswordResetService service = new PasswordResetService();
        service.resetPassword("user@example.com");
        StringBuilder builder = new StringBuilder();
        builder.append("Dependency Inversion Principle (DIP) – Bad example\n");
        builder.append("DIP says high-level modules should depend on abstractions, not concretions.\n\n");
        builder.append("Here PasswordResetService creates SmtpEmailSender with new inside the method:\n");
        builder.append("- new PasswordResetService().resetPassword(\"user@example.com\")\n\n");
        builder.append("Problems:\n");
        builder.append("- The service is tightly coupled to the SMTP implementation.\n");
        builder.append("- Swapping email mechanisms requires changing PasswordResetService.\n");
        builder.append("- Testing is harder because the real SMTP sender is always used.");
        return builder.toString();
    }

    @GetMapping("/dip/good")
    public String runDipGood() {
        com.pafiast.solid.dip.good.PasswordResetService service =
                new com.pafiast.solid.dip.good.PasswordResetService(new com.pafiast.solid.dip.good.SmtpEmailSender());
        service.resetPassword("user@example.com");
        StringBuilder builder = new StringBuilder();
        builder.append("Dependency Inversion Principle (DIP) – Good example\n");
        builder.append("PasswordResetService depends on the EmailSender abstraction instead of a concrete class.\n\n");
        builder.append("Operation:\n");
        builder.append("- EmailSender sender = new SmtpEmailSender()\n");
        builder.append("- PasswordResetService service = new PasswordResetService(sender)\n");
        builder.append("- service.resetPassword(\"user@example.com\")\n\n");
        builder.append("Benefits:\n");
        builder.append("- The high-level service only knows the EmailSender abstraction.\n");
        builder.append("- Different implementations (SMTP, mock, API-based) can be injected without changing the service.\n");
        builder.append("- The code is easier to test and adapt to new infrastructure.");
        return builder.toString();
    }
}
