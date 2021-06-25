package martdelivery;

import martdelivery.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class PolicyHandler{
    @Autowired
    MarketingRepository marketingRepository;
    @Autowired
    MyPageRepository myPageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverProductRegistered_BrandNewNotice(@Payload ProductRegistered productRegistered){

        if(!productRegistered.validate()) return;
        // Get Methods


        Iterable<Marketing> iterable = marketingRepository.findAll();

        // Send SNS with iterable HERE.
        iterable.forEach(new Consumer<Marketing>() {
            @Override
            public void accept(Marketing marketing) {
                System.out.println("#######################################################################");
                System.out.println("##### Send Email to Customer("+ marketing.getEmail() +") that the new product [" + productRegistered.getProductName() + "] is arrived.");
                System.out.println("#######################################################################");
            }
        });
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
