package hello.hellospring.controller;

import hello.hellospring.domain.ReplyDTO;
import hello.hellospring.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
}
