package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import hello.hellospring.domain.ReplyDTO;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcReplyRepository implements ReplyRepository {
    private final DataSource dataSource;

    public JdbcReplyRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }

    @Override
    public ReplyDTO save(ReplyDTO replyDTO) {
        String sql = "insert into reply values(?,?,?,?,?,?,1)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, replyDTO.getContent());
            pstmt.setString(2, replyDTO.getWriter());
            pstmt.setLong(3, replyDTO.getPostId());
            pstmt.setString(4, replyDTO.getResponseTo());
            pstmt.setString(5, replyDTO.getCommentId());
            pstmt.setString(6, replyDTO.getDate());

            pstmt.executeUpdate();

            return replyDTO;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<ReplyDTO> show(Long postId) {
        String sql = "select * from reply where postId=? ";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, postId);
            rs = pstmt.executeQuery();
            List<ReplyDTO> replyDTOS = new ArrayList<>();
            while (rs.next()) {
                ReplyDTO replyDTO = new ReplyDTO();
                replyDTO.setContent(rs.getString("content"));
                replyDTO.setWriter(rs.getString("writer"));
                replyDTO.setPostId(rs.getLong("postId"));
                replyDTO.setResponseTo(rs.getString("responseTo"));
                replyDTO.setCommentId(rs.getString("commentId"));
                replyDTO.setDate(rs.getString("created_at"));
                replyDTO.setExist(rs.getInt("exist"));
                replyDTOS.add(replyDTO);
            }
            return replyDTOS;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public ReplyDTO remove(String commentId) {
        String sql = "delete from reply where commentId = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, commentId);
            pstmt.executeUpdate();
            ReplyDTO replyDTO= new ReplyDTO();
            replyDTO = null;
            return replyDTO;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public ReplyDTO edit(String commentId, String content) {
        String sql = "update reply set content=? where commentId = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, content);
            pstmt.setString(2, commentId);

            pstmt.executeUpdate();
            ReplyDTO replyDTO = new ReplyDTO();
            replyDTO.setContent(content);
            replyDTO.setCommentId(commentId);
            return replyDTO;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public boolean findByCommentId(String commentId) {
        String sql = "select * from reply where responseTo = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, commentId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public ReplyDTO remove2(String commentId, String content, int exist) {
        String sql = "update reply set content=?, exist=?  where commentId = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, content);
            pstmt.setInt(2, exist);
            pstmt.setString(3, commentId);

            pstmt.executeUpdate();
            ReplyDTO replyDTO = new ReplyDTO();
            replyDTO.setContent(content);
            replyDTO.setExist(exist);
            replyDTO.setCommentId(commentId);
            return replyDTO;
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
}
