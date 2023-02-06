package com.pickx3.domain.entity.work_package;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pickx3.domain.entity.user_package.User;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Work {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workNum;

    private String workName;

    private int workPrice;

    private String workDesc;

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="userNum")
    private User userInfo;


    public void updateWork(String workName, int workPrice, String workDesc){
        this.workName = workName;
        this.workPrice = workPrice;
        this.workDesc = workDesc;
    }

}
