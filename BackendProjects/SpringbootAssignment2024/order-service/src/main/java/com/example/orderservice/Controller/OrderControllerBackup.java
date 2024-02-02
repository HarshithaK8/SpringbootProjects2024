//package com.example.orderservice.Controller;
//
//import com.example.orderservice.Model.Inventory;
//import com.example.orderservice.Model.Order;
//import com.example.orderservice.Model.Product;
//import com.example.orderservice.Service.OrderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//import java.util.Objects;
//
//@RestController
//public class OrderControllerBackup {
//
//    @Autowired
//    OrderService orderService;
//
//    @PostMapping(value = "/saveorder")
//    public ResponseEntity<?> saveOrder(@RequestBody Order order) {
////        RestTemplate restTemplate = new RestTemplate();
////        Product product = restTemplate.getForObject("http://localhost:8080/productByName/" + order.getProduct_name(), Product.class);
//
//        WebClient webClient = WebClient.create("http://localhost:8080");
//        Mono<Product> response = webClient.get()
//                .uri("/productByName/" + order.getProduct_name())
//                .retrieve()
//                .bodyToMono(Product.class);
//        Product product = response.block();
//
////        response.subscribe(product -> {
////            if (isValidOrder(product.getInventory(), order)) {
////                // do something if the product is valid
////                Order newOrder = orderService.saveOrder(order);
////
////                product.getInventory().setAvailable(updateAvailability(product.getInventory(), order));
////                product.getInventory().setReserved(updateReservedQualntity(product.getInventory(), order));
////
//////                restTemplate.put("http://localhost:8080/inventory/update", product.getInventory());
////
////                webClient.put()
////                        .uri("/inventory/update")
////                        .bodyValue(product.getInventory())
////                        .retrieve()
////                        .bodyToMono(Void.class)
////                        .block();
////
////                ResponseEntity<?> responseEntity =  new ResponseEntity<>(
////                        newOrder,
////                        HttpStatus.OK);
////            } else {
////                // do something if the product is not valid
////                ResponseEntity<?> responseEntity =  new ResponseEntity<>("No stock available",
////                        HttpStatus.NOT_FOUND);
////            }
////        });
////
////        return new ResponseEntity<>("Service error",
////                HttpStatus.SERVICE_UNAVAILABLE);
////    }
//
//
//        boolean canPlaceOrder = isValidOrder(Objects.requireNonNull(product.getInventory()), order);
//        if (canPlaceOrder) {
//            Order newOrder = orderService.saveOrder(order);
//
//            product.getInventory().setAvailable(updateAvailability(product.getInventory(), order));
//            product.getInventory().setReserved(updateReservedQualntity(product.getInventory(), order));
//
//            webClient.put()
//            .uri("/inventory/update")
//            .bodyValue(product.getInventory())
//            .retrieve()
//            .bodyToMono(Void.class)
//            .block();
//            return new ResponseEntity<>(
//                    newOrder,
//                    HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("No stock available",
//                    HttpStatus.NOT_FOUND);
//        }
//    }
//
//
//    private boolean isValidOrder(Inventory inventory,Order order) {
//        return order.getQuantity() < inventory.getAvailable();
//    }
//
//    private int updateReservedQualntity(Inventory inventory, Order order) {
//        return inventory.getReserved()+order.getQuantity();
//    }
//
//    private int updateAvailability(Inventory inventory, Order order) {
//        return inventory.getAvailable() - order.getQuantity();
//    }
//}
