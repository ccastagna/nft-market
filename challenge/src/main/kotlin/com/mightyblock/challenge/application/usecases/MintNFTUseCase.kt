package com.mightyblock.challenge.application.usecases

import com.mightyblock.challenge.application.models.MintNFTRequest
import com.mightyblock.challenge.domain.models.NFT
import com.mightyblock.challenge.domain.models.User
import com.mightyblock.challenge.domain.services.images.IImageService
import com.mightyblock.challenge.domain.services.nfts.INFTService
import com.mightyblock.challenge.domain.services.users.IUserService

class MintNFTUseCase(
    private val userService: IUserService,
    private val imageService: IImageService,
    private val nftService: INFTService
) : IMintNFTUseCase {
    override fun execute(request: MintNFTRequest): NFT {

        val creator: User = userService.getCreatorById(request.creatorId)

        request.coCreators
            .map { coCreatorId ->
                userService.getCoCreatorById(coCreatorId)
            }

        imageService.checkImageIsLoadedOrThrow(request.imageUrl)

        return nftService.mint(creator.userId, request.coCreators, request.imageUrl, request.description)
    }
}