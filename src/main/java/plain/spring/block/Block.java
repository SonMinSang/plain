package plain.spring.block;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import plain.spring.user.domain.User;

@Entity
@Table(name = "blocks")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Block {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "block_id")
    private Long id;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "blocker_id")
    private User blocker;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "blocked_id")
    private User blocked;
}
