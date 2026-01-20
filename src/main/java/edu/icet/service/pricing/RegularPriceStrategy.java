package edu.icet.service.pricing;

import edu.icet.model.Event;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component("REGULAR_STRATEGY")
public class RegularPriceStrategy implements PriceCalculatorStrategy {
    @Override
    public BigDecimal calculate(Event event) {
        return event.getBasePrice();
    }
}