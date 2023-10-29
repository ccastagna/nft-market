package com.mightyblock.challenge.domain.exceptions

import java.util.*

class BuyerIsNFTOwnerException(buyerId: Int, nftId: UUID) :
    DomainClientException("Buyer $buyerId is the owner of the NFT $nftId") {
}
