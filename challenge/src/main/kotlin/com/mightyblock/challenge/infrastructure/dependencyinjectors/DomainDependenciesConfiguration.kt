package com.mightyblock.challenge.infrastructure.dependencyinjectors

import com.mightyblock.challenge.domain.repositories.*
import com.mightyblock.challenge.domain.services.images.IImageService
import com.mightyblock.challenge.domain.services.images.ImageService
import com.mightyblock.challenge.domain.services.nfts.INFTService
import com.mightyblock.challenge.domain.services.nfts.NFTService
import com.mightyblock.challenge.domain.services.sales.INFTSaleService
import com.mightyblock.challenge.domain.services.sales.NFTSaleService
import com.mightyblock.challenge.domain.services.users.IUserService
import com.mightyblock.challenge.domain.services.users.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order

@Configuration
@Order(2)
class DomainDependenciesConfiguration {

    @Bean
    fun getUserService(@Autowired userRepositoryService: IUserRepositoryService): IUserService =
        UserService(userRepositoryService)

    @Bean
    fun getNFTService(
        @Autowired nftRepositoryService: INFTRepositoryService,
        @Autowired nftCoCreatorRepositoryService: INFTCoCreatorRepositoryService
    ): INFTService =
        NFTService(nftRepositoryService, nftCoCreatorRepositoryService)

    @Bean
    fun getImageService(@Autowired imageRepositoryService: IImageRepositoryService): IImageService = ImageService(imageRepositoryService)

    @Bean
    fun getNFTSaleService(
        @Autowired nftSaleRepositoryService: INFTSaleRepositoryService
    ): INFTSaleService =
        NFTSaleService(nftSaleRepositoryService)

}