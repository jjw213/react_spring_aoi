package hello.hellospring.domain;


import java.sql.Date;

public class ReplyDTO {
    private String content;
    private String writer;
    private Long postId;
    private String responseTo;
    private String commentId;
    private String date;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getResponseTo() {
        return responseTo;
    }

    public void setResponseTo(String responseTo) {
        this.responseTo = responseTo;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "ReplyDTO{" +
                "date=" + date +
                '}';
    }

    public void setDate(String date) {
        this.date = date;
    }
}
