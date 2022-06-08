package hello.hellospring.repository;

import hello.hellospring.domain.Board2DTO;
import hello.hellospring.domain.BoardDTO;
import hello.hellospring.domain.Member;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcBoardRepository implements BoardRepository {
    private final DataSource dataSource;

    public JdbcBoardRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public BoardDTO save(BoardDTO boardDTO) {
        String sql = "insert into board2 values(board2_seq.nextval,?,?,sysdate,0,?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, boardDTO.getTitle());
            pstmt.setString(2, boardDTO.getContent());
            pstmt.setString(3, boardDTO.getWriter());
            pstmt.executeUpdate();

            return boardDTO;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<BoardDTO> findById(Integer idx) {
        String sql = "select * from board where id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, idx);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                BoardDTO boardDTO = new BoardDTO();
                boardDTO.setIdx(rs.getInt("idx"));
                boardDTO.setTitle(rs.getString("title"));
                boardDTO.setContent(rs.getString("content"));
                return Optional.of(boardDTO);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<BoardDTO> show() {
        String sql = "select * from board";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<BoardDTO> boardDTOs = new ArrayList<>();
            while (rs.next()) {
                BoardDTO boardDTO = new BoardDTO();
                boardDTO.setIdx(rs.getInt("idx"));
                boardDTO.setTitle(rs.getString("title"));
                boardDTO.setContent(rs.getString("content"));
                boardDTO.setWriter(rs.getString("writer"));
                boardDTOs.add(boardDTO);
            }
            return boardDTOs;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<Board2DTO> show2() {
        String sql = "select * from board2 order by no desc";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<Board2DTO> board2DTOs = new ArrayList<>();
            while (rs.next()) {
                Board2DTO board2DTO = new Board2DTO();
                board2DTO.setNo(rs.getInt("no"));
                board2DTO.setTitle(rs.getString("title"));
                board2DTO.setContent(rs.getString("content"));
                board2DTO.setCreateDate(rs.getDate("createDate"));
                board2DTO.setReadCount(rs.getInt("readCount"));
                board2DTO.setWriter(rs.getString("writer"));
                board2DTOs.add(board2DTO);
            }
            return board2DTOs;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Board2DTO findByNo(Integer no) {
        String sql = "select * from board2 where no = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, no);
            rs = pstmt.executeQuery();
            // 조회수 증가 부분
            sql=" UPDATE BOARD2 set readCount = NVL(readCount, 0) + 1 where no=?";
            pstmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, no);
            pstmt.executeUpdate();
            if (rs.next()) {
                Board2DTO board2DTO = new Board2DTO();
                board2DTO.setNo(rs.getInt("no"));
                board2DTO.setTitle(rs.getString("title"));
                board2DTO.setContent(rs.getString("content"));
                board2DTO.setCreateDate(rs.getDate("createDate"));
                board2DTO.setReadCount(rs.getInt("readCount"));
                board2DTO.setWriter(rs.getString("writer"));
                return board2DTO;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<BoardDTO> findByName(String title) {
        String sql = "select * from board where title = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                BoardDTO boardDTO = new BoardDTO();
                boardDTO.setIdx(rs.getInt("idx"));
                boardDTO.setTitle(rs.getString("title"));
                boardDTO.setContent(rs.getString("content"));
                return Optional.of(boardDTO);
            }
            return Optional.empty();
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
