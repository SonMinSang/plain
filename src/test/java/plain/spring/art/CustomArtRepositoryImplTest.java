package plain.spring.art;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import plain.spring.art.domain.Art;
import plain.spring.art.dto.ArtWithLikes;
import plain.spring.art.repository.ArtRepository;
import plain.spring.commons.config.QueryDslConfig;
import plain.spring.likes.domain.Likes;
import plain.spring.likes.repository.LikesRepository;
import plain.spring.user.domain.User;
import plain.spring.user.repository.UserRepository;

import java.util.List;

@DataJpaTest
@ImportAutoConfiguration(QueryDslConfig.class)
class CustomArtRepositoryImplTest {

    @Autowired
    LikesRepository likesRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ArtRepository artRepository;

    @Test
    void findAllArtsByCategory() {
    }

    @Test
    void findAllArtsByCategoryWithUser() {
        User user1 = User.builder()
                .oauthId("dddddddddd")
                .nickname("dddddddddd")
                .email("dddddddd")
                .build();
        userRepository.save(user1);
        Art art1 = Art.builder()
                .artist(user1)
                .name("dddd")
                .category("공예")
                .build();
        Art art2 = Art.builder()
                .artist(user1)
                .name("ccccc")
                .category("공예")
                .build();
        Art art3 = Art.builder()
                .artist(user1)
                .name("aaaaa")
                .category("공예")
                .build();
        artRepository.save(art1);
        artRepository.save(art2);
        artRepository.save(art3);

        Likes likes = Likes.builder()
                .user(user1)
                .art(art2)
                .build();
        likesRepository.save(likes);
        List<ArtWithLikes> arts = artRepository.findAllArtsByCategoryWithLikes(null, null, user1.getId());
        for (ArtWithLikes a : arts){
            System.out.println("art size : " + a.isLikes());
        }

    }
}