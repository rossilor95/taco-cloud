package tacos.design;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import tacos.order.TacoOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DesignTacoControllerTest {
    AutoCloseable closeable;

    @Mock
    IngredientRepository ingredientRepository;

    @Mock
    Model model;

    @Mock
    Errors errors;

    @BeforeEach
    void setup() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();

    }

    @Test
    public void shouldAddIngredientsToModel() {
        // Given
        var ingredients = List.of(
                new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE)
        );
        when(ingredientRepository.findAll()).thenReturn(ingredients);
        var designTacoController = new DesignTacoController(ingredientRepository);

        // When
        designTacoController.addIngredientsToModel(model);

        // Then
        assertAll(
                () -> verify(model, times(1)).addAttribute("wrap", ingredients.subList(0, 1)),
                () -> verify(model, times(1)).addAttribute("protein", ingredients.subList(1, 2)),
                () -> verify(model, times(1)).addAttribute("cheese", ingredients.subList(2, 3))
        );
    }

    @Test
    public void shouldProcessTaco() {
        // Given
        var ingredients = List.of(
                new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE)
        );
        var taco = new Taco("Test Taco", ingredients);
        var tacoOrder = new TacoOrder();
        var designerTacoController = new DesignTacoController(ingredientRepository);

        // When
        String viewName = designerTacoController.processTaco(taco, errors, tacoOrder);

        // Then
        assertEquals("redirect:/orders/current", viewName);
        assertTrue(tacoOrder.getTacos().contains(taco));
    }
}