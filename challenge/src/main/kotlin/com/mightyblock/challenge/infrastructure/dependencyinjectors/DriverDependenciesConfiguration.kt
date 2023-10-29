package com.mightyblock.challenge.infrastructure.dependencyinjectors

import com.mightyblock.challenge.application.usecases.*
import com.mightyblock.challenge.infrastructure.drivers.dtos.requests.*
import com.mightyblock.challenge.infrastructure.drivers.dtos.requests.validators.*
import com.mightyblock.challenge.infrastructure.drivers.handlers.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order

@Configuration
@Order(4)
class DriverDependenciesConfiguration {

    //validators
    @Bean
    fun getBuyNFTInputRequestValidator(): IInputRequestValidator<BuyNFTRequestDTO> = BuyNFTInputRequestDTOValidator()

    @Bean
    fun getMintNFTInputRequestValidator(): IInputRequestValidator<MintNFTRequestDTO> = MintNFTInputRequestDTOValidator()

    @Bean
    fun getGetAllNFTsPaginatedInputRequestValidator(): IInputRequestValidator<GetAllNFTsPaginatedRequestDTO> =
        GetAllNFTsPaginatedInputRequestDTOValidator()

    @Bean
    fun getUploadNFTImageInputRequestValidator(): IInputRequestValidator<UploadNFTImageRequestDTO> =
        UploadNFTImageInputRequestDTOValidator()

    @Bean
    fun getGetNFTImageInputRequestValidator(): IInputRequestValidator<GetNFTImageRequestDTO> =
        GetNFTImageInputRequestDTOValidator()

    //handlers
    @Bean
    fun getBuyNFTHandler(
        @Autowired buyNFTInputRequestDTOValidator: IInputRequestValidator<BuyNFTRequestDTO>,
        @Autowired buyNFTUseCase: IBuyNFTUseCase
    ): IRequestHandler<BuyNFTRequestDTO> = BuyNFTHandler(buyNFTInputRequestDTOValidator, buyNFTUseCase)

    @Bean
    fun getMintNFTHandler(
        @Autowired mintNFTInputRequestDTOValidator: IInputRequestValidator<MintNFTRequestDTO>,
        @Autowired mintNFTUseCase: IMintNFTUseCase
    ): IRequestHandler<MintNFTRequestDTO> = MintNFTHandler(mintNFTInputRequestDTOValidator, mintNFTUseCase)

    @Bean
    fun getGetAllNFTsPaginatedHandler(
        @Autowired getAllNFTsPaginatedInputRequestDTOValidator: IInputRequestValidator<GetAllNFTsPaginatedRequestDTO>,
        @Autowired getAllNFTsPaginatedUseCase: IGetAllNFTsPaginatedUseCase
    ): IRequestHandler<GetAllNFTsPaginatedRequestDTO> =
        GetAllNFTsPaginatedHandler(getAllNFTsPaginatedInputRequestDTOValidator, getAllNFTsPaginatedUseCase)

    @Bean
    fun getUploadNFTImagedHandler(
        @Autowired uploadNFTImageInputRequestDTOValidator: IInputRequestValidator<UploadNFTImageRequestDTO>,
        @Autowired uploadNFTImageUseCase: IUploadNFTImageUseCase
    ): IRequestHandler<UploadNFTImageRequestDTO> =
        UploadNFTImageHandler(uploadNFTImageInputRequestDTOValidator, uploadNFTImageUseCase)

    @Bean
    fun getGetNFTImagedHandler(
        @Autowired getNFTImageInputRequestDTOValidator: IInputRequestValidator<GetNFTImageRequestDTO>,
        @Autowired getNFTImageUseCase: IGetNFTImageUseCase
    ): IRequestHandler<GetNFTImageRequestDTO> =
        GetNFTImageHandler(getNFTImageInputRequestDTOValidator, getNFTImageUseCase)

}