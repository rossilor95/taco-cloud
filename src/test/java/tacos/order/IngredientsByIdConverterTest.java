package tacos.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import tacos.design.Ingredient;
import tacos.design.IngredientRepository;
import tacos.design.IngredientsByIdConverter;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class IngredientsByIdConverterTest {

    @Autowired
    IngredientsByIdConverter converter;
    @MockBean
    private IngredientRepository ingredientRepository;

    @Test
    @DisplayName("Should convert known ID")
    void shouldConvertKnownId() {
        // Given
        Ingredient expectedIngredient = new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP);
        when(ingredientRepository.findById("FLTO")).thenReturn(Optional.of(expectedIngredient));

        // When
        Ingredient result = converter.convert("FLTO");

        // Then
        assertEquals(expectedIngredient, result);
    }

    @Test
    @DisplayName("Should throw when trying to convert non-existent ID")
    void shouldThrowWhenTryingToConvertNonExistentId() {
        // Given
        when(ingredientRepository.findById(anyString())).thenReturn(Optional.empty());

        // When and Then
        Exception e = assertThrows(NoSuchElementException.class, () -> converter.convert("XXXX"));
        assertTrue(e.getMessage().contains("No value present"));
    }
}