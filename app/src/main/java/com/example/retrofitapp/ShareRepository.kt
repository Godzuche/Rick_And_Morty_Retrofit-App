package com.example.retrofitapp

class ShareRepository {

    suspend fun getCharacterById(characterId: Int):GetCharacterByIdResponse?{
        val request = NetworkLayer.apiClient.getCharacterById(characterId)

        if(request.failed) {
            return null
        }

        if(!request.isSuccessful) {
            return null
        }
        return request.body
    }
}
