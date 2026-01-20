package edu.icet.service.pricing;

import edu.icet.model.Event;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component("VIP_STRATEGY")
public class VIPPriceStrategy implements PriceCalculatorStrategy {
    @Override
    public BigDecimal calculate(Event event) {
        if (Boolean.TRUE.equals(event.getIsHighDemand())) {
            return event.getBasePrice();
        }

        return event.getBasePrice().multiply(new BigDecimal("0.9"));
    }
}