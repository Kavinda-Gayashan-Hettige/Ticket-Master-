package edu.icet.service;

import edu.icet.model.Event;
import edu.icet.model.User;
import edu.icet.service.pricing.PriceCalculatorStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PriceCalculatorService {

    private final ApplicationContext context;

    public BigDecimal getTicketPrice(User user, Event event) {
        if (user == null || user.getTier() == null) {

            return context.getBean("REGULAR_STRATEGY", PriceCalculatorStrategy.class).calculate(event);
        }

        PriceCalculatorStrategy strategy;


        switch (user.getTier()) {
            case VIP:
                strategy = context.getBean("VIP_STRATEGY", PriceCalculatorStrategy.class);
                break;
            case PLATINUM:
                strategy = context.getBean("PLATINUM_STRATEGY", PriceCalculatorStrategy.class);
                break;
            default:
                strategy = context.getBean("REGULAR_STRATEGY", PriceCalculatorStrategy.class);
                break;
        }

        return strategy.calculate(event);
    }
}