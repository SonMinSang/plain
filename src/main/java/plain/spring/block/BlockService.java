package plain.spring.block;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import plain.spring.commons.exception.CustomException;
import plain.spring.commons.exception.ErrorCode;
import plain.spring.commons.util.SecurityUtil;
import plain.spring.follow.domain.Follow;
import plain.spring.follow.repository.FollowRepository;
import plain.spring.user.domain.User;
import plain.spring.user.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlockService {

    private final BlockRepository blockRepository;
    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    public void block(Long userId){
        String id = SecurityUtil.getId().orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        User blocker = userRepository.findById(Long.parseLong(id)).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        User blocked = userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        Block block = Block.builder()
                .blocker(blocker)
                .blocked(blocked)
                .build();
        blockRepository.save(block);
        Follow follow1 = followRepository.findByFollowerAndFollowing(blocker.getId(), blocked.getId()).orElse(null);
        Follow follow2 = followRepository.findByFollowerAndFollowing(blocked.getId(), blocker.getId()).orElse(null);
        if (follow1 != null){
            followRepository.delete(follow1);
        }
        if (follow2 != null){
            followRepository.delete(follow2);
        }
    }

    public void unblock(Long userId) {
        String id = SecurityUtil.getId().orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        User blocker = userRepository.findById(Long.parseLong(id)).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        User blocked = userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }
}

