package com.pickx3.dto;

import com.pickx3.domain.entity.Favorites;
import com.pickx3.domain.entity.portfolio_package.Portfolio;
import com.pickx3.domain.entity.user_package.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class FavoritesDto {

    private Long id;

    private User user;

    private Portfolio portfolio;

    // DTO -> Entity
    @Builder
    public Favorites toEntity(){
        return Favorites.builder()
                .id(id)
                .user(user)
                .portfolio(portfolio)
                .build();

    }

}
