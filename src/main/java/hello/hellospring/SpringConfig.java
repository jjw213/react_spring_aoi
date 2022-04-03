package hello.hellospring;
import hello.hellospring.repository.BoardRepository;
import hello.hellospring.repository.JdbcBoardRepository;
import hello.hellospring.repository.JdbcMemberRepository;
//import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberRepsitory;
//import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.BoardService;
import hello.hellospring.service.MemberService;
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
    public BoardService boardService(){ return new BoardService(boardRepository());}

    @Bean
    public BoardRepository boardRepository(){
        return new JdbcBoardRepository(dataSource);
    }
}