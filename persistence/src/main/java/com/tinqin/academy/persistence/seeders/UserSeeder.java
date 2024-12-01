package com.tinqin.academy.persistence.seeders;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Order(3)
public class UserSeeder implements ApplicationRunner {

    private final JdbcTemplate jdbcTemplate;

    private final String INSERT_USER_QUERY_TEMPLATE = """
            INSERT INTO users (id, first_name, last_name, is_admin, is_blocked)
            VALUES """;

    private final List<String> users = List.of(
            "Atanas Marinov true",
            "Penio Chernev false",
            "Valentin Tanev false"
    );

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String userLines = users.stream()
                .map(user -> user.split(" "))
                .map(userElement -> String.format("(gen_random_uuid(),'%s','%s',%s,false)", userElement[0], userElement[1], userElement[2]))
                .collect(Collectors.joining(", "));

        String query = INSERT_USER_QUERY_TEMPLATE + userLines;

        //jdbcTemplate.execute(query);
    }
}
