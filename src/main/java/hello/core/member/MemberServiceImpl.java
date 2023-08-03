package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired // ac.getBean(MemberRepository.class) 같이 동작
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        // 의존관계를 자동으로 할려면 @Autowired를 사용함.
    }
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
