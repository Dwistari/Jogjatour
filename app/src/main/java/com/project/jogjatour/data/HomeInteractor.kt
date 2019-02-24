package com.project.jogjatour.data

import com.project.jogjatour.data.api.ApiServiceInterface
import io.reactivex.Observable

class HomeInteractor {

    fun getData ( ): Observable <DestinationResponse> {
        return api.getData ()
    }

    private var api: ApiServiceInterface = ApiServiceInterface.create()
}