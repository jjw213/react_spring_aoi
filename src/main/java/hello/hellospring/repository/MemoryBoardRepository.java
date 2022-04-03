package hello.hellospring.repository;

import hello.hellospring.domain.BoardDTO;
import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryBoardRepository implements BoardRepository {

    private static Map<Integer, BoardDTO> store = new HashMap<>();
    private static Integer sequence = 0;

    @Override
    public BoardDTO save(BoardDTO boardDTO) {
        boardDTO.setIdx(++sequence);
        store.put(boardDTO.getIdx(), boardDTO);
        return boardDTO;
    }

    @Override
    public Optional<BoardDTO> findById(Integer idx) {
        return Optional.empty();
    }

    @Override
    public Optional<BoardDTO> findByName(String title) {
        return Optional.empty();
    }

    @Override
    public List<BoardDTO> show() {
        return null;
    }
}
