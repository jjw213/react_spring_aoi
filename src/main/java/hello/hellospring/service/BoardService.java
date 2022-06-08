package hello.hellospring.service;

import hello.hellospring.controller.Board2Form;
import hello.hellospring.domain.Board2DTO;
import hello.hellospring.domain.BoardDTO;
import hello.hellospring.repository.BoardRepository;

import java.util.List;
import java.util.Optional;

public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Integer save(BoardDTO boardDTO) {
        boardRepository.save(boardDTO);

        return boardDTO.getIdx();
    }

    public List<BoardDTO> show() {
        return boardRepository.show();
    }
    public List<Board2DTO> show2(Board2Form form) {
        return boardRepository.show2(form);
    }
    public Board2DTO findOne2(Integer no) {
        return boardRepository.findByNo(no);
    }
    public Optional<BoardDTO> findOne(Integer memberId) {
        return boardRepository.findById(memberId);
    }

    public Optional<BoardDTO> findOne(String memberName) {
        return boardRepository.findByName(memberName);
    }
}
