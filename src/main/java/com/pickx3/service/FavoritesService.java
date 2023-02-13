package com.pickx3.service;

import com.pickx3.domain.entity.portfolio_package.Favorites;
import com.pickx3.domain.entity.portfolio_package.FavoritesForm;
import com.pickx3.domain.entity.portfolio_package.Portfolio;
import com.pickx3.domain.entity.portfolio_package.dto.PortfolioResponseDto;
import com.pickx3.domain.entity.user_package.User;
import com.pickx3.domain.repository.FavoriteRepository;
import com.pickx3.domain.repository.PortfolioRepository;
import com.pickx3.domain.repository.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.pickx3.domain.entity.QFavorites.favorites;
import static com.pickx3.domain.entity.portfolio_package.QPortfolio.portfolio;


@RequiredArgsConstructor
@Transactional @Slf4j
@Service
public class FavoritesService {

    private final UserRepository userRepository;
    private final PortfolioRepository portfolioRepository;
    private final FavoriteRepository favoriteRepository;

    @PersistenceContext // 영속성 객체를 자동으로 삽입해줌
    private EntityManager em;

    // 좋아요
    public Long addLike(FavoritesForm favoritesForm){
        Portfolio portfolio = portfolioRepository.findById(favoritesForm.getPortfolioNum()).orElseThrow(() -> new IllegalArgumentException("id가 존재하지 않습니다." ));
        User user = userRepository.findById(favoritesForm.getUserNum()).orElseThrow(() -> new IllegalArgumentException("id가 존재하지 않습니다." ));

        // 좋아요 유무 확인 .isPresent ( == ) 값이 있으면 True 반환
        if(favoriteRepository.findByUserAndPortfolio(user, portfolio).isPresent()){throw new RuntimeException();}

        Favorites favorites = Favorites.builder()
                .portfolio(portfolio)
                .user(user)
                .build();

        favoriteRepository.save(favorites);

        return favorites.getId();
    }

    // 좋아요 취소
    public void cancelLike(FavoritesForm favoritesForm) {
        Portfolio portfolio = portfolioRepository.findById(favoritesForm.getPortfolioNum()).orElseThrow(() -> new IllegalArgumentException("id가 존재하지 않습니다." ));
        User user = userRepository.findById(favoritesForm.getUserNum()).orElseThrow(() -> new IllegalArgumentException("id가 존재하지 않습니다." ));
        Favorites favorites = favoriteRepository.findByUserAndPortfolio(user, portfolio).orElseThrow(() -> new IllegalArgumentException("id(user, portfolio)가 존재하지 않습니다." ));

        favoriteRepository.delete(favorites);
    }

    //좋아요한 작업물 목록 조회
    public List<PortfolioResponseDto> select(Long id) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("user_id가 존재하지 않습니다."));

        //조회
        List<Portfolio> result = queryFactory.selectFrom(portfolio)
                .join(portfolio.favorites, favorites)
                .on(favorites.user.id.eq(user.getId()).and(portfolio.user.id.eq(user.getId())))
                .fetch();

        // list add
        List<PortfolioResponseDto> portfolioResponseDtos = new ArrayList<>();
        result.forEach(s -> portfolioResponseDtos.add(new PortfolioResponseDto(s)));

        return portfolioResponseDtos;
    }

}
