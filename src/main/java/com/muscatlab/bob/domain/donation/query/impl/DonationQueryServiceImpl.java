package com.muscatlab.bob.domain.donation.query.impl;

import com.muscatlab.bob.domain.donation.entity.Donation;
import com.muscatlab.bob.domain.donation.query.DonationQueryService;
import com.muscatlab.bob.repository.DonationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DonationQueryServiceImpl implements DonationQueryService {
    private final DonationRepository donationRepository;

    @Override
    public List<Donation> getAll() {
        return this.donationRepository.findAll();
    }
}
