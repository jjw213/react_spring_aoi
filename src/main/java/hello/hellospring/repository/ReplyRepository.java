package hello.hellospring.repository;

import hello.hellospring.domain.ReplyDTO;

import java.util.List;

public interface ReplyRepository {
    ReplyDTO save(ReplyDTO replyDTO);

    List<ReplyDTO> show(Long postId);

    ReplyDTO remove(String commentId);

    ReplyDTO edit(String commentId, String content);

    boolean findByCommentId(String commentId);

    ReplyDTO remove2(String commentId, String s, int i);
}
