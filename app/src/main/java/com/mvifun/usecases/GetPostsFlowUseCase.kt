package com.mvifun.usecases

import com.mvifun.repository.PostRepository
import com.mvifun.util.networkBoundResource
import javax.inject.Inject

class GetPostsFlowUseCase @Inject constructor(
    private val repo: PostRepository,
) {
    operator fun invoke() = networkBoundResource(
        query = {
            repo.getPostsDatabaseFlow()
        },
        fetch = {
            repo.getPostsNetworkList()
        },
        saveFetchResult = {
            repo.deleteFromThenInsertIntoDatabase(it)
        }
    )
}