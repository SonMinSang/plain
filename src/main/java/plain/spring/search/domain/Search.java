package plain.spring.search.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import plain.spring.user.domain.User;

@Entity
@Table(name = "query")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Search {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "query_id")
    private Long id;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String query;
}
