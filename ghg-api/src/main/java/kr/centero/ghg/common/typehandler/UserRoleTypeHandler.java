package kr.centero.ghg.common.typehandler;

import kr.centero.ghg.client.auth.domain.enums.ERole;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleTypeHandler extends BaseTypeHandler<ERole> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ERole parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.name());
    }

    @Override
    public ERole getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return value == null ? null : ERole.valueOf(value);
    }

    @Override
    public ERole getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return value == null ? null : ERole.valueOf(value);
    }

    @Override
    public ERole getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return value == null ? null : ERole.valueOf(value);
    }
}
