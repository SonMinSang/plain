
package plain.spring.tag.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tags")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "tag_id")
    private Long id;

    private String name;
}
