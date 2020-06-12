package org.t01.gdp.mapper;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface SqlMapper {
    List<HashMap<String, Object>> getUserByRole(String role);
}
