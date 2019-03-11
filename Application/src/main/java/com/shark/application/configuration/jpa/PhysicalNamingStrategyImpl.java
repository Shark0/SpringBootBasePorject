package com.shark.application.configuration.jpa;

import com.shark.application.util.StringUtil;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class PhysicalNamingStrategyImpl extends PhysicalNamingStrategyStandardImpl {

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        return name.toIdentifier(generateTableName(name.getText()));
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
        return name.toIdentifier(generateColumnName(name.getText()));
    }

    public String generateTableName(String value) {
        String name = value.toUpperCase();
        if ("schema".equalsIgnoreCase(name) ||
                "order".equalsIgnoreCase(name) ||
                "role".equalsIgnoreCase(name)) {
            name = "`" + name + "`";
        }
        return name;
    }

    public String generateColumnName(String value) {
        String name = StringUtil.camelToUnderline(value).toUpperCase();
        if ("schema".equalsIgnoreCase(name) ||
                "order".equalsIgnoreCase(name) ||
                "role".equalsIgnoreCase(name)) {
            name = "`" + name + "`";
        }
        return name;
    }
}
