package com.muscatlab.bob.domain.donation.query;

import com.muscatlab.bob.domain.donation.entity.Donation;

import java.util.List;

public interface DonationQueryService {
    List<Donation> getAll();
}
