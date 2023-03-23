package tacos.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tacos.Ingredient;

import java.util.List;
import java.util.Optional;

// When Spring Data JDBC is on the classpath, Spring automatically creates an instance of JdbcTemplate and injects it
// into our constructor. This JdbcTemplate instance is a Bean that uses the properties that we've set in our
// application.yaml.
// Remark: Spring only creates a single instance of JdbcTemplate that will be used by every JDBC DAO in the application.
@Repository
public class JdbcIngredientRepository implements IngredientRepository {
    private static final Logger log = LoggerFactory.getLogger(JdbcIngredientRepository.class);

    private final RowMapper<Ingredient> ingredientRowMapper = (rs, rowNum) -> new Ingredient(
            rs.getString("id"),
            rs.getString("name"),
            Ingredient.Type.valueOf(rs.getString("type"))
    );

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Ingredient> findAll() {
        return jdbcTemplate.query("select id, name, type from ingredient", ingredientRowMapper);
    }

    @Override
    public Optional<Ingredient> findById(String id) {
        String sql = "select id, name,type from ingredient where id = ?";
        Ingredient ingredient = null;
        try {
            ingredient = jdbcTemplate.queryForObject(sql, ingredientRowMapper, id);
        } catch (DataAccessException e) {
            log.info("Ingredient not found: " + id);
        }
        return Optional.ofNullable(ingredient);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        String sql = "insert into ingredient(id, name, type) values(?, ?, ?)";
        int affectedRows = jdbcTemplate.update(sql, ingredient.getId(), ingredient.getName(), ingredient.getType());
        if (affectedRows == 1) {
            log.info("New ingredient added: " + ingredient.getName());
        }
        return ingredient;
    }
}
