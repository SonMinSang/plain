package plain.spring.likes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import plain.spring.art.domain.Art;
import plain.spring.art.repository.ArtRepository;
import plain.spring.commons.config.QueryDslConfig;
import plain.spring.likes.domain.Likes;
import plain.spring.likes.repository.LikesRepository;
import plain.spring.user.domain.User;
import plain.spring.user.repository.UserRepository;

import java.util.List;

@DataJpaTest
@ImportAutoConfiguration(QueryDslConfig.class)
class LikesRepositoryTest {

    @Autowired
    private LikesRepository likesRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ArtRepository artRepository;
    @Test
    void findAllArtByUserId() {
        User user = User.builder()
                .nickname("민상")
                .oauthId("ssss")
                .build();
        userRepository.save(user);
        Art art1 = Art.builder()
                .artist(user)
                .name("art1")
                .build();
        Art art2 = Art.builder()
                .artist(user)
                .name("art2")
                .build();
        Art art3 = Art.builder()
                .artist(user)
                .name("art3")
                .build();
        artRepository.save(art1);
        artRepository.save(art2);
        artRepository.save(art3);
        Likes likes1 = Likes.builder()
                .art(art1)
                .user(user)
                .build();
        Likes likes2 = Likes.builder()
                .art(art2)
                .user(user)
                .build();
        Likes likes3 = Likes.builder()
                .art(art3)
                .user(user)
                .build();
        likesRepository.save(likes1);
        likesRepository.save(likes2);
        likesRepository.save(likes3);

        List<Likes> likesList = likesRepository.findLikesWithArtByUserId(user.getId());
        for (Likes l : likesList){
            System.out.println(l.getArt().getName());
        }
    }

    @Test
    void findLikesWithArtByUserId() {
    }
}