package com.example.data.network.common

import com.example.data.cashe.room.features.home.entities.MostPopularEntity
import com.example.data.cashe.room.features.signup.entities.UserEntity
import com.example.domain.model.home.MostPopular
import com.example.domain.model.signup.User

internal fun User.toUserEntity(): UserEntity {
    return UserEntity(
        fullName = this.fullName,
        email = this.email,
        nationalId = this.nationalId,
        phoneNumber = this.phoneNumber,
        dateOfBirth = this.dateOfBirth,
        password = this.password,
    )
}

internal fun UserEntity.toUser(): User {
    return User(
        fullName = this.fullName,
        email = this.email,
        nationalId = this.nationalId,
        phoneNumber = this.phoneNumber,
        dateOfBirth = this.dateOfBirth,
        password = this.password
    )
}

internal fun List<MostPopularEntity>.toMostPopular(): List<MostPopular> {
    val mostPopularList = arrayListOf<MostPopular>()
    this.map {
        mostPopularList.add(
            MostPopular(
                title = it.title,
                id = it.id,
                newsImage = it.newsImage,
                publishTime = it.publishTime,
                url = it.url
            )
        )
    }
    return mostPopularList
}

internal fun List<MostPopular?>.toMostPopularEntity(): List<MostPopularEntity> {
    val mostPopularEntityList = arrayListOf<MostPopularEntity>()
    this.map { mostPopular ->
        mostPopularEntityList.add(
            MostPopularEntity(
                title = mostPopular?.title ?: "",
                id = mostPopular?.id ?: 0,
                newsImage = mostPopular?.newsImage ?: "",
                publishTime = mostPopular?.publishTime ?: "",
                url = mostPopular?.url ?: ""
            )
        )
    }
    return mostPopularEntityList
}

