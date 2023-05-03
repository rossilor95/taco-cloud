package tacos.design;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public class Taco {

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    @NotNull
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;

    private Long id;

    private ZonedDateTime createdAt;

    public Taco() {
    }

    public Taco(String name, List<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
        this.createdAt = ZonedDateTime.now(ZoneId.of("Europe/Rome"));
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Taco{" +
                "name='" + name + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }
}
