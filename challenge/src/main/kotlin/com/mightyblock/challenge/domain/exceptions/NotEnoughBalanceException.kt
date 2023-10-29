package com.mightyblock.challenge.domain.exceptions

import java.math.BigDecimal

class NotEnoughBalanceException(buyerId: Int, balance: BigDecimal, nftPrice: BigDecimal) :
    DomainClientException("Buyer $buyerId does not have enough balance to buy the NFT. Balance: $balance, NFT price: $nftPrice") {
}
