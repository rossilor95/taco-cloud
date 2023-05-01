package taco_cloud.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import taco_cloud.Ingredient;
import taco_cloud.data.IngredientRepository;

/**
 * This class automatically converts ingredients string ids returned from the <code>/design</code> template view into
 * ingredient types.
 */
@Component
public class IngredientsByIdConverter implements Converter<String, Ingredient> {
    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientsByIdConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(@NonNull String id) {
        return ingredientRepository.findById(id).orElseThrow();
    }
}
