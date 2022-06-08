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

    public ReplyDTO remove(String commentId) {
        if(findOne(commentId)) {
            return replyRepository.remove2(commentId, "삭제된 댓글입니다.", 0 );
        }
        else
            return replyRepository.remove(commentId);
    }
    public boolean findOne(String commentId) {return replyRepository.findByCommentId(commentId);}
    public ReplyDTO edit(String commentId, String content) {
        return replyRepository.edit(commentId, content);
    }
}
