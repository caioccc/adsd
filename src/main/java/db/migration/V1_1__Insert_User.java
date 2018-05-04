package db.migration;

import org.flywaydb.core.api.migration.spring.SpringJdbcMigration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Example of a Spring Jdbc migration.
 */
public class V1_1__Insert_User implements SpringJdbcMigration {
    public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
        jdbcTemplate.execute("\n" +
                "INSERT INTO livre.user (name,login,password,tags,role, flag) VALUES\n" +
                "\t('Administrador','admin@leucotron.com.br','��uEjn��R �����','', 'super_usuario', '1');");
        jdbcTemplate.execute("\n" +
                "INSERT INTO livre.role (role) VALUES\n" +
                "\t('super_usuario');");
        jdbcTemplate.execute("\n" +
                "INSERT INTO livre.role (role) VALUES\n" +
                "\t('usuario');");
    }
}
