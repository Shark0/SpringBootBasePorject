package com.shark.application.config.jpa;

import com.shark.application.util.StringUtil;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class PhysicalNamingStrategyImpl extends PhysicalNamingStrategyStandardImpl {

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {

        String tableName = name.getText().toUpperCase();
        return name.toIdentifier(tableName);
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
        String columnName = StringUtil.camelToUnderline(name.getText()).toUpperCase();
        if ("schema".equalsIgnoreCase(columnName) || "order".equalsIgnoreCase(columnName)) {
            columnName = "`" + columnName + "`";
        }
        return name.toIdentifier(columnName);
    }
}
