package com.example.shishangsujian.commodity;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/commodity")
public class commodityController {

    @Autowired
    private commodityService commodityService;

    @Autowired
    private commoditypayService commoditypayService;

    // 获取商品列表
    @GetMapping("/list")
    public ResponseEntity<List<commodityModel>> getCommodityList() {
        List<commodityModel> commodities = commodityService.getCommodityList();
        return ResponseEntity.ok().body(commodities);
    }

    // 创建商品
    @PostMapping("/create")
    public ResponseEntity<commodityModel> createCommodity(@RequestParam("price") double price,
                                                          @RequestParam("description") String description,
                                                          @RequestParam("photo") MultipartFile photo) {
        try {
            commodityModel newCommodity = commodityService.createCommodity(price, description, photo);
            return ResponseEntity.ok(newCommodity);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // 删除商品
    @GetMapping("/delete")
    public ResponseEntity<Void> deleteCommodity(@RequestParam int id) {
        commodityService.deleteCommodity(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/pay")
    public ResponseEntity<?> createPrepayOrder(@RequestParam("price") double price,
                                               @RequestParam("name") String name,
                                               @RequestParam("commodityId") int commodityId,
                                               @RequestParam("staffId") int staffId,
                                               @RequestParam("count") int count,
                                               HttpSession session) {
        try {
            Map<String, String> prepayInfo = commoditypayService.createPrepayOrder(price, name, commodityId,  staffId, count,session);
            return ResponseEntity.ok().body(prepayInfo);
        } catch (Exception e) {
            // 日志记录异常或者返回具体的错误信息
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating prepay order: " + e.getMessage());
        }
    }

    @PostMapping("/pay/notify")
    public ResponseEntity<String> handleWXNotification(@RequestBody String xmlData) {
        String response = commoditypayService.handlePaymentNotification(xmlData);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_XML).body(response);
    }

    @GetMapping("/getstaffCommodity")
    public List<commodityPayModel> getSCommodityOrders(@RequestParam int staffId){
        return commodityService.findSpecificCommodity(staffId);
    }
}
