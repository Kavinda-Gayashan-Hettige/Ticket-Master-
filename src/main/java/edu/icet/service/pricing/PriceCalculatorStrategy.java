package edu.icet.service.pricing;

import edu.icet.model.Event;
import java.math.BigDecimal;

public interface PriceCalculatorStrategy {
    BigDecimal calculate(Event event);
}