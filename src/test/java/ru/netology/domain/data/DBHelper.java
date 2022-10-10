package ru.netology.domain.data;

import lombok.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderEntity {
        private String id;
        private String created;
        private String credit_id;
        private String payment_id;
    }

    private static QueryRunner runner;
    private static Connection conn;
    private static String user = System.getProperty("user");
    private static String password = System.getProperty("pass");
    private static String url = System.getProperty("url");

    @SneakyThrows
    public static void setup() {
        runner = new QueryRunner();
        conn = DriverManager.getConnection(url, user, password);
    }

    @SneakyThrows
    public static OrderEntity getOrderInfo() {
        setup();
        var sqlOrderQuery = "SELECT * FROM order_entity ORDER BY created DESC";
        return runner.query(conn, sqlOrderQuery, new BeanHandler<>(OrderEntity.class));
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
    public static PaymentEntity getPaymentInfo() {
        var sqlPaymentQuery = "SELECT * FROM payment_entity ORDER BY created DESC";
        return runner.query(conn, sqlPaymentQuery, new BeanHandler<>(PaymentEntity.class));
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
    public static CreditRequestEntity getCreditInfo() {
        var sqlCreditQuery = "SELECT * FROM credit_request_entity ORDER BY created DESC;";
        return runner.query(conn, sqlCreditQuery, new BeanHandler<>(CreditRequestEntity.class));
    }

    @SneakyThrows
    public static void DropData() {
        var deleteOrders = "DELETE FROM order_entity";
        var deletePayments = "DELETE FROM payment_entity";
        var deleteCreditRequests = "DELETE FROM credit_request_entity";
        runner.update(conn, deleteOrders);
        runner.update(conn, deletePayments);
        runner.update(conn, deleteCreditRequests);

    }
}
