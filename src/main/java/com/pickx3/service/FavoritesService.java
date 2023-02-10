package com.pickx3.service;

import com.pickx3.domain.entity.Favorites;
import com.pickx3.domain.entity.portfolio_package.Portfolio;
import com.pickx3.domain.entity.user_package.User;
import com.pickx3.domain.repository.FavoriteRepository;
import com.pickx3.domain.repository.PortfolioRepository;
import com.pickx3.domain.repository.UserRepository;
import com.pickx3.dto.FavoritesDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional @Slf4j
@Service
public class FavoritesService {

    private final UserRepository userRepository;
    private final PortfolioRepository portfolioRepository;

    private final FavoriteRepository favoriteRepository;

    // 좋아요
    public Long addLike(FavoritesDto favoritesDto){
        Portfolio portfolio = portfolioRepository.findById(favoritesDto.getPortfolio().getId()).orElseThrow(() -> new IllegalArgumentException("id가 존재하지 않습니다." ));

        User user = userRepository.findById(favoritesDto.getUser().getId()).orElseThrow(() -> new IllegalArgumentException("id가 존재하지 않습니다." ));
        // 좋아요 유무 확인 .isPresent ( == ) 값이 있으면 True 반환
        if(favoriteRepository.findByUserAndPortfolio(user, portfolio).isPresent()){
            throw new RuntimeException();
        }

        Favorites favorites = Favorites.builder()
                .portfolio(portfolio)
                .user(user)
                .build();

        favoriteRepository.save(favorites);

        return favorites.getId();
    }

    // 좋아요 취소
    public void cancelLike(FavoritesDto favoritesDto) {
        Portfolio portfolio = portfolioRepository.findById(favoritesDto.getPortfolio().getId()).orElseThrow(() -> new IllegalArgumentException("id가 존재하지 않습니다." ));

        User user = userRepository.findById(favoritesDto.getUser().getId()).orElseThrow(() -> new IllegalArgumentException("id가 존재하지 않습니다." ));

        Favorites favorites = favoriteRepository.findByUserAndPortfolio(user, portfolio).orElseThrow(() -> new IllegalArgumentException("id(user, portfolio)가 존재하지 않습니다." ));

        favoriteRepository.delete(favorites);
    }

    //좋아요한 작업물 목록 조회
    public Long select(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(" userid가 존재하지 않습니다." ));
        log.debug("useruseruseruseruseruseruseruseruser = user.getId() === " + user.getId());

        Portfolio portfolio = portfolioRepository.findByUser_portfolioId(user.getId());
        log.debug("portfolioportfolioportfolioportfolio = user.getId() === " + user.getId());

     //   Portfolio portfolio = Portfolio.builder();

        Favorites favorites = favoriteRepository.findByUserAndPortfolio(user, portfolio).orElseThrow(() -> new IllegalArgumentException("id(user, portfolio)가 존재하지 않습니다." ));


        Optional<Portfolio> portfolioList = portfolioRepository.findById(favorites.getPortfolio().getId());

        return portfolioList.get().getId();

    //where userid = {id}
    }
}
