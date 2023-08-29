package br.com.fastfood.cashier.database.infra;

import br.com.fastfood.cashier.database.infra.model.OrderDocument;
import br.com.fastfood.cashier.database.infra.model.ProductDocument;
import br.com.fastfood.cashier.database.infra.model.ProductOrderDocument;
import br.com.fastfood.cashier.database.infra.mongorepo.OrderMongoDatabaseImpl;
import br.com.fastfood.cashier.database.infra.mongorepo.ProductMongoDatabaseImpl;
import br.com.fastfood.cashier.database.infra.mongorepo.ProductOrderMongoDatabaseImpl;
import br.com.fastfood.cashier.database.repositories.OrderRepository;
import br.com.fastfood.cashier.database.repositories.ProductOrderRepository;
import br.com.fastfood.cashier.domain.entities.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderMongoRepository implements OrderRepository {

    private final OrderMongoDatabaseImpl orderMongoDatabase;
    private final ProductMongoDatabaseImpl productMongoDatabase;
    private final ProductOrderMongoDatabaseImpl productOrderMongoDatabase;
    private final ProductOrderRepository productOrderRepository;
    @Override
    public void saveOrder(Order order) {
        log.info("Salvando ordem...");
        log.info(order.toString());

        var orderDocument = new OrderDocument();
        orderDocument.setOrderNumber(order.getOrderNumber());
        orderDocument.setOrderAt(order.getOrderAt());
        orderDocument.setId(order.getId());
        orderDocument.setStatus(order.getStatus().toString());
        orderDocument.setClientId(order.getClientId());
        orderMongoDatabase.save(orderDocument);

        List<ProductOrderDocument> collected = order.getProducts()
                .stream().
                map(productOrder ->
                        new ProductOrderDocument(productOrder.getId(), productOrder.getOrderId(), productOrder.getProduct().getId(), productOrder.getStatus().toString()))
                .collect(Collectors.toList());

        productOrderMongoDatabase.saveAll(collected);
    }

    @Override
    public Order getById(String orderId) {
        var orderDoc = orderMongoDatabase.findById(orderId).orElseThrow(() -> new RuntimeException("Não encontrado"));

        List<ProductOrderDocument> productOrderDocumentList = productOrderMongoDatabase.findAllByOrderId(orderId);
        List<ProductOrder> productOrders = new ArrayList<>();
        for (ProductOrderDocument productOrderDocument : productOrderDocumentList) {
            ProductDocument document = productMongoDatabase.findById(productOrderDocument.getProductId()).orElseThrow(() -> new RuntimeException("Erro"));
            Product product = new Product(document.getId(), document.getName(), document.getDescription(), document.getPrice());
            ProductOrder productOrder = new ProductOrder(productOrderDocument.getId(), productOrderDocument.getOrderId(), product, ProductOrderStatus.valueOf(productOrderDocument.getStatus()));
            productOrders.add(productOrder);
        }

        return new Order(orderDoc.getId(), orderDoc.getOrderNumber(), orderDoc.getOrderAt(), OrderStatus.valueOf(orderDoc.getStatus()), productOrders, orderDoc.getClientId());
    }

    @Override
    public List<Order> getLastTen() {
        Page<OrderDocument> lastTen = orderMongoDatabase.findAll(Pageable.ofSize(10));
        List<OrderDocument> collected = lastTen.get().collect(Collectors.toList());
        List<Order> orders = new ArrayList<>();
        for (OrderDocument orderDocument : collected) {
            List<ProductOrderDocument> allByOrderId = productOrderMongoDatabase.findAllByOrderId(orderDocument.getId());
            List<ProductOrder> collect = allByOrderId.stream().map(po -> productOrderRepository.getById(po.getId())).collect(Collectors.toList());
            Order order = new Order(orderDocument.getId(), orderDocument.getOrderNumber(), orderDocument.getOrderAt(), OrderStatus.valueOf(orderDocument.getStatus()), collect, orderDocument.getClientId());
            orders.add(order);
        }
        return orders;
    }
}
