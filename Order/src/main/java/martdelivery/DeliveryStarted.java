
package martdelivery;

public class DeliveryStarted extends AbstractEvent {
    private Long deliveryId;
    private Long productId;
    private Long orderId;
    private String status;
    private String address;
    private Integer qty;

    public DeliveryStarted(){
        super();
    }

    public Long getDeliveryId() {
        return deliveryId;
    }
    public void setDeliveryId(Long deliveryId) { this.deliveryId = deliveryId; }

    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) { this.productId = productId; }

    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String adress) {
        this.address = adress;
    }


    public Integer getQty() {
        return qty;
    }
    public void setQty(Integer qty) {
        this.qty = qty;
    }
}

