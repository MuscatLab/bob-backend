package com.muscatlab.bob.repository;

import com.muscatlab.bob.domain.card.entity.Card;

public interface CardRepositoryCustom {
    Card findByUid(String uid);
}
