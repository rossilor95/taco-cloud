package tacos.design;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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

@ExtendWith(MockitoExtension.class)
class DesignTacoControllerTest {
    private final List<Ingredient> INGREDIENTS = List.of(
            new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
            new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
            new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE)
    );

    @Mock
    private IngredientRepository ingredientRepository;

    @Mock
    private Model model;

    @Mock
    private Errors errors;

    private DesignTacoController designTacoController;

    @BeforeEach
    void setup() {
        designTacoController = new DesignTacoController(ingredientRepository);
    }

    @Test
    public void shouldAddIngredientsToModel() {
        // Given
        when(ingredientRepository.findAll()).thenReturn(INGREDIENTS);

        // When
        designTacoController.addIngredientsToModel(model);

        // Then
        assertAll(
                () -> verify(model, times(1)).addAttribute("wrap", INGREDIENTS.subList(0, 1)),
                () -> verify(model, times(1)).addAttribute("protein", INGREDIENTS.subList(1, 2)),
                () -> verify(model, times(1)).addAttribute("cheese", INGREDIENTS.subList(2, 3))
        );
    }

    @Test
    public void shouldProcessTaco() {
        // Given
        var taco = new Taco("Test Taco", INGREDIENTS);
        var tacoOrder = new TacoOrder();

        // When
        String viewName = designTacoController.processTaco(taco, errors, tacoOrder);

        // Then
        assertEquals("redirect:/orders/current", viewName);
        assertTrue(tacoOrder.getTacos().contains(taco));
    }
}