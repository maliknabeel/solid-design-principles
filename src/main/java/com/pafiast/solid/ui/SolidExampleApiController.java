package com.pafiast.solid.ui;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pafiast.solid.dip.bad.PasswordResetService;
import com.pafiast.solid.isp.bad.RobotWorker;
import com.pafiast.solid.isp.bad.Worker;
import com.pafiast.solid.lsp.bad.Document;
import com.pafiast.solid.lsp.bad.ReadOnlyDocument;
import com.pafiast.solid.ocp.bad.CustomerType;
import com.pafiast.solid.ocp.bad.DiscountCalculator;
import com.pafiast.solid.srp.bad.BadUserService;

/**
 * REST controller that exposes endpoints demonstrating each SOLID principle.
 * <p>
 * This controller provides endpoints that run good and bad examples of:
 * <ul>
 *   <li>Single Responsibility Principle (SRP)</li>
 *   <li>Open/Closed Principle (OCP)</li>
 *   <li>Liskov Substitution Principle (LSP)</li>
 *   <li>Interface Segregation Principle (ISP)</li>
 *   <li>Dependency Inversion Principle (DIP)</li>
 * </ul>
 * Each endpoint returns a descriptive string explaining the principle and the example execution.
 */
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
        return "TODO: Implement the Single Responsibility Principle (SRP) good example.\n" +
               "1. Create a User class responsible only for data.\n" +
               "2. Create separate interfaces/classes for UserRepository, NotificationSender, and AuditLogger.\n" +
               "3. Create a UserService that coordinates these collaborators.\n" +
               "4. Verify your solution here.";
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
        return "TODO: Implement the Open/Closed Principle (OCP) good example.\n" +
               "1. Create a DiscountPolicy interface.\n" +
               "2. Implement different policies (Standard, Premium, VIP).\n" +
               "3. Refactor OrderPriceCalculator to depend on the interface.\n" +
               "4. Verify your solution here.";
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
        return "TODO: Implement the Liskov Substitution Principle (LSP) good example.\n" +
               "1. Separate read-only and writable responsibilities (e.g., ReadableDocument interface).\n" +
               "2. Ensure ReadOnlyTextDocument implements only the reading interface.\n" +
               "3. Ensure WritableDocument extends the reading interface and adds writing capabilities.\n" +
               "4. Verify your solution here.";
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
        return "TODO: Implement the Interface Segregation Principle (ISP) good example.\n" +
               "1. Break the large Worker interface into smaller interfaces (Workable, Eatable, Sleepable).\n" +
               "2. Have RobotWorker implement only Workable.\n" +
               "3. Have HumanWorker implement all relevant interfaces.\n" +
               "4. Verify your solution here.";
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
        return "TODO: Implement the Dependency Inversion Principle (DIP) good example.\n" +
               "1. Create an EmailSender interface.\n" +
               "2. Implement SmtpEmailSender implementing that interface.\n" +
               "3. Refactor PasswordResetService to depend on EmailSender (constructor injection).\n" +
               "4. Verify your solution here.";
    }
}
