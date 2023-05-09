package kr.megaptera.backendsurvivalweek10;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

@Component
public class AppRunner implements CommandLineRunner {
    private final JdbcTemplate jdbcTemplate;
    private final TransactionTemplate transactionTemplate;

    public AppRunner(JdbcTemplate jdbcTemplate,
                     TransactionTemplate transactionTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public void run(String... args) {
        transactionTemplate.execute(status -> {
            String query = """
                CREATE TABLE IF NOT EXISTS users (
                id varchar(255) PRIMARY KEY,
                username varchar(255) NOT NULL,
                password varchar(255) NOT NULL,
                role varchar(255) NOT NULL,
                created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
                )
                """;

            jdbcTemplate.execute(query);

            return null;
        });

        transactionTemplate.execute(status -> {
            String query = """
                CREATE TABLE IF NOT EXISTS access_tokens (
                token varchar(255) PRIMARY KEY,
                user_id varchar(255) NOT NULL,
                created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
                )
                """;

            jdbcTemplate.execute(query);

            return null;
        });

        transactionTemplate.execute(status -> {
            String query = """
                INSERT INTO users (id, username, password, role)
                SELECT ?, ?, ?, ?
                WHERE NOT EXISTS (SELECT * FROM users WHERE username='admin');
                """;

            String id = "0C9PJRD75Z2Z6";
            String username = "admin";
            String password = "$argon2id$v=19$m=16384,t=2,p=1$RAxo+OMKDTozCz4B0OoCfQ$5rXxmWLptPVvu7biWD1e7p4tRrP10av1Cekh0MgrsLE";
            String role = "ROLE_ADMIN";

            jdbcTemplate.update(query, id, username, password, role);

            return null;
        });

        transactionTemplate.execute(status -> {
            String query = """
                INSERT INTO access_tokens (token, user_id)
                SELECT ?, ?
                WHERE NOT EXISTS (SELECT * FROM access_tokens WHERE user_id='0C9PJRD75Z2Z6');
                """;

            String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIwQzlQSlJENzVaMlo2In0.uoseuP4FsFauJiH7JaDgTmpCvJWm5gZRibd1mrmEKR4";
            String id = "0C9PJRD75Z2Z6";

            jdbcTemplate.update(query, token, id);

            return null;
        });
    }
}
