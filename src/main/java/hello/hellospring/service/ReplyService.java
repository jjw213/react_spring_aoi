package hello.hellospring.service;

import hello.hellospring.domain.ReplyDTO;
import hello.hellospring.repository.ReplyRepository;

import java.util.List;

public class ReplyService {

    private final ReplyRepository replyRepository;

    public ReplyService(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    public String save(ReplyDTO replyDTO) {
        replyRepository.save(replyDTO);

        return replyDTO.getCommentId();
    }
    public List<ReplyDTO> show(Long postId) {
        return replyRepository.show(postId);
    }
}
