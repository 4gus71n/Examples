package com.kimboo.core.interactors

import com.kimboo.core.mappers.toImgurList
import com.kimboo.core.repositories.imgur.ApiImgurGalleryRepository
import io.reactivex.disposables.Disposable
import java.net.ConnectException
import java.net.UnknownHostException

class GetImgurPostsInteractorImpl(
    private val apiImgurGalleryRepository: ApiImgurGalleryRepository
) : GetImgurPostsInteractor {

    private var disposable: Disposable? = null

    override fun execute(
        callback: GetImgurPostsInteractor.Callback,
        query: String,
        page: Int,
        sort: String,
        window: String
    ) {
        dispose()

        disposable = apiImgurGalleryRepository.fetchGalleryPostsByQuerySearch(
            query = query,
            page = page,
            sort = sort,
            window = window
        )
        .subscribe(
            {
                val list = it.response.toImgurList()
                callback.onSucessfullyFetchedImgurPosts(query, list)
            },
            {
                if (it is ConnectException || it is UnknownHostException) {
                    callback.onNoInternetConnection()
                } else {
                    callback.onErrorFecthingImgurPosts()
                }
            }
        )
    }

    override fun dispose() {
        if (disposable?.isDisposed == false) {
            disposable?.dispose()
        }
    }
}
