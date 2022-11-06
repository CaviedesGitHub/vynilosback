package com.miso.vyns

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miso.vyns.model.VynsAlbum
import com.miso.vyns.network.VynsApi
import kotlinx.coroutines.launch

class VynsViewModel: ViewModel() {
    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    private val _lstAlbums = MutableLiveData<List<VynsAlbum>>()
    val lstAlbums: LiveData<List<VynsAlbum>> = _lstAlbums

    private val _album = MutableLiveData<VynsAlbum>()
    val album: LiveData<VynsAlbum> = _album

    private val _palabra = MutableLiveData<String>()
    val palabra: LiveData<String> = _palabra

    private val _miUrl = MutableLiveData<String>()
    val miUrl: LiveData<String> = _miUrl

    init {
        getVynsAlbums()
        //getVynsAlbumDet(103)
    }

    //private
    fun getVynsAlbums() {
        viewModelScope.launch {
            try {
                val listResult = VynsApi.retrofitService.getAlbums()
                //_album.value=listResult[0]
                _lstAlbums.value=listResult
                _status.value = "Success: ${listResult.size} Vynilos Albums retrieved"
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }

    fun getVynsAlbumDet(id: Int) {
        Log.d("Album", "Inicio AlbumDet Get: "+id.toString())
        viewModelScope.launch {
            try {
                val result = VynsApi.retrofitService.getAlbumDet(id)
                Log.d("Album", "Inicio AlbumDet Get Post")
                Log.d("Album", "@{result.id}: "+result.id.toString())
                Log.d("Album", "@{result.id}: "+result.name)
                Log.d("Album", "@{result.id}: "+result.genre)
                Log.d("Album", "@{result.id}: "+result.releaseDate)
                _album.value = result
                _status.value = "Success: Vynilos Album retrieved"
                //notifyPropertyChanged(BR._all);
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }

    fun calculaLongitud(pal: String){
        _palabra.value=pal
        _miUrl.value="https://d7lju56vlbdri.cloudfront.net/var/ezwebin_site/storage/images/_aliases/img_1col/noticias/solar-orbiter-toma-imagenes-del-sol-como-nunca-antes/9437612-1-esl-MX/Solar-Orbiter-toma-imagenes-del-Sol-como-nunca-antes.jpg"
    }

    fun obtenerId22(pos: Int): Int {
        var lstTemp = _lstAlbums.value
        var idA: Int = 0
        for ((indice, item) in lstTemp!!.withIndex()) {
            if (indice==pos) {
                idA = item.id
            }
        }
        return idA
    }

    fun obtenerId(pos: Int): Int? {
        var lstTemp = _lstAlbums.value
        return lstTemp?.get(pos)?.id
    }

    fun actAlbum(id: Int){
        _album.value=_lstAlbums.value?.get(id)
        Log.d("INFO","album.id: "+album.value?.id.toString())
        Log.d("INFO","album.id: "+album.value?.name.toString())
        Log.d("INFO","album.id: "+album.value?.cover.toString())
        Log.d("INFO","album.id: "+album.value?.genre.toString())
    }
}