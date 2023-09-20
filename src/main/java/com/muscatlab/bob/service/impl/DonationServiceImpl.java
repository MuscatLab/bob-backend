package com.muscatlab.bob.service.impl;

import com.muscatlab.bob.domain.donation.entity.Donation;
import com.muscatlab.bob.domain.donation.query.DonationQueryService;
import com.muscatlab.bob.repository.DonationRepository;
import com.muscatlab.bob.service.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonationServiceImpl implements DonationService {
    private final DonationQueryService donationQueryService;

    @Override
    public int getAllDonationAmount() {
        List<Donation> donations = this.donationQueryService.getAll();
        int sum = 0;
        for (Donation donation : donations) {
            sum += donation.getAmount();
        }
        return sum;
    }
}
