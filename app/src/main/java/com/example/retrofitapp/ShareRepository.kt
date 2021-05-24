package com.example.retrofitapp

class ShareRepository {

    suspend fun getCharacterById(characterId: Int):GetCharacterByIdResponse?{
        val request = NetworkLayer.apiClient.getCharacterById(characterId)
        if(request.isSuccessful) {
            return request.body()!!
        }
        return null
    }
}
