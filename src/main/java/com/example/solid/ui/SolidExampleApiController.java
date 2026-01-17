package com.example.solid.ui;

import com.example.solid.dip.bad.PasswordResetService;
import com.example.solid.isp.bad.RobotWorker;
import com.example.solid.isp.bad.Worker;
import com.example.solid.isp.good.Workable;
import com.example.solid.lsp.bad.Document;
import com.example.solid.lsp.bad.ReadOnlyDocument;
import com.example.solid.ocp.bad.CustomerType;
import com.example.solid.ocp.bad.DiscountCalculator;
import com.example.solid.ocp.good.OrderPriceCalculator;
import com.example.solid.ocp.good.PremiumDiscountPolicy;
import com.example.solid.srp.bad.BadUserService;
import com.example.solid.srp.good.AuditLogger;
import com.example.solid.srp.good.ConsoleAuditLogger;
import com.example.solid.srp.good.ConsoleNotificationSender;
import com.example.solid.srp.good.InMemoryUserRepository;
import com.example.solid.srp.good.NotificationSender;
import com.example.solid.srp.good.UserRepository;
import com.example.solid.srp.good.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SolidExampleApiController {

    @GetMapping("/srp/bad")
    public String runSrpBad() {
        BadUserService service = new BadUserService();
        service.createUser("Alice", "alice@example.com");
        StringBuilder builder = new StringBuilder();
        builder.append("SRP bad example\n");
        builder.append("One class handles persistence, notifications, and audit logging.\n\n");
        builder.append("Simulated operation:\n");
        builder.append("- Created user Alice with email alice@example.com\n");
        builder.append("- Sent welcome email directly from the same service\n");
        builder.append("- Wrote audit log directly from the same service\n");
        builder.append("\nAny change in persistence, email, or logging forces this class to change.");
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
        builder.append("SRP good example\n");
        builder.append("UserService coordinates collaborators, each with a single responsibility.\n\n");
        builder.append("Collaborators used:\n");
        builder.append("- InMemoryUserRepository stores user Bob <bob@example.com>\n");
        builder.append("- ConsoleNotificationSender sends welcome notification\n");
        builder.append("- ConsoleAuditLogger records an audit entry\n");
        builder.append("\nChanging persistence, notifications, or logging does not require modifying UserService.");
        return builder.toString();
    }

    @GetMapping("/ocp/bad")
    public String runOcpBad() {
        DiscountCalculator calculator = new DiscountCalculator();
        double discount = calculator.calculateDiscount(CustomerType.PREMIUM, 100.0);
        double finalPrice = 100.0 - discount;
        StringBuilder builder = new StringBuilder();
        builder.append("OCP bad example\n");
        builder.append("DiscountCalculator uses conditionals on CustomerType.\n\n");
        builder.append("Input: customerType = PREMIUM, price = 100.0\n");
        builder.append("Discount: ").append(discount).append("\n");
        builder.append("Final price: ").append(finalPrice).append("\n\n");
        builder.append("To add a new customer type you must modify DiscountCalculator and add another conditional.");
        return builder.toString();
    }

    @GetMapping("/ocp/good")
    public String runOcpGood() {
        OrderPriceCalculator calculator = new OrderPriceCalculator(new PremiumDiscountPolicy());
        double finalPrice = calculator.calculatePrice(100.0);
        double originalPrice = 100.0;
        double discount = originalPrice - finalPrice;
        StringBuilder builder = new StringBuilder();
        builder.append("OCP good example\n");
        builder.append("OrderPriceCalculator depends on the DiscountPolicy abstraction.\n\n");
        builder.append("Input: price = ").append(originalPrice).append("\n");
        builder.append("Policy: PremiumDiscountPolicy\n");
        builder.append("Discount: ").append(discount).append("\n");
        builder.append("Final price: ").append(finalPrice).append("\n\n");
        builder.append("New customer types are supported by adding new DiscountPolicy implementations without changing OrderPriceCalculator.");
        return builder.toString();
    }

    @GetMapping("/lsp/bad")
    public String runLspBad() {
        Document document = new ReadOnlyDocument();
        try {
            document.setContent("New content");
            StringBuilder builder = new StringBuilder();
            builder.append("LSP bad example\n");
            builder.append("ReadOnlyDocument extends Document but allows setContent without error.\n");
            builder.append("This would let callers mutate something they expect to be read‑only.");
            return builder.toString();
        } catch (UnsupportedOperationException ex) {
            StringBuilder builder = new StringBuilder();
            builder.append("LSP bad example\n");
            builder.append("ReadOnlyDocument extends Document but setContent throws UnsupportedOperationException.\n\n");
            builder.append("Operation attempted:\n");
            builder.append("- document.setContent(\"New content\")\n");
            builder.append("Result: UnsupportedOperationException at runtime.\n\n");
            builder.append("Code that works with Document cannot safely substitute ReadOnlyDocument.");
            return builder.toString();
        }
    }

    @GetMapping("/lsp/good")
    public String runLspGood() {
        com.example.solid.lsp.good.ReadableDocument readableDocument =
                new com.example.solid.lsp.good.ReadOnlyTextDocument("Initial content");
        String content = readableDocument.getContent();
        StringBuilder builder = new StringBuilder();
        builder.append("LSP good example\n");
        builder.append("ReadableDocument represents read‑only access, and ReadOnlyTextDocument implements it.\n\n");
        builder.append("Operation:\n");
        builder.append("- ReadableDocument document = new ReadOnlyTextDocument(\"Initial content\")\n");
        builder.append("- document.getContent() -> ").append(content).append("\n\n");
        builder.append("Clients that only need to read depend on ReadableDocument and can use both read‑only and writable implementations safely.");
        return builder.toString();
    }

    @GetMapping("/isp/bad")
    public String runIspBad() {
        Worker worker = new RobotWorker();
        worker.work();
        try {
            worker.eat();
            StringBuilder builder = new StringBuilder();
            builder.append("ISP bad example\n");
            builder.append("Worker interface requires work, eat, and sleep.\n\n");
            builder.append("RobotWorker was able to handle eat without error, even though robots should not eat.\n");
            builder.append("Implementations are forced to support operations they do not need.");
            return builder.toString();
        } catch (UnsupportedOperationException ex) {
            StringBuilder builder = new StringBuilder();
            builder.append("ISP bad example\n");
            builder.append("Worker interface requires work, eat, and sleep.\n\n");
            builder.append("Operation sequence:\n");
            builder.append("- RobotWorker.work()\n");
            builder.append("- RobotWorker.eat() -> UnsupportedOperationException\n\n");
            builder.append("RobotWorker is forced to implement methods it cannot support, so clients depending on Worker get methods that may fail at runtime.");
            return builder.toString();
        }
    }

    @GetMapping("/isp/good")
    public String runIspGood() {
        Workable worker = new com.example.solid.isp.good.RobotWorker();
        worker.work();
        StringBuilder builder = new StringBuilder();
        builder.append("ISP good example\n");
        builder.append("Capabilities are split into Workable, Eatable, and Sleepable.\n\n");
        builder.append("RobotWorker only implements Workable:\n");
        builder.append("- Workable worker = new RobotWorker()\n");
        builder.append("- worker.work() succeeds\n\n");
        builder.append("Clients that only need work depend on Workable and are not exposed to eat or sleep operations.");
        return builder.toString();
    }

    @GetMapping("/dip/bad")
    public String runDipBad() {
        PasswordResetService service = new PasswordResetService();
        service.resetPassword("user@example.com");
        StringBuilder builder = new StringBuilder();
        builder.append("DIP bad example\n");
        builder.append("PasswordResetService creates SmtpEmailSender with new inside the method.\n\n");
        builder.append("Operation:\n");
        builder.append("- new PasswordResetService().resetPassword(\"user@example.com\")\n");
        builder.append("The service is tightly coupled to a concrete SMTP implementation, which makes testing and swapping implementations hard.");
        return builder.toString();
    }

    @GetMapping("/dip/good")
    public String runDipGood() {
        com.example.solid.dip.good.PasswordResetService service =
                new com.example.solid.dip.good.PasswordResetService(new com.example.solid.dip.good.SmtpEmailSender());
        service.resetPassword("user@example.com");
        StringBuilder builder = new StringBuilder();
        builder.append("DIP good example\n");
        builder.append("PasswordResetService depends on the EmailSender abstraction.\n\n");
        builder.append("Operation:\n");
        builder.append("- EmailSender sender = new SmtpEmailSender()\n");
        builder.append("- PasswordResetService service = new PasswordResetService(sender)\n");
        builder.append("- service.resetPassword(\"user@example.com\")\n\n");
        builder.append("The high‑level service only knows EmailSender, so different implementations can be injected without changing the service.");
        return builder.toString();
    }
}
