package tacos;

import java.util.Collections;
import java.util.List;

public record TacoOrder(
        String deliveryCity,
        String deliveryStreet,
        String deliveryName,
        String deliveryState,
        String deliveryZip,
        String ccNumber,
        String ccExpiration,
        String ccCVV,
        List<Taco> tacos
) {
    public TacoOrder() {
        this("","", "", "", "", "", "", "", Collections.emptyList());
    }

    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }
}
