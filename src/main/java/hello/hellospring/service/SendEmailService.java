package hello.hellospring.service;

import hello.hellospring.domain.MailDTO;
import hello.hellospring.repository.MemberRepsitory;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@AllArgsConstructor
public class SendEmailService {

    private final MemberRepsitory memberRepsitory;
    public SendEmailService(MemberRepsitory memberRepsitory) {this.memberRepsitory = memberRepsitory;}
    @Autowired
    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "ssvsxx@gmail.com";


    public MailDTO createMailAndChangePassword(String userEmail, String userName) {
        String str = getTempPassword();
        MailDTO dto = new MailDTO();
        dto.setAddress(userEmail);
        dto.setTitle(userName + "님의 AnimalService 인증 번호 안내 이메일 입니다.");
        dto.setMessage("안녕하세요. AnimalService 인증 번호 안내 관련 이메일 입니다." + "[" + userName + "]" + "님의 인증 번호는 "
                + str + " 입니다. \n 해당 인증 번호를 홈페이지 > 마이페이지 > 인증 번호 버튼을 누르면 뜨는 입력창에 입력해주세요.");
        saveCode(str, userName);
//        updatePassword(str,userEmail);
        return dto;
    }
    public void saveCode(String str, String userName){
        memberRepsitory.saveCode(str, userName);
    }
//    public void updatePassword(String str,String userEmail){
//        String pw = EncryptionUtils.encryptMD5(str);
//        int id = userRepository.findUserByUserId(userEmail).getId();
//        userRepository.updateUserPassword(id,pw);
//    }


    public String getTempPassword() {
        char[] charSet = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }

    public void mailSend(MailDTO mailDto) {
        System.out.println("이멜 전송 완료!");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getAddress());
        message.setFrom(SendEmailService.FROM_ADDRESS);
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());

        mailSender.send(message);
    }
}
