package entity;

import entity.base.BaseEntity;
import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "Users")
public class User extends BaseEntity<Integer> {

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false,unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "following",fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<User> followers = new HashSet<>();

    @JoinTable(name = "followers",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "follower_id")})
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<User> following = new HashSet<>();

    public void addFollower(User toFollow) {
        following.add(toFollow);
        toFollow.getFollowers().add(this);
    }

    public void removeFollower(User toFollow) {
        following.remove(toFollow);
        toFollow.getFollowers().remove(this);
    }

}
