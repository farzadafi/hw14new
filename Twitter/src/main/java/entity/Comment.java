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
public class Comment extends BaseEntity<Integer> {

    private String comment;

    @ManyToOne
    private Twit twit;

    @OneToOne
    Comment parent;

    @ManyToOne
    private User user;
}