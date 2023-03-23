package tacos.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tacos.Ingredient;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IngredientsByIdConverterTest {

    @Autowired
    IngredientsByIdConverter converter;

    @Test
    @DisplayName("Should convert known ID")
    void shouldConvertKnownId() {
        Ingredient ingredient = new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP);
        assertEquals(ingredient, converter.convert("FLTO"));
    }

    @Test
    @DisplayName("Should throw when trying to convert non-existent ID")
    void shouldThrowWhenTryingToConvertNonExistentId() {
        Exception e = assertThrows(NoSuchElementException.class, () -> converter.convert("XXXX"));
        assertTrue(e.getMessage().contains("No value present"));
    }
}