package hello.hellospring.repository;

import hello.hellospring.domain.ReplyDTO;

import java.util.List;

public interface ReplyRepository {
    ReplyDTO save(ReplyDTO replyDTO);

    List<ReplyDTO> show(Long postId);
}
