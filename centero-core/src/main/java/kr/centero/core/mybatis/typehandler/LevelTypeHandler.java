package kr.centero.core.mybatis.typehandler;

import kr.centero.core.domains.enums.ELevel;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LevelTypeHandler extends BaseTypeHandler<ELevel> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ELevel parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.name());
    }

    @Override
    public ELevel getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return value == null ? null : ELevel.valueOf(value);
    }

    @Override
    public ELevel getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return value == null ? null : ELevel.valueOf(value);
    }

    @Override
    public ELevel getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return value == null ? null : ELevel.valueOf(value);
    }
}
