package plain.spring.comment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import plain.spring.art.domain.Art;
import plain.spring.art.repository.ArtRepository;
import plain.spring.comment.domain.Comment;
import plain.spring.comment.repository.CommentRepository;
import plain.spring.user.domain.User;
import plain.spring.user.repository.UserRepository;

import java.util.List;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ArtRepository artRepository;
    @Test
    void findAllByArtWithUser() {
        User user = User.builder()
                .nickname("민상")
                .oauthId("ssss")
                .build();
        userRepository.save(user);
        Art art = Art.builder()
                .artist(user)
                .build();
        artRepository.save(art);
        Comment comment = Comment.builder()
                .art(art)
                .user(user)
                .content("ccccc")
                .build();
        commentRepository.save(comment);
        List<Comment> commentList = commentRepository.findAllWithUserByArt(art);
        System.out.println(commentList.get(0).getContent());
    }
}