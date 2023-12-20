package plain.spring.likes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import plain.spring.art.domain.Art;
import plain.spring.art.repository.ArtRepository;
import plain.spring.likes.service.LikesService;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@SpringBootTest
class LikesServiceTest {

    @Autowired
    LikesService likesService;
    @Autowired
    ArtRepository artRepository;
    @Test
    @DisplayName("like 동시성 테스트")
    void likesCountTest() throws InterruptedException {
        int threadCount = 10000;
        //given
        ExecutorService executorService = Executors.newFixedThreadPool(32);

        CountDownLatch latch = new CountDownLatch(threadCount);
        //when
        Long startTime = System.currentTimeMillis();
        for(int i=0; i<threadCount; i++){
            executorService.submit(()->{
                try{
                    likesService.like(1L);
                }
                finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        System.out.println("시간 : " + (System.currentTimeMillis() - startTime));
        Art art = artRepository.findById(1L).orElse(null);
        //then
        Assertions.assertEquals(threadCount, art.getLikesCount());
    }
}