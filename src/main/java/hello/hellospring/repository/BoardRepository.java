package hello.hellospring.repository;

import hello.hellospring.domain.BoardDTO;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    BoardDTO save(BoardDTO boardDTO);

    Optional<BoardDTO> findById(Integer idx);

    Optional<BoardDTO> findByName(String title);

    List<BoardDTO> show();
}
