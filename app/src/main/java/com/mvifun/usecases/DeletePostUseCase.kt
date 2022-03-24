package com.mvifun.usecases

import com.mvifun.repository.PostRepository
import javax.inject.Inject

class DeletePostUseCase @Inject constructor(
    private val repo: PostRepository,
) {
    suspend operator fun invoke(id: Int) {
        repo.deletePostById(id)
    }
}