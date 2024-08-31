package com.spring.client.auth.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.spring.client.auth.repository.UserAuthRepository;
import com.spring.client.domain.Member;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {

    private final UserAuthRepository userAuthRepository;
    private final JavaMailSender mailSender;

    private final Map<String, VerificationCodeEntry> verificationCodes = new HashMap<>();
    

    @Override
    public void saveMember(Member member) {
        userAuthRepository.save(member);
    }

    @Override
    public boolean isIdAvailable(String memberId) {
        return !userAuthRepository.existsByMemberId(memberId);
    }

    @Override
    public Member userLogin(String memberId, String memberPassword) {
        Member member = userAuthRepository.findByMemberId(memberId);
        return (member != null && member.getMemberPassword().equals(memberPassword)) ? member : null;
    }

    @Override
    public void sendVerificationCode(String email) {
        String verificationCode = generateVerificationCode();
        System.out.println("Generated Code: " + verificationCode);

        try {
            sendEmail(email, "이메일 인증번호", "인증번호: " + verificationCode);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        verificationCodes.put(email, new VerificationCodeEntry(verificationCode, System.currentTimeMillis()));
        System.out.println("Stored Code for " + email + ": " + verificationCode);
    }

    @Override
    public boolean verifyCode(String email, String code) {
        VerificationCodeEntry entry = verificationCodes.get(email);
        if (entry == null) {
            return false;
        }

        long currentTime = System.currentTimeMillis();
        long timeElapsed = currentTime - entry.getTimestamp();
        if (timeElapsed > TimeUnit.MINUTES.toMillis(5)) {
            verificationCodes.remove(email);
            return false;
        }

        return entry.getCode().equals(code);
    }

    private void sendEmail(String to, String subject, String text) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("spacehubProj@gmail.com");  // 발송자 이메일 주소
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);

        mailSender.send(message);
    }


    private String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return Integer.toString(code);
    }

    private static class VerificationCodeEntry {
        private final String code;
        private final long timestamp;

        public VerificationCodeEntry(String code, long timestamp) {
            this.code = code;
            this.timestamp = timestamp;
        }

        public String getCode() {
            return code;
        }

        public long getTimestamp() {
            return timestamp;
        }
    }
}
