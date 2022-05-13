package hello.hellospring.controller;

import hello.hellospring.domain.BoardDTO;
import hello.hellospring.domain.Member;
import hello.hellospring.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BoardController {
    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("board/new")
    @ResponseBody
    public BoardDTO create(BoardForm form) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setTitle(form.getTitle());
        boardDTO.setContent(form.getContent());
        boardDTO.setWriter(form.getWriter());
        System.out.println("Title = " + boardDTO.getTitle());
        boardService.save(boardDTO);
        return boardDTO;
    }

    @GetMapping("/board/boardList")
    @ResponseBody
    public List<BoardDTO> list(Model model) {
        List<BoardDTO> boardDTO = boardService.show();
        model.addAttribute("boardDTO", boardDTO);
//        return "members/memberList";
        return boardDTO;
    }
}
