package ru.netology.domain.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;

public class DBHelper {
    private DBHelper() {
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class OrderEntity {
        private String id;
        private String created;
        private String credit_id;
        private String payment_id;
    }

    @SneakyThrows
    public static void getOrderInfo() {
        var runner = new QueryRunner();
        var sqlOrderQuery = "SELECT * FROM app.order_entity ORDER BY created DESC";
        try (
                var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        ) {
            var all = runner.query(conn, sqlOrderQuery, new BeanListHandler<>(OrderEntity.class));
        }
    }

    @SneakyThrows
    public static int getOrderCountInfo() {
        var runner = new QueryRunner();
        var sqlOrderCountQuery = "SELECT COUNT(*) FROM app.order_entity;";
        try (
                var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        ) {
            var count = runner.query(conn, sqlOrderCountQuery, new ScalarHandler<>());
        }
        return 0;
    }

    @SneakyThrows
    public static String getPaymentId() {
        var runner = new QueryRunner();
        String paymentQuery = "SELECT payment_id FROM app.order_entity ORDER BY created DESC";
        try (
                var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        ) {
            return runner.query(conn, paymentQuery, new ScalarHandler<>());
        }
    }

    @SneakyThrows
    public static String getCreditId() {
        var runner = new QueryRunner();
        String creditQuery = "SELECT credit_id FROM app.order_entity ORDER BY created DESC";
        try (
                var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        ) {
            return runner.query(conn, creditQuery, new ScalarHandler<>());
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PaymentEntity {
        private String id;
        private String amount;
        private String created;
        private String status;
        private String transaction_id;
    }

    @SneakyThrows
    public static String getPaymentStatusInfo() {
        var runner = new QueryRunner();
        String sqlPaymentStatusQuery = "SELECT status FROM app.payment_entity ORDER BY created DESC";
        try (
                var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        ) {
            return runner.query(conn, sqlPaymentStatusQuery, new ScalarHandler<>());

        }

    }

    @SneakyThrows
    public static String getPaymentTransactionInfo() {
        var runner = new QueryRunner();
        String sqlTransactionIdQuery = "SELECT transaction_id FROM app.payment_entity ORDER BY created DESC";
        try (
                var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        ) {
            return runner.query(conn, sqlTransactionIdQuery, new ScalarHandler<>());
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreditRequestEntity {
        private String id;
        private String bank_id;
        private String created;
        private String status;
    }

    @SneakyThrows
    public static void getCreditInfo() {
        var runner = new QueryRunner();
        var sqlCreditQuery = "SELECT * FROM app.credit_request_entity ORDER BY created DESC;";
        try (
                var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        ) {
            var all = runner.query(conn, sqlCreditQuery, new BeanListHandler<>(CreditRequestEntity.class));
        }
    }
    @SneakyThrows
    public static String getCreditStatusInfo() {
        var runner = new QueryRunner();
        String creditStatusQuery = "SELECT status FROM app.credit_request_entity ORDER BY created DESC";
        try (
                var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        ) {
            return runner.query(conn, creditStatusQuery, new ScalarHandler<>());

        }

    }
    @SneakyThrows
    public static int getCreditCountInfo() {
        var runner = new QueryRunner();
        var sqlCreditCountQuery = "SELECT COUNT(*) FROM app.credit_request_entity;";
        try (
                var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        ) {
            var count = runner.query(conn, sqlCreditCountQuery, new ScalarHandler<>());
        }
        return 0;
    }

    @SneakyThrows
    public static String getBankId() {
        var runner = new QueryRunner();
        String bankIdQuery = "SELECT bank_id FROM app.credit_request_entity ORDER BY created DESC";
        try (
                var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        ) {
            return runner.query(conn, bankIdQuery, new ScalarHandler<>());
        }
    }

    @SneakyThrows
    public static void DropData() {
        var runner = new QueryRunner();
        var deleteOrders = "DELETE FROM order_entity";
        var deletePayments = "DELETE FROM payment_entity";
        var deleteCreditRequests = "DELETE FROM credit_request_entity";
        try (
                var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        ) {
            runner.update(conn, deleteOrders);
            runner.update(conn, deletePayments);
            runner.update(conn, deleteCreditRequests);
        }
    }
}
