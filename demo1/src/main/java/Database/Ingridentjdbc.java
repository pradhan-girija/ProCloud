package Database;
import java.sql.*;
import com.example.demo1.Ingridents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
public class Ingridentjdbc implements IngridentsRepo{
    private JdbcTemplate jdbc;
    @Autowired
    public Ingridentjdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    @Override
    public Iterable<Ingridents> findAll() {
        return jdbc.query("select id, name, type from Ingredient", this::mapRowToIngredient);
    }
    @Override
    public Ingridents findOne(String id) {
        return jdbc.queryForObject(
                "select id, name, type from Ingredient where id=?",
                this::mapRowToIngredient, id);
    }
    private Ingridents mapRowToIngredient(ResultSet rs, int rowNum)
            throws SQLException {
        return new Ingridents(
                rs.getString("id"),
                rs.getString("name"),
                Ingridents.Type.valueOf(rs.getString("type")));
    }
}
