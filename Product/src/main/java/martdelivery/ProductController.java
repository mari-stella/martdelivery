package martdelivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
 public class ProductController {
     @Autowired  ProductRepository productRepository;

     @RequestMapping(value = "/products/checkAndModifyStock",
          method = RequestMethod.GET,
          produces = "application/json;charset=UTF-8")
     public boolean chkAndModifyStock(@RequestParam("productId") Long productId,
                                      @RequestParam("qty")  int qty)
             throws Exception {

         System.out.println("##### /products/checkAndModifyStock  called #####");
         boolean status = false;
         Optional<Product> productOptional = productRepository.findByProductId(productId);
         if (productOptional.isPresent()) {
             Product product = productOptional.get();



             // Hystrix timeout 점검
             if(product.getProductId() ==2){
                 System.out.println("##################### Hystrix 테스트를 위한 강제 슬립 ");
                 Thread.sleep(5000);
             }
             else {
                 // 현 재고보다 주문수량이 적거나 같은경우에만 true 회신
                 if (product.getQty() >= qty) {
                     status = true;
                     product.setStockBeforeUpdate(product.getQty());
                     product.setQty(product.getQty() - qty); // 주문수량만큼 재고 감소
                     productRepository.save(product);
                 }
             }
         }
         return status;
     }


    @RequestMapping(value = "/products/reStock",
            method = RequestMethod.PATCH,
            produces = "application/json;charset=UTF-8")
    @Transactional
    public void reStock(@RequestBody Product product)
            throws Exception {
        long productId = product.getProductId();
        int qty = product.getQty();

        Optional<Product> productOptional = productRepository.findByProductId(productId);

        if (productOptional.isPresent()) {
            Product originalProduct = productOptional.get();
            originalProduct.setStockBeforeUpdate(originalProduct.getQty());
            originalProduct.setQty(originalProduct.getQty() + qty);
            productRepository.save(originalProduct);
        }
    }
 }
