package com.enesselcuk.watchmovies.source.local.paging

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeysEntity(
    @PrimaryKey val repoId: Int?,
    val prevKey: Int?,
    val nextKey: Int?,

)
