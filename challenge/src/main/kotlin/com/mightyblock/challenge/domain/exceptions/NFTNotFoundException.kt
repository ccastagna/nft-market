package com.mightyblock.challenge.domain.exceptions

import java.util.*

class NFTNotFoundException(nftId: UUID) : DomainClientException("NFT not found: $nftId") {

}
