package martdelivery;

import martdelivery.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class MarketingViewHandler {


    @Autowired
    private MarketingRepository marketingRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCustomerRegistered_then_CREATE_1 (@Payload CustomerRegistered customerRegistered) {
        try {

            if (!customerRegistered.validate()) return;

            // view 객체 생성
            Marketing marketing = new Marketing();
            // view 객체에 이벤트의 Value 를 set 함
            marketing.setCustomerId(customerRegistered.getCustomerId());
            marketing.setEmail(customerRegistered.getEmail());
            // view 레파지 토리에 save
            marketingRepository.save(marketing);

        }catch (Exception e){
            e.printStackTrace();
        }
    }



}

