package com.sirex.presentation.mappers

import com.sirex.domain.entites.DbUser
import com.sirex.domain.model.ApiUser
import com.sirex.presentation.model.UserListItem

fun DbUser.mapToListItem() = UserListItem.UserItem(
    id = id,
    avatarUrl = avatarUrl,
    htmlUrl = htmlUrl,
    login = login
)