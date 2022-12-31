package tacos;

import java.util.List;

public record Taco(String name, List<Ingredient> ingredients) {
}
