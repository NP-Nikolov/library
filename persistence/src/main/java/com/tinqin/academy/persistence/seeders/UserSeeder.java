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
            INSERT INTO users (id, first_name, last_name, is_admin, is_blocked, username, password)
            VALUES """;

    private final List<String> users = List.of(
            "Atanas Marinov true abcde123 $2a$10$naSBAq/mD76noYengz903u.5jazvGgs3rC5WglyuiwWUbDtjPAmMC",
            "Penio Chernev false abcde124 $2a$10$N232hqJA3x7iEYRqk3ynP.3M0B3KsKrBUCr3q035aaEAHxHFsKmQa",
            "Valentin Tanev false abcde125 $2a$10$HQmctD6AxqWE5vSvCgybZ.j7E7/Opd73lP3H7nzYs8l2E/3GBIyNa"
    );

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String userLines = users.stream()
                .map(user -> user.split(" "))
                .map(userElement -> String.format("(gen_random_uuid(),'%s','%s',%s,false,'%s','%s')", userElement[0], userElement[1], userElement[2], userElement[3], userElement[4]))
                .collect(Collectors.joining(", "));

        String query = INSERT_USER_QUERY_TEMPLATE + userLines;

        //jdbcTemplate.execute(query);
    }
}
