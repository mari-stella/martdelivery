package martdelivery;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name="Delivery_table")
public class Delivery {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long deliveryId;
    private Long orderId;
    private Long productId;
    private String address;
    private String status;
    private Integer qty;

    @PostPersist
    public void onPostPersist(){

        if (this.status == "Order-Delivery") {
            DeliveryStarted deliveryStarted = new DeliveryStarted();
            BeanUtils.copyProperties(this, deliveryStarted);
            System.out.println("ProductId : " + this.getProductId());
            deliveryStarted.setOrderId(this.orderId);
            deliveryStarted.setProductId(this.productId);
            deliveryStarted.setAddress(this.address);
            System.out.println("Orderid : " + deliveryStarted.getOrderId());
            deliveryStarted.setDeliveryId((long) (Math.random()%100));
            System.out.println("Deliveryid : " + deliveryStarted.getDeliveryId());
            deliveryStarted.setStatus("Delivery Start");
            deliveryStarted.publishAfterCommit();
        }

    }
    @PostUpdate
    public void onPostUpdate(){
        if (this.status == "Order Cancel-Delivery") {
            DeliveryCancelled deliveryCancelled = new DeliveryCancelled();
            BeanUtils.copyProperties(this, deliveryCancelled);
            deliveryCancelled.setOrderId(this.orderId);
            deliveryCancelled.setProductId(this.productId);
            deliveryCancelled.setQty(this.qty); 
            deliveryCancelled.setStatus("Delivery Cancel");
            deliveryCancelled.publishAfterCommit();
        }
    }

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
    public void setOrderId(Long orderId) {   this.orderId = orderId;  }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Integer getQty() {
        return qty;
    }
    public void setQty(Integer qty) {
        this.qty = qty;
    }


}
