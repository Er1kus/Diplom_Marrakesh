package ru.netology.domain.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

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
        var sqlOrderQuery = "SELECT * FROM order_entity ORDER BY created DESC";
        try (
                var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        ) {
            var all = runner.query(conn, sqlOrderQuery, new BeanListHandler<>(OrderEntity.class));
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
    public static void getPaymentInfo() {
        var runner = new QueryRunner();
        var sqlPaymentQuery = "SELECT * FROM payment_entity ORDER BY created DESC";
        try (
                var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        ) {
            var all = runner.query(conn, sqlPaymentQuery, new BeanListHandler<>(PaymentEntity.class));
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
        var sqlCreditQuery = "SELECT * FROM credit_request_entity ORDER BY created DESC";
        try (
                var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        ) {
            var all = runner.query(conn, sqlCreditQuery, new BeanListHandler<>(CreditRequestEntity.class));
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
