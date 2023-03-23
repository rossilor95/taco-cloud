package tacos.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tacos.Ingredient;

import java.util.Optional;

// When Spring Data JDBC is on the classpath, Spring automatically creates an instance of JdbcTemplate and injects it
// into our constructor. This JdbcTemplate instance is a Bean that uses the properties that we've set in our
// application.yaml.
// Remark: Spring only creates a single instance of JdbcTemplate that will be used by every JDBC DAO in the application.
@Repository
public class JdbcIngredientRepository implements IngredientRepository {
    private static final Logger log = LoggerFactory.getLogger(JdbcIngredientRepository.class);
    RowMapper<Ingredient> ingredientRowMapper = (rs, rowNum) -> {
        Ingredient ingredient = new Ingredient(
                rs.getString("id"),
                rs.getString("name"),
                Ingredient.Type.valueOf(rs.getString("type"))
        );
        return ingredient;
    };
    private JdbcTemplate jdbcTemplate;


    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return jdbcTemplate.query("select id, name, type from ingredient", ingredientRowMapper);
    }

    @Override
    public Optional<Ingredient> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        return null;
    }
}
