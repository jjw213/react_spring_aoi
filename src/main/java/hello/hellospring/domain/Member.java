package hello.hellospring.domain;

public class Member {
    private int id;
    private String name;
    private String password;
    private long kakao_id;

    public long getKakao_id() {
        return kakao_id;
    }

    public void setKakao_id(long kakao_id) {
        this.kakao_id = kakao_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
