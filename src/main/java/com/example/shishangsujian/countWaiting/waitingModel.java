package com.example.shishangsujian.countWaiting;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class waitingModel {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "staffId")
    private String staffId;

    @Column(name = "status")
    private String status;

    @Column(name = "productClassId")
    private String productClassId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductClassId() {
        return productClassId;
    }

    public void setProductClassId(String productClassId) {
        this.productClassId = productClassId;
    }
}
