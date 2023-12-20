package plain.spring.exhibition.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import plain.spring.commons.util.SecurityUtil;
import plain.spring.exhibition.domain.Exhibition;
import plain.spring.exhibition.dto.ExhibitionDetailResponse;
import plain.spring.exhibition.dto.ExhibitionInfo;
import plain.spring.exhibition.dto.ExhibitionSummary;
import plain.spring.exhibition.dto.ExhibitionWithLikes;
import plain.spring.exhibition.repository.ExhibitionRepository;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ExhibitionService {
    private final ExhibitionRepository exhibitionRepository;

    public List<ExhibitionSummary> getAllAvailableExhibitions(){
        String id = SecurityUtil.getId().orElse(null);
        Long userId;
        if (id == null)
            userId = null;
        else {
            userId = Long.parseLong(id);
        }
        List<ExhibitionSummary> exhibitions = exhibitionRepository.findAllAvailableExhibition(userId);

        return exhibitions;
    }
    public ExhibitionDetailResponse getExhibitionDetail(Long exhibitionId) {
        String id = SecurityUtil.getId().orElse(null);
        ExhibitionDetailResponse result;
        ExhibitionWithLikes exhibition;
        if (id == null) {
            exhibition = exhibitionRepository.findExhibitionWithLikes(exhibitionId, null).orElseThrow();
            result = new ExhibitionDetailResponse(exhibition);
        }
        else {
            exhibition = exhibitionRepository.findExhibitionWithLikes(exhibitionId, Long.parseLong(id)).orElseThrow();
            List<Long> userIds = exhibition.getExhibition().getArtists().stream().map(e -> e.getId()).collect(Collectors.toList());
            HashSet<Long> followingIds = new HashSet<Long>(userIds);
            result = new ExhibitionDetailResponse(exhibition, followingIds);

        }
        return result;
    }
    public ExhibitionInfo getExhibitionInfo(Long exhibitionId){
        Exhibition exhibition = exhibitionRepository.findByIdAvailable(exhibitionId).orElseThrow();
        return new ExhibitionInfo(exhibition);

    }


}
