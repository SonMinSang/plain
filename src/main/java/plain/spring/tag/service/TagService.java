package plain.spring.tag.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import plain.spring.tag.domain.Tag;
import plain.spring.tag.repository.TagRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public Tag createOrFindTag(String tagName){
        Tag tag = tagRepository.findByName(tagName).orElse(Tag.builder().name(tagName).build());
        tagRepository.save(tag);
        return tag;
    }
}
