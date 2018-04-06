package db.migration;

import org.flywaydb.core.api.migration.spring.SpringJdbcMigration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Example of a Spring Jdbc migration.
 */
public class V1_1__Insert_User implements SpringJdbcMigration {
    public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
        jdbcTemplate.execute("\n" +
                "INSERT INTO livre.USUARIO (NOME,LOGIN,SENHA,SETOR) VALUES\n" +
                "\t('Administrador','admin@leucotron.com','����ՔK0�#}v���f','Admin');");
    }
}
