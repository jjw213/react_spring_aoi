package hello.hellospring.repository;

import hello.hellospring.domain.Animal;
import hello.hellospring.domain.Member;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDibsRepository implements DibsRepository {
    private final DataSource dataSource;

    public JdbcDibsRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Animal save(Animal animal) {
        String sql = "insert into dibs values(?,?,?,?,?,?,?,?,?,?,?,?,seq_dibs.nextval)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, animal.getAge());
            pstmt.setString(2, animal.getCareAddr());
            pstmt.setString(3, animal.getCareNm());
            pstmt.setString(4, animal.getCareTel());
            pstmt.setString(5, animal.getKindCd());
            pstmt.setDouble(6,animal.getDesertionNo());
            pstmt.setString(7, animal.getPopfile());
            pstmt.setString(8, animal.getSexCd());
            pstmt.setString(9, animal.getProcessState());
            pstmt.setString(10, animal.getSpecialMark());
            pstmt.setString(11, animal.getWeight());
            pstmt.setString(12, animal.getName());
            pstmt.executeUpdate();
//            rs = pstmt.getGeneratedKeys();
//            if (rs.next()) {
////                System.out.println(rs.getString(1));
////                System.out.println(rs.getString(2));
//                System.out.println(member.getId());
////                System.out.println(rs.getInt(2));
//                member.setId(rs.getInt(1));
//            } else {
//                throw new SQLException("id 조회 실패");
//            }
            return animal;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<Animal> findAllByName(String name) {
        String sql = "select * from dibs where name=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            List<Animal> animals = new ArrayList<>();
            while (rs.next()) {
                Animal animal = new Animal();
                animal.setAge(rs.getString("age"));
                animal.setCareAddr(rs.getString("careAddr"));
                animal.setCareNm(rs.getString("careNm"));
                animal.setCareTel(rs.getString("careTel"));
                animal.setKindCd(rs.getString("kindCd"));
                animal.setDesertionNo(rs.getLong("desertionNo"));
                animal.setPopfile(rs.getString("popfile"));
                animal.setSexCd(rs.getString("secCd"));
                animal.setProcessState(rs.getString("processState"));
                animal.setSpecialMark(rs.getString("specialMark"));
                animal.setWeight(rs.getString("weight"));

                animals.add(animal);
            }
            return animals;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
}
