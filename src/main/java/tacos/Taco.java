package tacos;

import java.util.ArrayList;
import java.util.List;

public record Taco(String name, List<Ingredient> ingredients) {
    public Taco() {
        this("", new ArrayList<>());
    }
}
