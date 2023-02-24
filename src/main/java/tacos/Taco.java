package tacos;

import java.util.Collections;
import java.util.List;

public record Taco(String name, List<Ingredient> ingredients) {
    public Taco() {
        this("", Collections.emptyList());
    }
}
