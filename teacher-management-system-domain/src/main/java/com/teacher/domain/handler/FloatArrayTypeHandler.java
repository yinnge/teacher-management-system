package com.teacher.domain.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.*;

public class FloatArrayTypeHandler implements TypeHandler<float[]> {

    @Override
    public void setParameter(PreparedStatement ps, int i, float[] parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");   // 注意 pgvector 要求有 []
            for (int j = 0; j < parameter.length; j++) {
                sb.append(parameter[j]);
                if (j < parameter.length - 1) {
                    sb.append(",");
                }
            }
            sb.append("]");

            ps.setObject(i, sb.toString(), Types.OTHER); // 必须用 OTHER
        } else {
            ps.setNull(i, Types.OTHER);
        }
    }

    @Override
    public float[] getResult(ResultSet rs, String columnName) throws SQLException {
        return parse(rs.getString(columnName));
    }

    @Override
    public float[] getResult(ResultSet rs, int columnIndex) throws SQLException {
        return parse(rs.getString(columnIndex));
    }

    @Override
    public float[] getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return parse(cs.getString(columnIndex));
    }

    private float[] parse(String val) {
        if (val == null || val.isEmpty()) return null;

        // 去掉 []
        val = val.replace("[", "").replace("]", "");

        String[] parts = val.split(",");
        float[] arr = new float[parts.length];
        for (int i = 0; i < parts.length; i++) {
            arr[i] = Float.parseFloat(parts[i]);
        }
        return arr;
    }
}
