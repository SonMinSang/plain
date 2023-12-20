package plain.spring;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import plain.spring.ExhibitionArtImage.domain.ExhibitionArtImage;
import plain.spring.ExhibitionArtImage.repository.ExhibitionArtImageRepository;
import plain.spring.art.domain.Art;
import plain.spring.art.repository.ArtRepository;
import plain.spring.arttag.domain.ArtTag;
import plain.spring.arttag.repository.ArtTagRepository;
import plain.spring.comment.domain.Comment;
import plain.spring.comment.repository.CommentRepository;
import plain.spring.exhibition.domain.Exhibition;
import plain.spring.exhibition.repository.ExhibitionRepository;
import plain.spring.exhibitionart.domain.ExhibitionArt;
import plain.spring.exhibitionart.repository.ExhibitionArtRepository;
import plain.spring.exhibitionartist.domain.ExhibitionArtist;
import plain.spring.exhibitionartist.repository.ExhibitionArtistRepository;
import plain.spring.exhibitionartthumbnailimage.domain.ExhibitionArtThumbnailImage;
import plain.spring.exhibitionartthumbnailimage.repository.ExhibitionArtThumbnailImageRepository;
import plain.spring.image.domain.ArtImage;
import plain.spring.image.repository.ArtImageRepository;
import plain.spring.job.domain.Job;
import plain.spring.job.repository.JobRepository;
import plain.spring.notification.domain.Notification;
import plain.spring.notification.domain.NotificationType;
import plain.spring.notification.repository.NotificationRepository;
import plain.spring.tag.domain.Tag;
import plain.spring.tag.repository.TagRepository;
import plain.spring.thumbnailimage.domain.ThumbnailImage;
import plain.spring.thumbnailimage.repository.ThumbnailImageRepository;
import plain.spring.user.domain.User;
import plain.spring.user.repository.UserRepository;
import plain.spring.userjob.domain.UserJob;
import plain.spring.userjob.repository.UserJobRepository;
import plain.spring.usertag.domain.UserTag;
import plain.spring.usertag.repository.UserTagRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.tag();
        initService.userInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final UserRepository userRepository;
        private final UserTagRepository userTagRepository;
        private final JobRepository jobRepository;
        private final UserJobRepository userJobRepository;
        private final TagRepository tagRepository;
        private final ArtRepository artRepository;
        private final ArtTagRepository artTagRepository;
        private final ArtImageRepository artImageRepository;
        private final CommentRepository commentRepository;
        private final ThumbnailImageRepository thumbnailImageRepository;

        private final ExhibitionRepository exhibitionRepository;
        private final ExhibitionArtRepository exhibitionArtRepository;
        private final ExhibitionArtThumbnailImageRepository exhibitionArtThumbnailImageRepository;
        private final ExhibitionArtistRepository exhibitionArtistRepository;
        private final ExhibitionArtImageRepository exhibitionArtImageRepository;
        private final NotificationRepository notificationRepository;
        public void tag(){
            String[] tags = {"제품", "공예", "그래픽", "회화", "UX", "UI", "모던", "클래식", "오브제", "감성적인", "심플", "아담한", "귀여운", "키치한", "유니크", "힙한", "실용적인", "부드러운", "레트로", "몽환적인", "고급진", "트렌디", "빈티지", "엔틱", "화려한", "재활용", "친환경", "지속가능한", "업사이클링", "밝은", "어두운", "차가운", "따뜻한", "감성", "메탈", "도자기", "빈티지스러운", "아기자기한"};
            for (String tag: tags){
                Tag tag1 = Tag.builder()
                        .name(tag)
                        .build();
                tagRepository.save(tag1);
            }
            String[] jobs = {"제품 디자이너", "시각 디자이너", "UX 디자이너", "패션 디자이너", "3D 아티스트", "공예가", "화가", "일러스트레이터", "크리에이터"};
            for (String job: jobs){
                Job job1 = Job.builder()
                        .name(job)
                        .build();
                jobRepository.save(job1);
            }

        }
        public void userInit1(){
            User user1 = User.builder()
                    .nickname("방태림")
                    .email("sms7624@gmail.com")
                    .followedCount(10)
                    .profileImgUrl("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/%EB%B0%A9%ED%83%9C%EB%A6%BC_%ED%94%84%EB%A1%9C%ED%95%84.png")
                    .backgroundImgUrl("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/%EB%B0%A9%ED%83%9C%EB%A6%BC_%EB%B0%B0%EA%B2%BD.png")
                    .build();
            userRepository.save(user1);
            User user2 = User.builder()
                    .nickname("김민지")
                    .email("sms7624@gmail.com")
                    .followedCount(9)
                    .profileImgUrl("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/%EA%B9%80%EB%AF%BC%EC%A7%80_%ED%94%84%EB%A1%9C%ED%95%84.png")
                    .backgroundImgUrl("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/%EA%B9%80%EB%AF%BC%EC%A7%80_%EB%B0%B0%EA%B2%BD.png")
                    .build();
            userRepository.save(user2);
            Tag tag1 = tagRepository.findByName("감성").orElse(null);
            Tag tag2 = tagRepository.findByName("힙한").orElse(null);
            Tag tag3 = tagRepository.findByName("레트로").orElse(null);
            Tag tag4 = tagRepository.findByName("모던").orElse(null);
            Tag tag5 = tagRepository.findByName("클래식").orElse(null);
            Tag tag6 = tagRepository.findByName("유니크").orElse(null);
            Tag tag7 = tagRepository.findByName("메탈").orElse(null);
            Tag tag8 = tagRepository.findByName("도자기").orElse(null);
            Tag tag9 = tagRepository.findByName("재활용").orElse(null);
            Tag tag10 = tagRepository.findByName("빈티지스러운").orElse(null);
            Tag tag11 = tagRepository.findByName("아기자기한").orElse(null);
            UserTag userTag1 = UserTag.builder()
                    .tag(tag1)
                    .user(user1)
                    .build();
            UserTag userTag2 = UserTag.builder()
                    .tag(tag2)
                    .user(user1)
                    .build();
            UserTag userTag3 = UserTag.builder()
                    .tag(tag3)
                    .user(user1)
                    .build();
            UserTag userTag4 = UserTag.builder()
                    .tag(tag4)
                    .user(user2)
                    .build();
            UserTag userTag5 = UserTag.builder()
                    .tag(tag5)
                    .user(user2)
                    .build();
            UserTag userTag6 = UserTag.builder()
                    .tag(tag6)
                    .user(user2)
                    .build();

            Job job = jobRepository.findByName("제품 디자이너").orElse(null);
            Job job1 = jobRepository.findByName("패션 디자이너").orElse(null);
            UserJob userJob = UserJob.builder()
                    .job(job)
                    .user(user1)
                    .build();
            UserJob userJob1 = UserJob.builder()
                    .job(job1)
                    .user(user2)
                    .build();
            userJobRepository.save(userJob);
            userJobRepository.save(userJob1);
            userTagRepository.save(userTag1);
            userTagRepository.save(userTag2);
            userTagRepository.save(userTag3);
            userTagRepository.save(userTag4);
            userTagRepository.save(userTag5);
            userTagRepository.save(userTag6);
            Art art1 = Art.builder()
                    .name("Filling Cabinet")
                    .artist(user1)
                    .likesCount(0)
                    .category("제품")
                    .price(100000)
                    .description("판재의 특징을 살린 오브젝트 디자인’ 프로제트에 맞춰 진행한 사무용품 카비넷’입니다. 효율성과 실용성에 치중하여 다소 복잡한 외관을 지닌 서류정리함을 심플한 형태를 가진 판재를 이용하여 단순하고 간결하게 리디자인하였습니다.")
                    .build();
            Art art2 = Art.builder()
                    .name("몰입북")
                    .artist(user1)
                    .likesCount(9)
                    .category("제품")
                    .price(1000000)
                    .description("몰입북")
                    .build();
            artRepository.save(art1);
            artRepository.save(art2);
            ArtTag artTag1 = ArtTag.builder()
                    .art(art1)
                    .tag(tag7)
                    .build();
            ArtTag artTag2 = ArtTag.builder()
                    .art(art1)
                    .tag(tag8)
                    .build();
            ArtTag artTag3 = ArtTag.builder()
                    .art(art1)
                    .tag(tag9)
                    .build();
            ArtTag artTag4 = ArtTag.builder()
                    .art(art1)
                    .tag(tag10)
                    .build();
            ArtTag artTag5 = ArtTag.builder()
                    .art(art1)
                    .tag(tag11)
                    .build();
            ArtTag artTag6 = ArtTag.builder()
                    .art(art2)
                    .tag(tag7)
                    .build();
            artTagRepository.save(artTag1);
            artTagRepository.save(artTag2);
            artTagRepository.save(artTag3);
            artTagRepository.save(artTag4);
            artTagRepository.save(artTag5);
            artTagRepository.save(artTag6);
            ThumbnailImage thumbnailImage1 = ThumbnailImage.builder()
                    .art(art1)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/filling_cabinet_thumbnail_1.png")
                    .build();
            ThumbnailImage thumbnailImage2 = ThumbnailImage.builder()
                    .art(art1)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/filling_cabinet_thumbnail_2.png")
                    .build();
            ThumbnailImage thumbnailImage3 = ThumbnailImage.builder()
                    .art(art2)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/%EB%AA%B0%EC%9E%85%EB%B6%81.png")
                    .build();
            thumbnailImageRepository.save(thumbnailImage1);
            thumbnailImageRepository.save(thumbnailImage2);
            thumbnailImageRepository.save(thumbnailImage3);
            ArtImage artImage1 = ArtImage.builder()
                    .art(art1)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/filling_cabinet_detail_1.png")
                    .width(390)
                    .height(174)
                    .build();
            ArtImage artImage2 = ArtImage.builder()
                    .art(art1)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/filling_cabinet_detail_2.png")
                    .width(390)
                    .height(268)
                    .build();
            ArtImage artImage3 = ArtImage.builder()
                    .art(art2)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/%EB%AA%B0%EC%9E%85%EB%B6%81.png")
                    .width(165)
                    .height(165)
                    .build();
            artImageRepository.save(artImage1);
            artImageRepository.save(artImage2);
            artImageRepository.save(artImage3);
            Comment comment1 = Comment.builder()
                    .art(art1)
                    .user(user1)
                    .content("안녕하세요1")
                    .build();
            Comment comment2 = Comment.builder()
                    .art(art2)
                    .user(user1)
                    .content("안녕하세요3")
                    .build();
            Comment comment3 = Comment.builder()
                    .art(art1)
                    .user(user2)
                    .content("안녕하세요2")
                    .build();
            Comment comment4 = Comment.builder()
                    .art(art2)
                    .user(user2)
                    .content("안녕하세요4")
                    .build();
            commentRepository.save(comment1);
            commentRepository.save(comment2);
            commentRepository.save(comment3);
            commentRepository.save(comment4);
            Exhibition exhibition1 = Exhibition.builder()
                    .posterImageUrl("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/filling_cabinet_detail_1.png")
                    .name("서울과학기술대학교 산업디자인학과 졸업 전시회")
                    .englishName("Seoultech. Univ. Industrial Exhibition")
                    .category("공예")
                    .description("본 전시는 지난 120년동안 한국의 기대수명에 대한 '데이터'를 '디자인'과 접목하여 긍정적인 사회적 변화상을 시각적으로 표현한 공공디자인 전시입니다.공공디자인 전시입니다.공공디자인 전시입니다.공공디자인 전시입니다.공공디자인 전시입니다.")
                    .status("available")
                    .artCount("10점")
                    .estimatedDuration("10분")
                    .build();
            Exhibition exhibition2 = Exhibition.builder()
                    .posterImageUrl("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/filling_cabinet_thumbnail_2.png")
                    .name("전시회2")
                    .englishName("Exhibition2")
                    .category("제품")
                    .status("available")
                    .build();
            exhibitionRepository.save(exhibition1);
            exhibitionRepository.save(exhibition2);
            ExhibitionArt exhibitionArt1 = ExhibitionArt.builder()
                    .exhibition(exhibition1)
                    .artist(user1)
                    .build();
            ExhibitionArt exhibitionArt2 = ExhibitionArt.builder()
                    .exhibition(exhibition1)
                    .artist(user2)
                    .build();
            ExhibitionArt exhibitionArt3 = ExhibitionArt.builder()
                    .exhibition(exhibition2)
                    .artist(user2)
                    .build();

            exhibitionArtRepository.save(exhibitionArt1);
            exhibitionArtRepository.save(exhibitionArt2);
            exhibitionArtRepository.save(exhibitionArt3);
            ExhibitionArtThumbnailImage exhibitionArtThumbnailImage1 = ExhibitionArtThumbnailImage.builder()
                    .exhibitionArt(exhibitionArt1)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/filling_cabinet_thumbnail_1.png")
                    .build();
            ExhibitionArtThumbnailImage exhibitionArtThumbnailImage2 = ExhibitionArtThumbnailImage.builder()
                    .exhibitionArt(exhibitionArt1)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/filling_cabinet_thumbnail_2.png")
                    .build();

            ExhibitionArtThumbnailImage exhibitionArtThumbnailImage3 = ExhibitionArtThumbnailImage.builder()
                    .exhibitionArt(exhibitionArt1)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/%EB%AA%B0%EC%9E%85%EB%B6%81.png")
                    .build();
            exhibitionArtThumbnailImageRepository.save(exhibitionArtThumbnailImage1);
            exhibitionArtThumbnailImageRepository.save(exhibitionArtThumbnailImage2);
            exhibitionArtThumbnailImageRepository.save(exhibitionArtThumbnailImage3);
            ExhibitionArtist exhibitionArtist1 = ExhibitionArtist.builder()
                    .exhibition(exhibition1)
                    .user(user1)
                    .build();
            ExhibitionArtist exhibitionArtist2 = ExhibitionArtist.builder()
                    .exhibition(exhibition1)
                    .user(user2)
                    .build();
            exhibitionArtistRepository.save(exhibitionArtist1);
            exhibitionArtistRepository.save(exhibitionArtist2);

            ExhibitionArtImage exhibitionArtImage1 = ExhibitionArtImage.builder()
                    .exhibitionArt(exhibitionArt1)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/filling_cabinet_detail_1.png")
                    .width(390)
                    .height(174)
                    .build();
            ExhibitionArtImage exhibitionArtImage2 = ExhibitionArtImage.builder()
                    .exhibitionArt(exhibitionArt1)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/filling_cabinet_detail_2.png")
                    .width(390)
                    .height(268)
                    .build();
            ExhibitionArtImage exhibitionArtImage3 = ExhibitionArtImage.builder()
                    .exhibitionArt(exhibitionArt1)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/%EB%AA%B0%EC%9E%85%EB%B6%81.png")
                    .width(165)
                    .height(165)
                    .build();
            exhibitionArtImageRepository.save(exhibitionArtImage1);
            exhibitionArtImageRepository.save(exhibitionArtImage2);
            exhibitionArtImageRepository.save(exhibitionArtImage3);
            for (int i = 0; i < 20; i++) {
                Notification notification = Notification.builder()
                        .receiverId(1L)
                        .sender(user2)
                        .type(NotificationType.FOLLOW)
                        .build();
                Notification notification1 = Notification.builder()
                        .receiverId(1L)
                        .art(art1)
                        .sender(user2)
                        .type(NotificationType.LIKES)
                        .build();
                Notification notification2 = Notification.builder()
                        .receiverId(1L)
                        .art(art1)
                        .sender(user2)
                        .type(NotificationType.COMMENT)
                        .build();

                notificationRepository.save(notification);
                notificationRepository.save(notification1);
                notificationRepository.save(notification2);

            }

        }

    }
}
