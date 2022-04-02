package entity;

import entity.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class Twit extends BaseEntity<Integer> {

    @Column(nullable = false,length = 280)
    private String twit;

    private Integer likeNumber;
    private Integer dislikeNumber;

    @ManyToOne
    private User user;
}
