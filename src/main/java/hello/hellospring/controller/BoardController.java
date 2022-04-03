package hello.hellospring.controller;

import hello.hellospring.domain.BoardDTO;
import hello.hellospring.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
