package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepsitory {
    private static Map<Integer, Member> store = new HashMap<>();
    private static Integer sequence = 0;


    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public void saveCode(String str, String userName) {

    }

    @Override
    public Optional<Member> findById(Integer id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String memberName) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByEmail(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public Optional<Member> findByKakao(long name) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public boolean deleteByName(String memberName) {
        return true;
    }

    @Override
    public boolean update(String member) {
        return true;
    }

}
