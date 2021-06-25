package martdelivery;

public class DeliveryCancelled extends AbstractEvent {

    private Long deliveryId;
    private Long productId;
    private Long orderId;
    private Integer qty;
    private String status;

    public Long getDeliveryId() {
        return deliveryId;
    }
    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }


    public Integer getQty() { return qty; }
    public void setQty(Integer qty) { this.qty = qty; }

}
