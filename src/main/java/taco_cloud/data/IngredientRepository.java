package taco_cloud.data;

import org.springframework.data.jpa.repository.JpaRepository;
import taco_cloud.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, String> {
}
