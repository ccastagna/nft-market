package com.mightyblock.challenge.infrastructure.dependencyinjectors

import com.mightyblock.challenge.domain.repositories.*
import com.mightyblock.challenge.infrastructure.drivens.repositories.images.ImageRepositoryService
import com.mightyblock.challenge.infrastructure.drivens.repositories.nftcocreators.INFTCoCreatorRepository
import com.mightyblock.challenge.infrastructure.drivens.repositories.nftcocreators.NFTCoCreatorRepositoryService
import com.mightyblock.challenge.infrastructure.drivens.repositories.nfts.INFTRepository
import com.mightyblock.challenge.infrastructure.drivens.repositories.nfts.NFTRepositoryService
import com.mightyblock.challenge.infrastructure.drivens.repositories.sales.INFTSaleRepository
import com.mightyblock.challenge.infrastructure.drivens.repositories.sales.NFTSaleRepositoryService
import com.mightyblock.challenge.infrastructure.drivens.repositories.users.IUserRepository
import com.mightyblock.challenge.infrastructure.drivens.repositories.users.UserRepositoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order

@Configuration
@Order(1)
class DrivenDependenciesConfiguration {

    @Bean
    fun getUserRepositoryService(@Autowired userRepository: IUserRepository): IUserRepositoryService =
        UserRepositoryService(userRepository)

    @Bean
    fun getNFTRepositoryService(@Autowired nftRepository: INFTRepository): INFTRepositoryService =
        NFTRepositoryService(nftRepository)

    @Bean
    fun getNFTCoCreatorRepositoryService(@Autowired nftCoCreatorRepository: INFTCoCreatorRepository): INFTCoCreatorRepositoryService =
        NFTCoCreatorRepositoryService(nftCoCreatorRepository)

    @Bean
    fun getNFTSaleRepositoryService(@Autowired nftSaleRepository: INFTSaleRepository): INFTSaleRepositoryService =
        NFTSaleRepositoryService(nftSaleRepository)

    @Bean
    fun getImageRepositoryService(): IImageRepositoryService = ImageRepositoryService()
}
