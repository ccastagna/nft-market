package com.mightyblock.challenge.domain.exceptions

import java.util.*

class NFTNotFoundException(nftId: UUID) : DomainClientException("NFT $nftId not found.") {

}
