package plain.spring.exhibitionlikes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import plain.spring.commons.util.SecurityUtil;
import plain.spring.exhibition.domain.Exhibition;
import plain.spring.exhibition.repository.ExhibitionRepository;
import plain.spring.exhibitionlikes.domain.ExhibitionLikes;
import plain.spring.exhibitionlikes.repository.ExhibitionLikesRepository;
import plain.spring.user.domain.User;
import plain.spring.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class ExhibitionLikesService {

    private final ExhibitionLikesRepository exhibitionLikesRepository;
    private final UserRepository userRepository;
    private final ExhibitionRepository exhibitionRepository;

    public void likes(Long exhibitionId){
        Long userId = Long.valueOf(SecurityUtil.getId().orElseThrow());
        User user = userRepository.findById(userId).orElseThrow();
        Exhibition exhibition = exhibitionRepository.findById(exhibitionId).orElse(null);
        ExhibitionLikes likes = exhibitionLikesRepository.findLikesByUserAndExhibition(user, exhibition).orElse(null);

        if (likes == null){
            exhibitionLikesRepository.save(ExhibitionLikes.builder()
                    .user(user)
                    .exhibition(exhibition)
                    .build());
            exhibition.likes();
        }
    }

    public void unlikes(Long exhibitionId){
        Long userId = Long.valueOf(SecurityUtil.getId().orElseThrow());
        User user = userRepository.findById(userId).orElseThrow();
        Exhibition exhibition = exhibitionRepository.findById(exhibitionId).orElseThrow();
        ExhibitionLikes likes = exhibitionLikesRepository.findLikesByUserAndExhibition(user, exhibition).orElse(null);
        if (likes != null){
            exhibitionLikesRepository.delete(likes);
            exhibition.unlikes();
        }
    }
}

