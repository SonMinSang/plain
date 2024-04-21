package plain.spring.search.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import plain.spring.commons.exception.CustomException;
import plain.spring.commons.util.SecurityUtil;
import plain.spring.search.domain.Search;
import plain.spring.search.repository.SearchRepository;

import java.util.List;

import static plain.spring.commons.exception.ErrorCode.USER_NOT_FOUND;

@Service
@Transactional
@RequiredArgsConstructor
public class SearchService {

    private final SearchRepository searchRepository;

    public List<String> getAllSearchQuery(){
        String userId = SecurityUtil.getId().orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        List<String> result = searchRepository.findSearchByUserId(Long.parseLong(userId));
        return result;
    }

    public void deleteSearchQuery(String query){
        String userId = SecurityUtil.getId().orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        List<Search> searches = searchRepository.findSearchByQueryAndUserId(Long.parseLong(userId), query);
        searchRepository.deleteAll(searches);
    }

    public void deleteAllSearchQuery(){
        String userId = SecurityUtil.getId().orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        List<Search> searches = searchRepository.findAllSearchByUserId(Long.parseLong(userId));
        searchRepository.deleteAll(searches);
    }
}
