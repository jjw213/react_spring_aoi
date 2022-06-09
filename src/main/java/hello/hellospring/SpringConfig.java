package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.BoardService;
import hello.hellospring.service.DibsService;
import hello.hellospring.service.MemberService;
import hello.hellospring.service.ReplyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepsitory memberRepository() {
// return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }

    @Bean
    public BoardService boardService() {
        return new BoardService(boardRepository());
    }

    @Bean
    public BoardRepository boardRepository() {
        return new JdbcBoardRepository(dataSource);
    }

    @Bean
    public DibsService dibsService() {
        return new DibsService(dibsRepository());
    }

    @Bean
    public DibsRepository dibsRepository() {
        return new JdbcDibsRepository(dataSource);
    }

    @Bean
    public ReplyService replyService() {
        return new ReplyService(replyRepository());
    }

    @Bean
    public ReplyRepository replyRepository() {
        return new JdbcReplyRepository(dataSource);
    }

}