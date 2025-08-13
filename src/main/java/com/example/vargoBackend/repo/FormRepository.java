package com.example.vargoBackend.repo;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.vargoBackend.model.Form;

@Repository
public class FormRepository {

    private final NamedParameterJdbcTemplate jdbc;

    public FormRepository(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void submitForm(Form form) {
        var sql = """
        INSERT INTO forms (name, age, title, hometown)
        VALUES (:name, :age, :title, :hometown)
    """;

        jdbc.update(sql, Map.of(
                "name", form.name(),
                "age", form.age(),
                "title", form.title(),
                "hometown", form.hometown()
        ));
    }

    public List<Form> findAllForms() {
        String sql = "SELECT name, age, title, hometown FROM forms ORDER BY id DESC";
        return jdbc.query(sql, (rs, i)
                -> new Form(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("title"),
                        rs.getString("hometown")
                )
        );
    }

}
