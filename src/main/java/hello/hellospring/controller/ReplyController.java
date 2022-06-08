package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.domain.ReplyDTO;
import hello.hellospring.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ReplyController {
    private ReplyService replyService;

    @Autowired
    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping("/reply/new")
    @ResponseBody
    public ReplyDTO create(ReplyDTO replyDTO) {
        replyService.save(replyDTO);
        return replyDTO;
    }

    @PostMapping("/reply/replyList")
    @ResponseBody
    public List<ReplyDTO> list(ReplyDTO replyForm) {
        ReplyDTO replyDTO = new ReplyDTO();
        replyDTO.setPostId(replyForm.getPostId());
        List<ReplyDTO> list = replyService.show(replyDTO.getPostId());
//        return "members/memberList";
        return list;
    }

    @PostMapping("/reply/remove")
    @ResponseBody
    public ReplyDTO remove(ReplyDTO replyForm){
        return replyService.remove(replyForm.getCommentId());
    }

    @PostMapping("/reply/edit")
    @ResponseBody
    public ReplyDTO edit(ReplyDTO replyForm){
        return replyService.edit(replyForm.getCommentId(), replyForm.getContent());
    }
}
