package com.mightyblock.challenge.infrastructure.dependencyinjectors

import com.mightyblock.challenge.application.usecases.*
import com.mightyblock.challenge.domain.services.images.IImageService
import com.mightyblock.challenge.domain.services.nfts.INFTService
import com.mightyblock.challenge.domain.services.sales.INFTSaleService
import com.mightyblock.challenge.domain.services.users.IUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order

@Configuration
@Order(3)
class ApplicationDependenciesConfiguration {

    @Bean
    fun getMintNFTUseCase(
        @Autowired userService: IUserService,
        @Autowired imageService: IImageService,
        @Autowired nftService: INFTService
    ): IMintNFTUseCase =
        MintNFTUseCase(userService, imageService, nftService)

    @Bean
    fun getGetAllNFTsPaginatedUseCase(
        @Autowired nftService: INFTService
    ): IGetAllNFTsPaginatedUseCase =
        GetAllNFTsPaginatedUseCase(nftService)

    @Bean
    fun getBuyNFTUseCase(
        @Autowired userService: IUserService,
        @Autowired nftSaleService: INFTSaleService,
        @Autowired nftService: INFTService
    ): IBuyNFTUseCase =
        BuyNFTUseCase(userService, nftSaleService, nftService)

    @Bean
    fun getUploadNFTImageUseCase(
        @Autowired imageService: IImageService,
    ): IUploadNFTImageUseCase =
        UploadNFTImageUseCase(imageService)

    @Bean
    fun getGetNFTImageUseCase(
        @Autowired imageService: IImageService,
    ): IGetNFTImageUseCase =
        GetNFTImageUseCase(imageService)
}