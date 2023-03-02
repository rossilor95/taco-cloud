package tacos.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tacos.Ingredient;

import static org.junit.jupiter.api.Assertions.*;

class IngredientsByIdConverterTest {

    @Test
    @DisplayName("Should convert known ID")
    void shouldConvertKnownId() {
        IngredientsByIdConverter converter = new IngredientsByIdConverter();
        Ingredient ingredient = new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP);
        assertEquals(ingredient, converter.convert("FLTO"));
    }

    @Test
    @DisplayName("Should not convert unknown ID")
    void shouldNotConvertUnknownId() {
        IngredientsByIdConverter converter = new IngredientsByIdConverter();
        assertNull(converter.convert("XXXX"));
    }
}