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
import plain.spring.usersetting.UserSetting;
import plain.spring.usersetting.UserSettingRepository;
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
//        initService.tag();
//        initService.userInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final UserRepository userRepository;
        private final UserSettingRepository userSettingRepository;
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
            UserSetting userSetting1 = new UserSetting();
            userSetting1.setBlockRequest(false);
            UserSetting userSetting2 = new UserSetting();
            userSetting2.setBlockRequest(false);
            UserSetting userSetting3 = new UserSetting();
            userSetting3.setBlockRequest(false);

            userSettingRepository.save(userSetting1);
            userSettingRepository.save(userSetting2);
            userSettingRepository.save(userSetting3);

            User user1 = User.builder()
                    .nickname("방태림")
                    .email("sms7624@gmail.com")
                    .followedCount(10)
                    .userSetting(userSetting1)
                    .deviceToken("eMGjgF8-WURNkqIVeIlHgb:APA91bGbkGlfIL4iw8vqOoZKdXCp8GYdLxQlpT_8l3Ho-_cMvg9FSVuSTT8NkJYEdJod3LBH9SqTuPxhWtXVS7MXDwQwawThhyf6TleBDF28SkIsgW5jd00mXiFfkEqooB2TAgyStP7v")
                    .profileImageUrl("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/%EB%B0%A9%ED%83%9C%EB%A6%BC_%ED%94%84%EB%A1%9C%ED%95%84.png")
                    .backgroundImageUrl("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/%EB%B0%A9%ED%83%9C%EB%A6%BC_%EB%B0%B0%EA%B2%BD.png")
                    .build();
            userRepository.save(user1);
            User user2 = User.builder()
                    .nickname("김민지")
                    .email("sms7624@gmail.com")
                    .followedCount(9)
                    .userSetting(userSetting2)
                    .profileImageUrl("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/%EA%B9%80%EB%AF%BC%EC%A7%80_%ED%94%84%EB%A1%9C%ED%95%84.png")
                    .deviceToken("eMGjgF8-WURNkqIVeIlHgb:APA91bGbkGlfIL4iw8vqOoZKdXCp8GYdLxQlpT_8l3Ho-_cMvg9FSVuSTT8NkJYEdJod3LBH9SqTuPxhWtXVS7MXDwQwawThhyf6TleBDF28SkIsgW5jd00mXiFfkEqooB2TAgyStP7v")
                    .backgroundImageUrl("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/%EA%B9%80%EB%AF%BC%EC%A7%80_%EB%B0%B0%EA%B2%BD.png")
                    .build();
            userRepository.save(user2);
            User user3 = User.builder()
                    .nickname("손민상")
                    .email("sms7624@gmail.com")
                    .followedCount(0)
                    .userSetting(userSetting3)
                    .profileImageUrl("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/%EA%B9%80%EB%AF%BC%EC%A7%80_%ED%94%84%EB%A1%9C%ED%95%84.png")
                    .deviceToken("eMGjgF8-WURNkqIVeIlHgb:APA91bGbkGlfIL4iw8vqOoZKdXCp8GYdLxQlpT_8l3Ho-_cMvg9FSVuSTT8NkJYEdJod3LBH9SqTuPxhWtXVS7MXDwQwawThhyf6TleBDF28SkIsgW5jd00mXiFfkEqooB2TAgyStP7v")
                    .backgroundImageUrl("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/%EA%B9%80%EB%AF%BC%EC%A7%80_%EB%B0%B0%EA%B2%BD.png")
                    .build();
            userRepository.save(user3);
            for (int i = 0; i < 100; i++){
                UserSetting userSetting = new UserSetting();
                userSettingRepository.save(userSetting);
                User user = User.builder()
                        .nickname("Paging" + i)
                        .email("sms7624@gmail.com")
                        .followedCount(0)
                        .userSetting(userSetting)
                        .profileImageUrl("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/%EA%B9%80%EB%AF%BC%EC%A7%80_%ED%94%84%EB%A1%9C%ED%95%84.png")
                        .deviceToken("eMGjgF8-WURNkqIVeIlHgb:APA91bGbkGlfIL4iw8vqOoZKdXCp8GYdLxQlpT_8l3Ho-_cMvg9FSVuSTT8NkJYEdJod3LBH9SqTuPxhWtXVS7MXDwQwawThhyf6TleBDF28SkIsgW5jd00mXiFfkEqooB2TAgyStP7v")
                        .backgroundImageUrl("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/%EA%B9%80%EB%AF%BC%EC%A7%80_%EB%B0%B0%EA%B2%BD.png")
                        .build();
                userRepository.save(user);
            }

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
            List<Art> arts = new ArrayList<>();
            List<ThumbnailImage> thumbnailImages = new ArrayList<>();
            Art art1 = Art.builder()
                    .name("Filling Cabinet")
                    .artist(user1)
                    .likesCount(0)
                    .category("제품")
                    .price(100000)
                    .forSale(true)
                    .description("판재의 특징을 살린 오브젝트 디자인’ 프로제트에 맞춰 진행한 사무용품 카비넷’입니다. 효율성과 실용성에 치중하여 다소 복잡한 외관을 지닌 서류정리함을 심플한 형태를 가진 판재를 이용하여 단순하고 간결하게 리디자인하였습니다.")
                    .build();
            Art art2 = Art.builder()
                    .name("몰입북")
                    .artist(user1)
                    .likesCount(9)
                    .category("제품")
                    .price(1000000)
                    .forSale(true)
                    .description("몰입북")
                    .build();
            Art art3 = Art.builder()
                    .name("other")
                    .artist(user2)
                    .likesCount(9)
                    .category("제품")
                    .price(1000000)
                    .forSale(false)
                    .description("제품3")
                    .build();
            for (int i = 0; i < 50; i++){
                Art art4 = Art.builder()
                        .name("Filling Cabinet" + i)
                        .artist(user1)
                        .likesCount(0)
                        .category("제품")
                        .price(100000)
                        .forSale(true)
                        .description("판재의 특징을 살린 오브젝트 디자인’ 프로제트에 맞춰 진행한 사무용품 카비넷’입니다. 효율성과 실용성에 치중하여 다소 복잡한 외관을 지닌 서류정리함을 심플한 형태를 가진 판재를 이용하여 단순하고 간결하게 리디자인하였습니다.")
                        .build();
                Art art5 = Art.builder()
                        .name("몰입북" + i)
                        .artist(user1)
                        .likesCount(9)
                        .category("제품")
                        .price(1000000)
                        .forSale(true)
                        .description("몰입북")
                        .build();
                Art art6 = Art.builder()
                        .name("other" + i)
                        .artist(user2)
                        .likesCount(9)
                        .category("제품")
                        .price(1000000)
                        .forSale(false)
                        .description("제품3")
                        .build();
                arts.add(art4);
                arts.add(art5);
                arts.add(art6);
                ThumbnailImage thumbnailImage1 = ThumbnailImage.builder()
                        .art(art4)
                        .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/filling_cabinet_thumbnail_1.png")
                        .build();
                ThumbnailImage thumbnailImage2 = ThumbnailImage.builder()
                        .art(art5)
                        .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/filling_cabinet_thumbnail_2.png")
                        .build();
                ThumbnailImage thumbnailImage3 = ThumbnailImage.builder()
                        .art(art6)
                        .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/%EB%AA%B0%EC%9E%85%EB%B6%81.png")
                        .build();
                thumbnailImages.add(thumbnailImage1);
                thumbnailImages.add(thumbnailImage2);
                thumbnailImages.add(thumbnailImage3);

            }
            thumbnailImageRepository.saveAll(thumbnailImages);
            artRepository.save(art1);
            artRepository.save(art2);
            artRepository.save(art3);
            artRepository.saveAll(arts);
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
            ArtTag artTag7 = ArtTag.builder()
                    .art(art3)
                    .tag(tag1)
                    .build();
            ArtTag artTag8 = ArtTag.builder()
                    .art(art3)
                    .tag(tag2)
                    .build();
            ArtTag artTag9 = ArtTag.builder()
                    .art(art3)
                    .tag(tag3)
                    .build();
            artTagRepository.save(artTag1);
            artTagRepository.save(artTag2);
            artTagRepository.save(artTag3);
            artTagRepository.save(artTag4);
            artTagRepository.save(artTag5);
            artTagRepository.save(artTag6);
            artTagRepository.save(artTag7);
            artTagRepository.save(artTag8);
            artTagRepository.save(artTag9);
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
            ThumbnailImage thumbnailImage4 = ThumbnailImage.builder()
                    .art(art2)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/product/87ccfd11-ffdc-4df2-b329-0d35d9e22a3aart_2024-01-07+11%3A32%3A20+%2B0000_00.jpeg")
                    .build();
            ThumbnailImage thumbnailImage5 = ThumbnailImage.builder()
                    .art(art3)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/filling_cabinet_thumbnail_1.png")
                    .build();
            ThumbnailImage thumbnailImage6 = ThumbnailImage.builder()
                    .art(art3)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/filling_cabinet_thumbnail_2.png")
                    .build();
            thumbnailImageRepository.save(thumbnailImage1);
            thumbnailImageRepository.save(thumbnailImage2);
            art1.getThumbnailImageUrls().add(thumbnailImage1);
            art1.getThumbnailImageUrls().add(thumbnailImage2);
            thumbnailImageRepository.save(thumbnailImage3);
            thumbnailImageRepository.save(thumbnailImage4);
            art2.getThumbnailImageUrls().add(thumbnailImage3);
            art2.getThumbnailImageUrls().add(thumbnailImage4);
            thumbnailImageRepository.save(thumbnailImage5);
            thumbnailImageRepository.save(thumbnailImage6);
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
            ArtImage artImage4 = ArtImage.builder()
                    .art(art3)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/product/87ccfd11-ffdc-4df2-b329-0d35d9e22a3aart_2024-01-07+11%3A32%3A20+%2B0000_00.jpeg")
                    .width(242)
                    .height(161)
                    .build();
            artImageRepository.save(artImage1);
            artImageRepository.save(artImage2);
            artImageRepository.save(artImage3);
            artImageRepository.save(artImage4);
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
                    .backgroundColor("F2E5DF")
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
                    .backgroundColor("E2DFF2")
                    .category("제품")
                    .status("available")
                    .artCount("11점")
                    .estimatedDuration("11분")
                    .build();
            Exhibition exhibition3 = Exhibition.builder()
                    .posterImageUrl("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/filling_cabinet_thumbnail_2.png")
                    .name("전시회3")
                    .englishName("Exhibition3")
                    .backgroundColor("E2DFDF")
                    .category("제품")
                    .status("available")
                    .artCount("12점")
                    .estimatedDuration("12분")
                    .build();
            exhibitionRepository.save(exhibition1);
            exhibitionRepository.save(exhibition2);
            exhibitionRepository.save(exhibition3);
            ExhibitionArt exhibitionArt1 = ExhibitionArt.builder()
                    .exhibition(exhibition1)
                    .name("Chair")
                    .description("‘판재의 특징을 살린 오브젝트 디자인’ 프로제트에 맞춰 진행한 사무용품 카비넷’입니다. 효율성과 실용성에 치중하여 다소 복잡한 외관을 지닌 서류정리함을 심플한 형태를 가진 판재를 이용하여 단순하고 간결하게 리디자인하였습니다.")
                    .artist(user1)
                    .build();
            ExhibitionArt exhibitionArt2 = ExhibitionArt.builder()
                    .exhibition(exhibition1)
                    .name("오렌지 푹신 의자")
                    .description("‘판재의 특징을 살린 오브젝트 디자인’ 프로제트에 맞춰 진행한 사무용품 카비넷’입니다. 효율성과 실용성에 치중하여 다소 복잡한 외관을 지닌 서류정리함을 심플한 형태를 가진 판재를 이용하여 단순하고 간결하게 리디자인하였습니다.")
                    .artist(user2)
                    .build();
            ExhibitionArt exhibitionArt3 = ExhibitionArt.builder()
                    .exhibition(exhibition1)
                    .name("그림")
                    .description("그림그림그림")
                    .artist(user2)
                    .build();
            ExhibitionArt exhibitionArt4 = ExhibitionArt.builder()
                    .exhibition(exhibition2)
                    .name("Chair")
                    .description("‘판재의 특징을 살린 오브젝트 디자인’ 프로제트에 맞춰 진행한 사무용품 카비넷’입니다. 효율성과 실용성에 치중하여 다소 복잡한 외관을 지닌 서류정리함을 심플한 형태를 가진 판재를 이용하여 단순하고 간결하게 리디자인하였습니다.")
                    .artist(user1)
                    .build();
            ExhibitionArt exhibitionArt5 = ExhibitionArt.builder()
                    .exhibition(exhibition2)
                    .name("오렌지 푹신 의자")
                    .description("‘판재의 특징을 살린 오브젝트 디자인’ 프로제트에 맞춰 진행한 사무용품 카비넷’입니다. 효율성과 실용성에 치중하여 다소 복잡한 외관을 지닌 서류정리함을 심플한 형태를 가진 판재를 이용하여 단순하고 간결하게 리디자인하였습니다.")
                    .artist(user2)
                    .build();
            ExhibitionArt exhibitionArt6 = ExhibitionArt.builder()
                    .exhibition(exhibition2)
                    .name("그림")
                    .description("그림그림그림")
                    .artist(user2)
                    .build();
            exhibitionArtRepository.save(exhibitionArt1);
            exhibitionArtRepository.save(exhibitionArt2);
            exhibitionArtRepository.save(exhibitionArt3);
            exhibitionArtRepository.save(exhibitionArt4);
            exhibitionArtRepository.save(exhibitionArt5);
            exhibitionArtRepository.save(exhibitionArt6);

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
            ExhibitionArtThumbnailImage exhibitionArtThumbnailImage4 = ExhibitionArtThumbnailImage.builder()
                    .exhibitionArt(exhibitionArt2)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/filling_cabinet_thumbnail_1.png")
                    .build();
            ExhibitionArtThumbnailImage exhibitionArtThumbnailImage5 = ExhibitionArtThumbnailImage.builder()
                    .exhibitionArt(exhibitionArt2)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/filling_cabinet_thumbnail_2.png")
                    .build();
            ExhibitionArtThumbnailImage exhibitionArtThumbnailImage6 = ExhibitionArtThumbnailImage.builder()
                    .exhibitionArt(exhibitionArt2)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/%EB%AA%B0%EC%9E%85%EB%B6%81.png")
                    .build();
            ExhibitionArtThumbnailImage exhibitionArtThumbnailImage7 = ExhibitionArtThumbnailImage.builder()
                    .exhibitionArt(exhibitionArt3)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/filling_cabinet_thumbnail_1.png")
                    .build();
            ExhibitionArtThumbnailImage exhibitionArtThumbnailImage8 = ExhibitionArtThumbnailImage.builder()
                    .exhibitionArt(exhibitionArt3)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/filling_cabinet_thumbnail_2.png")
                    .build();
            ExhibitionArtThumbnailImage exhibitionArtThumbnailImage9 = ExhibitionArtThumbnailImage.builder()
                    .exhibitionArt(exhibitionArt3)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/%EB%AA%B0%EC%9E%85%EB%B6%81.png")
                    .build();
            ExhibitionArtThumbnailImage exhibitionArtThumbnailImage10 = ExhibitionArtThumbnailImage.builder()
                    .exhibitionArt(exhibitionArt4)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/filling_cabinet_thumbnail_1.png")
                    .build();
            ExhibitionArtThumbnailImage exhibitionArtThumbnailImage11 = ExhibitionArtThumbnailImage.builder()
                    .exhibitionArt(exhibitionArt4)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/filling_cabinet_thumbnail_2.png")
                    .build();
            ExhibitionArtThumbnailImage exhibitionArtThumbnailImage12 = ExhibitionArtThumbnailImage.builder()
                    .exhibitionArt(exhibitionArt4)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/%EB%AA%B0%EC%9E%85%EB%B6%81.png")
                    .build();
            ExhibitionArtThumbnailImage exhibitionArtThumbnailImage13 = ExhibitionArtThumbnailImage.builder()
                    .exhibitionArt(exhibitionArt5)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/filling_cabinet_thumbnail_1.png")
                    .build();
            ExhibitionArtThumbnailImage exhibitionArtThumbnailImage14 = ExhibitionArtThumbnailImage.builder()
                    .exhibitionArt(exhibitionArt5)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/filling_cabinet_thumbnail_2.png")
                    .build();
            ExhibitionArtThumbnailImage exhibitionArtThumbnailImage15 = ExhibitionArtThumbnailImage.builder()
                    .exhibitionArt(exhibitionArt5)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/%EB%AA%B0%EC%9E%85%EB%B6%81.png")
                    .build();
            ExhibitionArtThumbnailImage exhibitionArtThumbnailImage16 = ExhibitionArtThumbnailImage.builder()
                    .exhibitionArt(exhibitionArt6)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/filling_cabinet_thumbnail_1.png")
                    .build();
            ExhibitionArtThumbnailImage exhibitionArtThumbnailImage17 = ExhibitionArtThumbnailImage.builder()
                    .exhibitionArt(exhibitionArt6)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/filling_cabinet_thumbnail_2.png")
                    .build();
            ExhibitionArtThumbnailImage exhibitionArtThumbnailImage18 = ExhibitionArtThumbnailImage.builder()
                    .exhibitionArt(exhibitionArt6)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/%EB%AA%B0%EC%9E%85%EB%B6%81.png")
                    .build();
            exhibitionArtThumbnailImageRepository.save(exhibitionArtThumbnailImage1);
            exhibitionArtThumbnailImageRepository.save(exhibitionArtThumbnailImage2);
            exhibitionArtThumbnailImageRepository.save(exhibitionArtThumbnailImage3);
            exhibitionArtThumbnailImageRepository.save(exhibitionArtThumbnailImage4);
            exhibitionArtThumbnailImageRepository.save(exhibitionArtThumbnailImage5);
            exhibitionArtThumbnailImageRepository.save(exhibitionArtThumbnailImage6);
            exhibitionArtThumbnailImageRepository.save(exhibitionArtThumbnailImage7);
            exhibitionArtThumbnailImageRepository.save(exhibitionArtThumbnailImage8);
            exhibitionArtThumbnailImageRepository.save(exhibitionArtThumbnailImage9);
            exhibitionArtThumbnailImageRepository.save(exhibitionArtThumbnailImage10);
            exhibitionArtThumbnailImageRepository.save(exhibitionArtThumbnailImage11);
            exhibitionArtThumbnailImageRepository.save(exhibitionArtThumbnailImage12);
            exhibitionArtThumbnailImageRepository.save(exhibitionArtThumbnailImage13);
            exhibitionArtThumbnailImageRepository.save(exhibitionArtThumbnailImage14);
            exhibitionArtThumbnailImageRepository.save(exhibitionArtThumbnailImage15);
            exhibitionArtThumbnailImageRepository.save(exhibitionArtThumbnailImage16);
            exhibitionArtThumbnailImageRepository.save(exhibitionArtThumbnailImage17);
            exhibitionArtThumbnailImageRepository.save(exhibitionArtThumbnailImage18);
            ExhibitionArtist exhibitionArtist1 = ExhibitionArtist.builder()
                    .exhibition(exhibition1)
                    .user(user1)
                    .build();
            ExhibitionArtist exhibitionArtist2 = ExhibitionArtist.builder()
                    .exhibition(exhibition1)
                    .user(user2)
                    .build();
            ExhibitionArtist exhibitionArtist3 = ExhibitionArtist.builder()
                    .exhibition(exhibition2)
                    .user(user1)
                    .build();
            ExhibitionArtist exhibitionArtist4 = ExhibitionArtist.builder()
                    .exhibition(exhibition2)
                    .user(user2)
                    .build();
            exhibitionArtistRepository.save(exhibitionArtist1);
            exhibitionArtistRepository.save(exhibitionArtist2);
            exhibitionArtistRepository.save(exhibitionArtist3);
            exhibitionArtistRepository.save(exhibitionArtist4);



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
            ExhibitionArtImage exhibitionArtImage4 = ExhibitionArtImage.builder()
                    .exhibitionArt(exhibitionArt2)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/filling_cabinet_detail_1.png")
                    .width(390)
                    .height(174)
                    .build();
            ExhibitionArtImage exhibitionArtImage5 = ExhibitionArtImage.builder()
                    .exhibitionArt(exhibitionArt2)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/filling_cabinet_detail_2.png")
                    .width(390)
                    .height(268)
                    .build();
            ExhibitionArtImage exhibitionArtImage6 = ExhibitionArtImage.builder()
                    .exhibitionArt(exhibitionArt2)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/%EB%AA%B0%EC%9E%85%EB%B6%81.png")
                    .width(165)
                    .height(165)
                    .build();
            ExhibitionArtImage exhibitionArtImage7 = ExhibitionArtImage.builder()
                    .exhibitionArt(exhibitionArt3)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/filling_cabinet_detail_1.png")
                    .width(390)
                    .height(174)
                    .build();
            ExhibitionArtImage exhibitionArtImage8 = ExhibitionArtImage.builder()
                    .exhibitionArt(exhibitionArt3)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/filling_cabinet_detail_2.png")
                    .width(390)
                    .height(268)
                    .build();
            ExhibitionArtImage exhibitionArtImage9 = ExhibitionArtImage.builder()
                    .exhibitionArt(exhibitionArt3)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/%EB%AA%B0%EC%9E%85%EB%B6%81.png")
                    .width(165)
                    .height(165)
                    .build();
            ExhibitionArtImage exhibitionArtImage10 = ExhibitionArtImage.builder()
                    .exhibitionArt(exhibitionArt4)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/filling_cabinet_detail_1.png")
                    .width(390)
                    .height(174)
                    .build();
            ExhibitionArtImage exhibitionArtImage11 = ExhibitionArtImage.builder()
                    .exhibitionArt(exhibitionArt4)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/filling_cabinet_detail_2.png")
                    .width(390)
                    .height(268)
                    .build();
            ExhibitionArtImage exhibitionArtImage12 = ExhibitionArtImage.builder()
                    .exhibitionArt(exhibitionArt4)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/%EB%AA%B0%EC%9E%85%EB%B6%81.png")
                    .width(165)
                    .height(165)
                    .build();
            ExhibitionArtImage exhibitionArtImage13 = ExhibitionArtImage.builder()
                    .exhibitionArt(exhibitionArt5)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/filling_cabinet_detail_1.png")
                    .width(390)
                    .height(174)
                    .build();
            ExhibitionArtImage exhibitionArtImage14 = ExhibitionArtImage.builder()
                    .exhibitionArt(exhibitionArt5)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/filling_cabinet_detail_2.png")
                    .width(390)
                    .height(268)
                    .build();
            ExhibitionArtImage exhibitionArtImage15 = ExhibitionArtImage.builder()
                    .exhibitionArt(exhibitionArt5)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/%EB%AA%B0%EC%9E%85%EB%B6%81.png")
                    .width(165)
                    .height(165)
                    .build();
            ExhibitionArtImage exhibitionArtImage16 = ExhibitionArtImage.builder()
                    .exhibitionArt(exhibitionArt6)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/filling_cabinet_detail_1.png")
                    .width(390)
                    .height(174)
                    .build();
            ExhibitionArtImage exhibitionArtImage17 = ExhibitionArtImage.builder()
                    .exhibitionArt(exhibitionArt6)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/filling_cabinet_detail_2.png")
                    .width(390)
                    .height(268)
                    .build();
            ExhibitionArtImage exhibitionArtImage18 = ExhibitionArtImage.builder()
                    .exhibitionArt(exhibitionArt6)
                    .url("https://novart-bucket.s3.ap-northeast-2.amazonaws.com/plain/%EB%AA%B0%EC%9E%85%EB%B6%81.png")
                    .width(165)
                    .height(165)
                    .build();
            exhibitionArtImageRepository.save(exhibitionArtImage1);
            exhibitionArtImageRepository.save(exhibitionArtImage2);
            exhibitionArtImageRepository.save(exhibitionArtImage3);
            exhibitionArtImageRepository.save(exhibitionArtImage4);
            exhibitionArtImageRepository.save(exhibitionArtImage5);
            exhibitionArtImageRepository.save(exhibitionArtImage6);
            exhibitionArtImageRepository.save(exhibitionArtImage7);
            exhibitionArtImageRepository.save(exhibitionArtImage8);
            exhibitionArtImageRepository.save(exhibitionArtImage9);
            exhibitionArtImageRepository.save(exhibitionArtImage10);
            exhibitionArtImageRepository.save(exhibitionArtImage11);
            exhibitionArtImageRepository.save(exhibitionArtImage12);
            exhibitionArtImageRepository.save(exhibitionArtImage13);
            exhibitionArtImageRepository.save(exhibitionArtImage14);
            exhibitionArtImageRepository.save(exhibitionArtImage15);
            exhibitionArtImageRepository.save(exhibitionArtImage16);
            exhibitionArtImageRepository.save(exhibitionArtImage17);
            exhibitionArtImageRepository.save(exhibitionArtImage18);
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
